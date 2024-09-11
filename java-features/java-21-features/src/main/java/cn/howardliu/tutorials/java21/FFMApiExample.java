package cn.howardliu.tutorials.java21;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.Arrays;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-28
 */
public class FFMApiExample {
    public static void main(String[] args) throws Throwable {
        // 1. 在C库路径上查找名为radixsort的外部函数
        Linker linker = Linker.nativeLinker();
        SymbolLookup stdlib = linker.defaultLookup();
        final MemorySegment memorySegment = stdlib.find("radixsort").orElseThrow();
        FunctionDescriptor descriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,
                ValueLayout.JAVA_INT,
                ValueLayout.ADDRESS
        );
        MethodHandle radixsort = linker.downcallHandle(memorySegment, descriptor);

        // 下面的代码将使用这个外部函数对字符串进行排序

        // 2. 分配栈上内存来存储四个字符串
        String[] javaStrings = {"mouse", "cat", "dog", "car"};
        // 3. 使用try-with-resources来管理离堆内存的生命周期
        try (Arena offHeap = Arena.ofConfined()) {
            // 4. 分配一段离堆内存来存储四个指针
            MemorySegment pointers = offHeap.allocateArray(ValueLayout.ADDRESS, javaStrings.length);
            // 5. 将字符串从栈上内存复制到离堆内存
            for (int i = 0; i < javaStrings.length; i++) {
                MemorySegment cString = offHeap.allocateUtf8String(javaStrings[i]);
                pointers.setAtIndex(ValueLayout.ADDRESS, i, cString);
            }
            // 6. 通过调用外部函数对离堆数据进行排序
            radixsort.invoke(pointers, javaStrings.length, MemorySegment.NULL, '\0');
            // 7. 将排序后的字符串从离堆内存复制回栈上内存
            for (int i = 0; i < javaStrings.length; i++) {
                MemorySegment cString = pointers.getAtIndex(ValueLayout.ADDRESS, i);
                javaStrings[i] = cString.getUtf8String(0);
            }
        } // 8. 所有离堆内存在此处被释放

        // 验证排序结果
        assert Arrays.equals(javaStrings, new String[] {"car", "cat", "dog", "mouse"});  // true
    }
}
