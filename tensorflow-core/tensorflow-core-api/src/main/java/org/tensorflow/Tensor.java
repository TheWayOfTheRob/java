/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_AllocateTensor;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteTensor;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_Dim;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NumDims;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorByteSize;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorType;

import java.util.function.Consumer;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.types.family.TType;

/**
 * A statically typed multi-dimensional array whose elements are of a type described by T.
 *
 * <p>Instances of a Tensor are <b>not</b> thread-safe.
 *
 * <p><b>WARNING:</b> Resources consumed by the Tensor object <b>must</b> be explicitly freed by
 * invoking the {@link #close()} method when the object is no longer needed. For example, using a
 * try-with-resources block:
 *
 * <pre>{@code
 * try (Tensor t = Tensor.of(...)) {
 *   doSomethingWith(t);
 * }
 * }</pre>
 */
public final class Tensor<T extends TType> implements AutoCloseable {

  /**
   * Allocates a tensor of a given datatype and shape.
   *
   * <p>The amount of memory to allocate is derived from the datatype and the shape of the tensor.
   * Memory is left uninitialized after this method returns, so it is the responsibility of the
   * caller to initialize the tensor data before it is used, via the {@link #data()} accessor.
   * For example:
   *
   * <pre>{@code
   * FloatNdArray data = ...
   * try (Tensor<TFloat32> t = Tensor.of(TFloat32.DTYPE, Shape.of(2, 2))) {
   *   data.copyTo(t.data());
   *   ...
   * }
   * }</pre>
   *
   * @param <T> the tensor element type
   * @param dtype datatype of the tensor
   * @param shape shape of the tensor
   * @return an allocated but uninitialized tensor
   */
  public static <T extends TType> Tensor<T> of(DataType<T> dtype, Shape shape) {
    return of(dtype, shape, shape.size() * dtype.byteSize());
  }

  /**
   * Allocates a tensor of a given datatype, shape and size.
   *
   * <p>This method is identical to {@link #of(DataType, Shape)}, except that the final size of the
   * tensor is explicitly set instead of computing it from the datatype and shape.
   *
   * <p>This could be useful for tensor types that stores data but also metadata in the tensor memory,
   * like {@link org.tensorflow.types.TString TString}.
   *
   * @param <T> the tensor element type
   * @param dtype datatype of the tensor
   * @param shape shape of the tensor
   * @param size size, in bytes, of the tensor
   * @return an allocated but uninitialized tensor
   * @see #of(DataType, Shape)
   */
  public static <T extends TType> Tensor<T> of(DataType<T> dtype, Shape shape, long size) {
    Tensor<T> t = new Tensor<>(dtype, shape);
    TF_Tensor nativeHandle = allocate(t.dtype.nativeCode(), shape.asArray(), size);
    t.nativeRef = new NativeReference(nativeHandle);
    return t;
  }

  /**
   * Allocates and initialize a tensor of a given datatype and shape.
   *
   * <p>The amount of memory to allocate is derived from the datatype and the shape of the tensor.
   * Tensor data is initialized by calling the {@code dataInitializer}, which receives in argument
   * the value returned by {@link #data()} on the allocated tensor. For example:
   *
   * <pre>{@code
   * FloatNdArray data = ...
   * try (Tensor<TFloat32> t = Tensor.of(TFloat32.DTYPE, Shape.of(2, 2), data::copyTo)) {
   *   ...
   * }
   * }</pre>
   *
   * <p>If {@code dataInitializer} fails and throws an exception, the allocated tensor will be
   * automatically released before rethrowing the same exception.
   *
   * @param <T> the tensor element type
   * @param dtype datatype of the tensor
   * @param shape shape of the tensor
   * @param dataInitializer method receiving accessor to the allocated tensor data for initialization
   * @return an allocated and initialized tensor
   */
  public static <T extends TType> Tensor<T> of(DataType<T> dtype, Shape shape,
      Consumer<T> dataInitializer) {
    return of(dtype, shape, shape.size() * dtype.byteSize(), dataInitializer);
  }

  /**
   * Allocates a tensor of a given datatype, shape and size.
   *
   * <p>This method is identical to {@link #of(DataType, Shape, Consumer)}, except that the final
   * size for the tensor is explicitly set instead of being computed from the datatype and shape.
   *
   * <p>This could be useful for tensor types that stores data but also metadata in the tensor memory,
   * such as {@link org.tensorflow.types.TString TString}.
   *
   * @param <T> the tensor element type
   * @param dtype datatype of the tensor
   * @param shape shape of the tensor
   * @param size size, in bytes, of the tensor
   * @param dataInitializer method receiving accessor to the allocated tensor data for initialization
   * @return an allocated and initialized tensor
   * @see #of(DataType, Shape, long, Consumer)
   */
  public static <T extends TType> Tensor<T> of(DataType<T> dtype, Shape shape, long size,
      Consumer<T> dataInitializer) {
    Tensor<T> tensor = of(dtype, shape, size);
    try {
      dataInitializer.accept(tensor.data());
      return tensor;
    } catch (Throwable t) {
      tensor.close();
      throw t;
    }
  }

