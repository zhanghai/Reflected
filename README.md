# Reflected

A Java library for easy-to-use reflection.

## Why Reflected?

- Thread-safe one-time retrieval of reflected objects.
- One line to reflect a method/field along with its class.
- A single exception type to handle (or ignore) for reflective operations.

## Integration

```gradle
dependencies {
    implementation 'me.zhanghai.java.reflected:library:1.0.0'
}
```

## Usage

Create an instance of `ReflectedClass`, `ReflectedConstructor`, `ReflectedField` or `ReflectdMethod` to access a class, constructor, field or method via reflection. `.class` constant, `ReflectedClass` instance and `String` for class name can all be used in place where a class is expected. If you want to handle a reflection failure, catch for `ReflectedException`.

For example:

```java
public class ReflectedExamples {

    // Using reflection to access a field.

    private static final ReflectedField<ErrnoException> sFunctionNameField = new ReflectedField<>(
            ErrnoException.class, "functionName");

    @NonNull
    public static String getFunctionName(@NonNull ErrnoException errnoException) {
        return sFunctionNameField.getObject(errnoException);
    }


    // Using reflection to access a method.

    @NonNull
    private static final ReflectedMethod<StorageManager> sGetVolumeListMethod =
            new ReflectedMethod<>(StorageManager.class, "getVolumeList");

    @NonNull
    public static StorageVolume[] getStorageVolumes(@NonNull StorageManager storageManager) {
        return sGetVolumeListMethod.invoke(storageManager);
    }


    // Using a string for the declaring class of a method.

    private static final ReflectedMethod<?> sNewFileChannelMethod = new ReflectedMethod<>(
            "java.nio.NioUtils", "newFileChannel", Closeable.class, FileDescriptor.class,
            int.class);

    @NonNull
    public static FileChannel newFileChannel(@NonNull Closeable ioObject,
                                             @NonNull FileDescriptor fd, int mode) {
        return sNewFileChannelMethod.invoke(null, ioObject, fd, mode);
    }


    // Sharing a ReflectedClass instance for the declaring class of methods.

    @NonNull
    private static final ReflectedClass<?> sSELinuxClass = new ReflectedClass<>(
            "android.os.SELinux");

    @NonNull
    private static final ReflectedMethod<?> sIsSELinuxEnabledMethod = new ReflectedMethod<>(
            sSELinuxClass, "isSELinuxEnabled");

    @NonNull
    private static final ReflectedMethod<?> sIsSELinuxEnforcedMethod = new ReflectedMethod<>(
            sSELinuxClass, "isSELinuxEnforced");

    public static boolean isSELinuxEnabled() {
        return sIsSELinuxEnabledMethod.invoke(null);
    }

    public static boolean isSELinuxEnforced() {
        return sIsSELinuxEnforcedMethod.invoke(null);
    }
}
```

## License

    Copyright 2018 Hai Zhang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
