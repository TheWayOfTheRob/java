// Targeted by JavaCPP version 1.5.1: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class Delete_func_Pointer extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    Delete_func_Pointer(Pointer p) { super(p); }
    protected Delete_func_Pointer() { allocate(); }
    private native void allocate();
    public native void call(Pointer arg0);
}