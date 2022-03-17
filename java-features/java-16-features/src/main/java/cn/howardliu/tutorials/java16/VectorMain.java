package cn.howardliu.tutorials.java16;

import java.util.Arrays;

import jdk.incubator.vector.IntVector;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/3/16 22:35
 */
public class VectorMain {
    public static void main(String[] args) {
        final int[] a = {1, 2, 3, 4};
        final int[] b = {5, 6, 7, 8};
        final int[] c = new int[3];

        IntVector vectorA = IntVector.fromArray(IntVector.SPECIES_128, a, 0);
        IntVector vectorB = IntVector.fromArray(IntVector.SPECIES_128, b, 0);
        IntVector vectorC = vectorA.mul(vectorB);
        vectorC.intoArray(c, 0);

        System.out.println(Arrays.toString(c));
    }
}