  /**
   * Creates a Tensor of any type from the raw data provided by the given buffer.
   *
   * <p>Data must have been encoded into {@code data} as per the specification of the TensorFlow <a
   * href="https://www.tensorflow.org/code/tensorflow/c/c_api.h">C API</a>.
   *
   * @param <T> the tensor element type
   * @param dtype the tensor element data type
   * @param shape the tensor shape.
   * @param rawData a buffer containing the tensor raw data.
   * @throws IllegalArgumentException If the tensor datatype or shape is not compatible with the
   *     buffer
   */
  public static <T extends TType> Tensor<T> of(DataType<T> dtype, Shape shape, ByteDataBuffer rawData) {
    // The raw data buffer should be at least large enough to contain all scalar values of the tensor,
    // but could be larger as well (e.g. TString)
    if (shape.size() * dtype.byteSize() > rawData.size()) {
      throw new IllegalArgumentException("Buffer is not large enough to contain all scalar values");
    }
    Tensor<T> t = of(dtype, shape, rawData.size());
    rawData.copyTo(TensorBuffers.toBytes(t.nativeHandle()), rawData.size());
    return t;
  }

  /**
   * Returns this Tensor object with the type {@code Tensor<U>}. This method is useful when given a
   * value of type {@code Tensor<?>}.
   *
   * @param dt any supported tensor data type
   * @throws IllegalArgumentException if the actual data type of this object does not match the type
   *     {@code U}.
   */
  @SuppressWarnings("unchecked")
  public <U extends TType> Tensor<U> expect(DataType<U> dt) {
    if (!dt.equals(this.dtype)) {
      throw new IllegalArgumentException(
          "Cannot cast from tensor of " + dtype + " to tensor of " + dt);
    }
    return ((Tensor<U>) this);
  }

  /**
   * Release resources associated with the Tensor.
   *
   * <p><b>WARNING:</b>This must be invoked for all tensors that were not been produced by an eager
   * operation or memory will be leaked.
   *
   * <p>The Tensor object is no longer usable after {@code close} returns.
   */
  @Override
  public void close() {
    nativeRef.release();
  }

  /** Returns the {@link DataType} of elements stored in the Tensor. */
  public DataType<T> dataType() {
    return dtype;
  }

  /** Returns the size, in bytes, of the tensor data. */
  public long numBytes() {
    if (numBytes == null) {
      numBytes = TF_TensorByteSize(nativeRef.tensorHandle);
    }
    return numBytes;
  }

  /**
   * Returns the <a href="https://www.tensorflow.org/resources/dims_types.html#shape">shape</a> of
   * the Tensor, i.e., the sizes of each dimension.
   *
   * @return shape of this tensor
   */
  public Shape shape() {
    return shape;
  }

  /**
   * Returns the data of this tensor.
   *
   * <p>This method returns an accessor to the tensor data as an instance of {@code T}, which
   * commonly maps this data to an {@link org.tensorflow.tools.ndarray.NdArray NdArray}. Input and
   * output operations performed on the returned n-dimensional array are applied directly to the
   * tensor native memory. For example:
   *
   * <pre>{@code
   * Ops tf = Ops.create();
   * try (Tensor<TFloat32> t = TFloat32.tensorOf(Shape.of(2, 2))) {
   *   TFloat32 data = t.data();
   *
   *   StdArrays.copyTo(data, new float[][] {
   *     {1.0f, 2.0f},
   *     {3.0f, 4.0f}
   *   });
   *   assertEquals(NdArrays.vectorOf(3.0f, 4.0f), data.getFloat(1));
   *
   *   Constant<TFloat32> c = tf.val(t);
   *   assertEquals(4.0f, c.data().getFloat(1, 1));
   * }
   * }</pre>
   *
   * <p>Please refer to the documentation of the {@link org.tensorflow.tools.ndarray.NdArray NdArray}
   * classes for more information on the various techniques to read or write data in an
   * n-dimensional space using this data structure.
   *
   * @return the tensor data mapped to an n-dimensional space
   * @throws IllegalStateException if the tensor has been closed
   * @see org.tensorflow.tools.ndarray.NdArray
   */
  public T data() {
    if (data == null) {
      data = dtype.map(this);
    } else {
      nativeHandle(); // Checks that the tensor has not been released or will throw
    }
    return data;
  }

