package cn.howardliu.tutorials.java17;

import static java.util.stream.Collectors.toList;

import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/4/12 08:30
 */
class VectorTest {
    @Test
    void test() {
        float[] a = initArray(100);
        float[] b = initArray(100);
        float[] c = new float[100];
        float[] d = new float[100];

        newVectorComputation(a, b, c);
        commonVectorComputation(a, b, d);

        for (int i = 0; i < a.length; i++) {
            Assertions.assertEquals(c[i], d[i]);
        }
    }

    private float[] initArray(int size) {
        final var values = new float[size];
        final var pseudoInts = getPseudoInts("Xoshiro256PlusPlus", size)
                .mapToObj(Float::valueOf)
                .collect(toList());

        for (int i = 0; i < pseudoInts.size(); i++) {
            values[i] = pseudoInts.get(i);
        }

        return values;
    }

    public void newVectorComputation(float[] a, float[] b, float[] c) {
        final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

        for (var i = 0; i < a.length; i += SPECIES.length()) {
            var m = SPECIES.indexInRange(i, a.length);
            var va = FloatVector.fromArray(SPECIES, a, i, m);
            var vb = FloatVector.fromArray(SPECIES, b, i, m);
            var vc = va.mul(vb);
            vc.intoArray(c, i, m);
        }
    }


    public void commonVectorComputation(float[] a, float[] b, float[] c) {
        for (var i = 0; i < a.length; i++) {
            c[i] = a[i] * b[i];
        }
    }

    public IntStream getPseudoInts(String algorithm, int streamSize) {
        // returns an IntStream with size @streamSize of random numbers generated using the @algorithm
        // where the lower bound is 0 and the upper is 100 (exclusive)
        return RandomGeneratorFactory.of(algorithm)
                .create()
                .ints(streamSize, 0, 100);
    }
}
