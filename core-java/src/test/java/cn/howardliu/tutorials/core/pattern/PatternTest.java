package cn.howardliu.tutorials.core.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-12
 */
public class PatternTest {
    @Test
    public void givenText_whenSimpleRegexMatches_thenCorrect() {
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher("foo");

        assertTrue(matcher.find());
    }

    @Test
    public void givenText_whenSimpleRegexMatchesTwice_thenCorrect() {
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher("foofoo");
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }

        assertEquals(matches, 2);
    }

    @Test
    public void givenText_whenMatchesWithDotMetach_thenCorrect() {
        int matches = runTest(".", "foo");

        assertTrue(matches > 0);
    }

    @Test
    public void givenRepeatedText_whenMatchesOnceWithDotMetach_thenCorrect() {
        int matches = runTest("foo.", "foofoo");

        assertEquals(matches, 1);
    }

    @Test
    public void givenORSet_whenMatchesAny_thenCorrect() {
        int matches = runTest("[abc]", "b");

        assertEquals(matches, 1);
    }

    @Test
    public void givenORSet_whenMatchesAnyAndAll_thenCorrect() {
        int matches = runTest("[abc]", "cab");

        assertEquals(matches, 3);
    }

    @Test
    public void givenORSet_whenMatchesAllCombinations_thenCorrect() {
        int matches = runTest("[bcr]at", "bat cat rat");

        assertEquals(matches, 3);
    }

    @Test
    public void givenNORSet_whenMatchesNon_thenCorrect() {
        int matches = runTest("[^abc]", "g");

        assertTrue(matches > 0);
    }

    @Test
    public void givenNORSet_whenMatchesAllExceptElements_thenCorrect() {
        int matches = runTest("[^bcr]at", "sat mat eat");

        assertTrue(matches > 0);
    }

    @Test
    public void givenUpperCaseRange_whenMatchesUpperCase_thenCorrect() {
        int matches = runTest("[A-Z]", "Two Uppercase alphabets 34 overall");

        assertEquals(matches, 2);
    }

    @Test
    public void givenLowerCaseRange_whenMatchesLowerCase_thenCorrect() {
        int matches = runTest("[a-z]", "Two Uppercase alphabets 34 overall");

        assertEquals(matches, 26);
    }

    @Test
    public void givenBothLowerAndUpperCaseRange_whenMatchesAllLetters_thenCorrect() {
        int matches = runTest("[a-zA-Z]", "Two Uppercase alphabets 34 overall");

        assertEquals(matches, 28);
    }

    @Test
    public void givenNumberRange_whenMatchesAccurately_thenCorrect() {
        int matches = runTest("[1-5]", "Two Uppercase alphabets 34 overall");

        assertEquals(matches, 2);
    }

    @Test
    public void givenNumberRange_whenMatchesAccurately_thenCorrect2() {
        int matches = runTest("3[0-5]", "Two Uppercase alphabets 34 overall");

        assertEquals(matches, 1);
    }

    @Test
    public void givenTwoSets_whenMatchesUnion_thenCorrect() {
        int matches = runTest("[1-3[7-9]]", "123456789");

        assertEquals(matches, 6);
    }

    @Test
    public void givenTwoSets_whenMatchesIntersection_thenCorrect() {
        int matches = runTest("[1-6&&[3-9]]", "123456789");

        assertEquals(matches, 4);
    }

    @Test
    public void givenSetWithSubtraction_whenMatchesAccurately_thenCorrect() {
int matches = runTest("[0-9&&[^2468]]", "123456789");

assertEquals(matches, 5);
    }

    public static int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }
}