  /**
   * Returns the raw data of this tensor as a buffer of bytes.
   *
   * <p>Use this method to obtain a read-only serializable view of the tensor raw data and must be
   * used with care since there is no guard on the element boundaries. For regular input or output
   * operations, use {@link #data()}.
   *
   * @return the tensor raw data mapped to a read-only byte buffer
   * @throws IllegalStateException if the tensor has been closed
   */
  public ByteDataBuffer rawData() {
    return TensorBuffers.toBytes(nativeHandle(), true);
  }

  /** Returns a string describing the type and shape of the Tensor. */
  @Override
  public String toString() {
    return String.format("%s tensor with shape %s", dtype.toString(), shape);
  }

  /**
   * Create a Tensor object from a handle to the C TF_Tensor object.
   *
   * <p>Takes ownership of the handle.
   */
  static Tensor<?> fromHandle(TF_Tensor handle) {
    Tensor<?> t = new Tensor<>(DataTypes.fromNativeCode(dtype(handle)), Shape.of(shape(handle)));
    t.nativeRef = new NativeReference(handle);
    return t;
  }

  /**
   * Create an eager Tensor object from a handle to the C TF_Tensor object.
   *
   * <p>Takes ownership of the handle.
   */
  static Tensor<?> fromHandle(TF_Tensor handle, EagerSession session) {
    Tensor<?> t = fromHandle(handle);
    t.nativeRef.eager(session, t);
    return t;
  }

  /**
   * @return native handle to this tensor
   * @throws IllegalStateException if tensor has been closed
   */
  TF_Tensor nativeHandle() {
    return requireHandle(nativeRef.tensorHandle);
  }

  /**
   * Reference to the underlying native tensor
   *
   * <p>Tensors are commonly allocated in a `try-with-resources` statement, where they get
   * automatically released after executing the last line of the `try` block they were declared in.
   *
   * <p>They can also be attached to an eager session, where in this case their lifetime ends either
   * when this session is closed or when the Tensor instance is no longer referenced and have been
   * garbage-collected.
   *
   * <p>This helper class wraps the tensor native handle and support both situations; If an eager
   * reference to the tensor exists, it will take care of releasing the tensor at the end of its
   * life. If the tensor is being explicitly closed before this happens, it will take cake of
   * clearing its association with any eager session before cleaning up the resources.
   */
  private static class NativeReference {

    /** Attaches this reference to an eager session */
    private class EagerReference extends EagerSession.NativeReference {

      EagerReference(EagerSession session, Tensor<?> tensor) {
        super(session, tensor);
      }

      @Override
      void delete() {
        // Mark this eager reference as cleared since it has been deleted by the session
        Tensor.NativeReference.this.eagerRef = null;
        Tensor.NativeReference.this.release();
      }
    }

    NativeReference(TF_Tensor tensorHandle) {
      setTensorHandle(tensorHandle);
    }

    void eager(EagerSession session, Tensor<?> tensor) {
      if (eagerRef != null) {
        throw new IllegalStateException("The tensor is already attached to an eager session");
      }
      eagerRef = new EagerReference(session, tensor);
    }

    synchronized void release() {
      if (tensorHandle != null && !tensorHandle.isNull()) {
        // Clear any remaining eager reference to this tensor
        if (eagerRef != null) {
          eagerRef.clear();
          eagerRef = null;
        }
        Tensor.delete(tensorHandle);
        setTensorHandle(null);
      }
    }

    private TF_Tensor tensorHandle;
    private EagerReference eagerRef;

    private void setTensorHandle(TF_Tensor tensorHandle) {
      this.tensorHandle = tensorHandle;
    }
  }

  private static TF_Tensor requireHandle(TF_Tensor handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() was called on the Tensor");
    }
    return handle;
  }

  private static TF_Tensor allocate(int dtype, long[] shape, long byteSize) {
    TF_Tensor t = TF_AllocateTensor(dtype, shape, shape.length, byteSize);
    if (t == null || t.isNull()) {
      throw new IllegalStateException("unable to allocate memory for the Tensor");
    }
    return t;
  }

  private static void delete(TF_Tensor handle) {
    if (handle == null || handle.isNull()) return;
    TF_DeleteTensor(handle);
  }

  private static int dtype(TF_Tensor handle) {
    requireHandle(handle);
    return TF_TensorType(handle);
  }

  private static long[] shape(TF_Tensor handle) {
    requireHandle(handle);
    int numDims = TF_NumDims(handle);
    long[] dims = new long[numDims];
    for (int i = 0; i < numDims; ++i) {
      dims[i] = TF_Dim(handle, i);
    }
    return dims;
  }

  private NativeReference nativeRef = null;
  private final DataType<T> dtype;
  private final Shape shape;
  private T data = null;
  private Long numBytes = null;

  private Tensor(DataType<T> dtype, Shape shape) {
    this.dtype = dtype;
    this.shape = shape;
  }

  static {
    TensorFlow.init();
  }
}
