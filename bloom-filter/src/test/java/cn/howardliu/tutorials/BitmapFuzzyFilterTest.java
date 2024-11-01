package cn.howardliu.tutorials;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.roaringbitmap.RoaringBitmap;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-31
 */
public class BitmapFuzzyFilterTest {
    @Test
    void givenTwoRoaringBitmap_whenUsingOr_thenWillGetSetsUnion() {
        RoaringBitmap expected = RoaringBitmap.bitmapOf(1, 2, 3, 4, 5, 6, 7, 8);
        RoaringBitmap A = RoaringBitmap.bitmapOf(1, 2, 3, 4, 5);
        RoaringBitmap B = RoaringBitmap.bitmapOf(4, 5, 6, 7, 8);
        RoaringBitmap union = RoaringBitmap.or(A, B);
        assertEquals(expected, union);
    }

    @Test
    void fuzzyFilter() {
        final RoaringBitmap a = new RoaringBitmap();
        final RoaringBitmap b = new RoaringBitmap();
        final Random random = new Random(Integer.MAX_VALUE);

        final int nums = 1_000_000_000;
        for (int i = 0; i < nums; i++) {
            a.add(random.nextInt());
            b.add(random.nextInt());
        }

        final RoaringBitmap intersection = RoaringBitmap.and(a, b);
        System.out.println("重复数量：" + intersection.getLongCardinality());
    }
}
