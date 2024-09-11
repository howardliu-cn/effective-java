package cn.howardliu.tutorials.java22;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

/**
 * 一个收集器，将元素收集到固定大小的滑动窗口中。
 * 此收集器将一系列元素收集到非重叠的窗口中，每个窗口的大小为 {@code windowSize}。
 *
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-09-02
 */
public record WindowFixed<TR>(int windowSize) implements Gatherer<TR, ArrayList<TR>, List<TR>> {

    public static void main(String[] args) {
        var list = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .gather(new WindowFixed<>(3))
                .toList();
        // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
        System.out.println(list);
    }

    public WindowFixed {
        // Validate input
        if (windowSize < 1) {
            throw new IllegalArgumentException("window size must be positive");
        }
    }

    @Override
    public Supplier<ArrayList<TR>> initializer() {
        // 创建一个 ArrayList 来保存当前打开的窗口
        return () -> new ArrayList<>(windowSize);
    }

    @Override
    public Integrator<ArrayList<TR>, TR, List<TR>> integrator() {
        // 集成器在每次消费元素时被调用
        return Gatherer.Integrator.ofGreedy((window, element, downstream) -> {

            // 将元素添加到当前打开的窗口
            window.add(element);

            // 直到达到所需的窗口大小，
            // 返回 true 表示希望继续接收更多元素
            if (window.size() < windowSize) {
                return true;
            }

            // 当窗口已满时，通过创建副本关闭窗口
            var result = new ArrayList<TR>(window);

            // 清空窗口以便开始新的窗口
            window.clear();

            // 将关闭的窗口发送到下游
            return downstream.push(result);

        });
    }

    // 由于此操作本质上是顺序的，因此无法并行化，因此省略了合并器

    @Override
    public BiConsumer<ArrayList<TR>, Downstream<? super List<TR>>> finisher() {
        // 终结器在没有更多元素传递时运行
        return (window, downstream) -> {
            // 如果下游仍然接受更多元素且当前打开的窗口非空，则将其副本发送到下游
            if (!downstream.isRejecting() && !window.isEmpty()) {
                downstream.push(new ArrayList<TR>(window));
                window.clear();
            }
        };
    }
}
