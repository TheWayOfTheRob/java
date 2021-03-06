// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/device_filters.proto

package org.tensorflow.proto.distruntime;

/**
 * <pre>
 * Defines the device filters for tasks in a job.
 * </pre>
 *
 * Protobuf type {@code tensorflow.JobDeviceFilters}
 */
public  final class JobDeviceFilters extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tensorflow.JobDeviceFilters)
    JobDeviceFiltersOrBuilder {
private static final long serialVersionUID = 0L;
  // Use JobDeviceFilters.newBuilder() to construct.
  private JobDeviceFilters(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private JobDeviceFilters() {
    name_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new JobDeviceFilters();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private JobDeviceFilters(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              tasks_ = com.google.protobuf.MapField.newMapField(
                  TasksDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000001;
            }
            com.google.protobuf.MapEntry<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters>
            tasks__ = input.readMessage(
                TasksDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            tasks_.getMutableMap().put(
                tasks__.getKey(), tasks__.getValue());
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.proto.distruntime.DeviceFiltersProtos.internal_static_tensorflow_JobDeviceFilters_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @java.lang.Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 2:
        return internalGetTasks();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.proto.distruntime.DeviceFiltersProtos.internal_static_tensorflow_JobDeviceFilters_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.proto.distruntime.JobDeviceFilters.class, org.tensorflow.proto.distruntime.JobDeviceFilters.Builder.class);
  }

  public static final int NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object name_;
  /**
   * <pre>
   * The name of this job.
   * </pre>
   *
   * <code>string name = 1;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * The name of this job.
   * </pre>
   *
   * <code>string name = 1;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TASKS_FIELD_NUMBER = 2;
  private static final class TasksDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters>newDefaultInstance(
                org.tensorflow.proto.distruntime.DeviceFiltersProtos.internal_static_tensorflow_JobDeviceFilters_TasksEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.INT32,
                0,
                com.google.protobuf.WireFormat.FieldType.MESSAGE,
                org.tensorflow.proto.distruntime.TaskDeviceFilters.getDefaultInstance());
  }
  private com.google.protobuf.MapField<
      java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> tasks_;
  private com.google.protobuf.MapField<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters>
  internalGetTasks() {
    if (tasks_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          TasksDefaultEntryHolder.defaultEntry);
    }
    return tasks_;
  }

  public int getTasksCount() {
    return internalGetTasks().getMap().size();
  }
  /**
   * <pre>
   * Mapping from task ID to task device filters.
   * </pre>
   *
   * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
   */

  public boolean containsTasks(
      int key) {
    
    return internalGetTasks().getMap().containsKey(key);
  }
  /**
   * Use {@link #getTasksMap()} instead.
   */
  @java.lang.Deprecated
  public java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> getTasks() {
    return getTasksMap();
  }
  /**
   * <pre>
   * Mapping from task ID to task device filters.
   * </pre>
   *
   * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
   */

  public java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> getTasksMap() {
    return internalGetTasks().getMap();
  }
  /**
   * <pre>
   * Mapping from task ID to task device filters.
   * </pre>
   *
   * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
   */

  public org.tensorflow.proto.distruntime.TaskDeviceFilters getTasksOrDefault(
      int key,
      org.tensorflow.proto.distruntime.TaskDeviceFilters defaultValue) {
    
    java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> map =
        internalGetTasks().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <pre>
   * Mapping from task ID to task device filters.
   * </pre>
   *
   * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
   */

  public org.tensorflow.proto.distruntime.TaskDeviceFilters getTasksOrThrow(
      int key) {
    
    java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> map =
        internalGetTasks().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
    }
    com.google.protobuf.GeneratedMessageV3
      .serializeIntegerMapTo(
        output,
        internalGetTasks(),
        TasksDefaultEntryHolder.defaultEntry,
        2);
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
    }
    for (java.util.Map.Entry<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> entry
         : internalGetTasks().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters>
      tasks__ = TasksDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, tasks__);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.tensorflow.proto.distruntime.JobDeviceFilters)) {
      return super.equals(obj);
    }
    org.tensorflow.proto.distruntime.JobDeviceFilters other = (org.tensorflow.proto.distruntime.JobDeviceFilters) obj;

    if (!getName()
        .equals(other.getName())) return false;
    if (!internalGetTasks().equals(
        other.internalGetTasks())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    if (!internalGetTasks().getMap().isEmpty()) {
      hash = (37 * hash) + TASKS_FIELD_NUMBER;
      hash = (53 * hash) + internalGetTasks().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.distruntime.JobDeviceFilters parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.tensorflow.proto.distruntime.JobDeviceFilters prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Defines the device filters for tasks in a job.
   * </pre>
   *
   * Protobuf type {@code tensorflow.JobDeviceFilters}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.JobDeviceFilters)
      org.tensorflow.proto.distruntime.JobDeviceFiltersOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.proto.distruntime.DeviceFiltersProtos.internal_static_tensorflow_JobDeviceFilters_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetTasks();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetMutableTasks();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.proto.distruntime.DeviceFiltersProtos.internal_static_tensorflow_JobDeviceFilters_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.proto.distruntime.JobDeviceFilters.class, org.tensorflow.proto.distruntime.JobDeviceFilters.Builder.class);
    }

    // Construct using org.tensorflow.proto.distruntime.JobDeviceFilters.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      name_ = "";

      internalGetMutableTasks().clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.proto.distruntime.DeviceFiltersProtos.internal_static_tensorflow_JobDeviceFilters_descriptor;
    }

    @java.lang.Override
    public org.tensorflow.proto.distruntime.JobDeviceFilters getDefaultInstanceForType() {
      return org.tensorflow.proto.distruntime.JobDeviceFilters.getDefaultInstance();
    }

    @java.lang.Override
    public org.tensorflow.proto.distruntime.JobDeviceFilters build() {
      org.tensorflow.proto.distruntime.JobDeviceFilters result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.tensorflow.proto.distruntime.JobDeviceFilters buildPartial() {
      org.tensorflow.proto.distruntime.JobDeviceFilters result = new org.tensorflow.proto.distruntime.JobDeviceFilters(this);
      int from_bitField0_ = bitField0_;
      result.name_ = name_;
      result.tasks_ = internalGetTasks();
      result.tasks_.makeImmutable();
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.tensorflow.proto.distruntime.JobDeviceFilters) {
        return mergeFrom((org.tensorflow.proto.distruntime.JobDeviceFilters)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.proto.distruntime.JobDeviceFilters other) {
      if (other == org.tensorflow.proto.distruntime.JobDeviceFilters.getDefaultInstance()) return this;
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      internalGetMutableTasks().mergeFrom(
          other.internalGetTasks());
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.tensorflow.proto.distruntime.JobDeviceFilters parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.tensorflow.proto.distruntime.JobDeviceFilters) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object name_ = "";
    /**
     * <pre>
     * The name of this job.
     * </pre>
     *
     * <code>string name = 1;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * The name of this job.
     * </pre>
     *
     * <code>string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * The name of this job.
     * </pre>
     *
     * <code>string name = 1;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The name of this job.
     * </pre>
     *
     * <code>string name = 1;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The name of this job.
     * </pre>
     *
     * <code>string name = 1;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.MapField<
        java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> tasks_;
    private com.google.protobuf.MapField<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters>
    internalGetTasks() {
      if (tasks_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            TasksDefaultEntryHolder.defaultEntry);
      }
      return tasks_;
    }
    private com.google.protobuf.MapField<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters>
    internalGetMutableTasks() {
      onChanged();;
      if (tasks_ == null) {
        tasks_ = com.google.protobuf.MapField.newMapField(
            TasksDefaultEntryHolder.defaultEntry);
      }
      if (!tasks_.isMutable()) {
        tasks_ = tasks_.copy();
      }
      return tasks_;
    }

    public int getTasksCount() {
      return internalGetTasks().getMap().size();
    }
    /**
     * <pre>
     * Mapping from task ID to task device filters.
     * </pre>
     *
     * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
     */

    public boolean containsTasks(
        int key) {
      
      return internalGetTasks().getMap().containsKey(key);
    }
    /**
     * Use {@link #getTasksMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> getTasks() {
      return getTasksMap();
    }
    /**
     * <pre>
     * Mapping from task ID to task device filters.
     * </pre>
     *
     * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
     */

    public java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> getTasksMap() {
      return internalGetTasks().getMap();
    }
    /**
     * <pre>
     * Mapping from task ID to task device filters.
     * </pre>
     *
     * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
     */

    public org.tensorflow.proto.distruntime.TaskDeviceFilters getTasksOrDefault(
        int key,
        org.tensorflow.proto.distruntime.TaskDeviceFilters defaultValue) {
      
      java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> map =
          internalGetTasks().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <pre>
     * Mapping from task ID to task device filters.
     * </pre>
     *
     * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
     */

    public org.tensorflow.proto.distruntime.TaskDeviceFilters getTasksOrThrow(
        int key) {
      
      java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> map =
          internalGetTasks().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearTasks() {
      internalGetMutableTasks().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <pre>
     * Mapping from task ID to task device filters.
     * </pre>
     *
     * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
     */

    public Builder removeTasks(
        int key) {
      
      internalGetMutableTasks().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters>
    getMutableTasks() {
      return internalGetMutableTasks().getMutableMap();
    }
    /**
     * <pre>
     * Mapping from task ID to task device filters.
     * </pre>
     *
     * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
     */
    public Builder putTasks(
        int key,
        org.tensorflow.proto.distruntime.TaskDeviceFilters value) {
      
      if (value == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableTasks().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <pre>
     * Mapping from task ID to task device filters.
     * </pre>
     *
     * <code>map&lt;int32, .tensorflow.TaskDeviceFilters&gt; tasks = 2;</code>
     */

    public Builder putAllTasks(
        java.util.Map<java.lang.Integer, org.tensorflow.proto.distruntime.TaskDeviceFilters> values) {
      internalGetMutableTasks().getMutableMap()
          .putAll(values);
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tensorflow.JobDeviceFilters)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.JobDeviceFilters)
  private static final org.tensorflow.proto.distruntime.JobDeviceFilters DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.proto.distruntime.JobDeviceFilters();
  }

  public static org.tensorflow.proto.distruntime.JobDeviceFilters getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<JobDeviceFilters>
      PARSER = new com.google.protobuf.AbstractParser<JobDeviceFilters>() {
    @java.lang.Override
    public JobDeviceFilters parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new JobDeviceFilters(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<JobDeviceFilters> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<JobDeviceFilters> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.tensorflow.proto.distruntime.JobDeviceFilters getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

