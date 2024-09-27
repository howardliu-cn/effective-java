package cn.howardliu.tutorials.core;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-09-27
 */
@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@Warmup(iterations = 1)
@Measurement(iterations = 2, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Threads(4)
@State(Scope.Benchmark)
@OperationsPerInvocation
public class StringAggJmhTest {
    private String a = "Hello, ";
    private String b = "World!";

    @Test
    public void runBenchmarks() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .result("StringAggJmhTest.json")
                .resultFormat(ResultFormatType.JSON)
                .build();


        new Runner(options).run();
    }

    @Benchmark
    public void testStringAdd(Blackhole blackhole) {
        String s = a + b;
        blackhole.consume(s);
    }

    @Benchmark
    public void testStringBuilderAdd(Blackhole blackhole) {
        StringBuilder s = new StringBuilder();
        s.append(a).append(b);
        blackhole.consume(s.toString());
    }

    @Benchmark
    public void testStringBufferAdd(Blackhole blackhole) {
        StringBuffer s = new StringBuffer();
        s.append(a).append(b);
        blackhole.consume(s.toString());
    }

    @Benchmark
    public void testStringConcatAdd(Blackhole blackhole) {
        String s = a.concat(b);
        blackhole.consume(s);
    }
}
