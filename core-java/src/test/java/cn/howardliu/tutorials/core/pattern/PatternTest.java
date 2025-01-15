package cn.howardliu.tutorials.core.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void givenDigits_whenMatches_thenCorrect() {
        int matches = runTest("\\d", "123");

        assertEquals(matches, 3);
    }

    @Test
    public void givenNonDigits_whenMatches_thenCorrect() {
        int matches = runTest("\\D", "a6c");

        assertEquals(matches, 2);
    }

    @Test
    public void givenWhiteSpace_whenMatches_thenCorrect() {
        int matches = runTest("\\s", "a c");

        assertEquals(matches, 1);
    }

    @Test
    public void givenNonWhiteSpace_whenMatches_thenCorrect() {
        int matches = runTest("\\S", "a c");

        assertEquals(matches, 2);
    }

    @Test
    public void givenWordCharacter_whenMatches_thenCorrect() {
        int matches = runTest("\\w", "hi!");

        assertEquals(matches, 2);
    }

    @Test
    public void givenNonWordCharacter_whenMatches_thenCorrect() {
        int matches = runTest("\\W", "hi!");

        assertEquals(matches, 1);
    }

    @Test
    public void givenZeroOrOneQuantifier_whenMatches_thenCorrect() {
        int matches = runTest("\\a?", "hi");

        assertEquals(matches, 3);
    }

    @Test
    public void givenZeroOrOneQuantifier_whenMatches_thenCorrect2() {
        int matches = runTest("\\a{0,1}", "hi");

        assertEquals(matches, 3);
    }

    @Test
    public void givenZeroOrManyQuantifier_whenMatches_thenCorrect() {
        int matches = runTest("\\a*", "hi");

        assertEquals(matches, 3);
    }

    @Test
    public void givenZeroOrManyQuantifier_whenMatches_thenCorrect2() {
        int matches = runTest("\\a{0,}", "hi");

        assertEquals(matches, 3);
    }

    @Test
    public void givenOneOrManyQuantifier_whenMatches_thenCorrect() {
        int matches = runTest("\\a+", "hi");

        assertEquals(matches, 0);
    }

    @Test
    public void givenOneOrManyQuantifier_whenMatches_thenCorrect2() {
        int matches = runTest("\\a{1,}", "hi");

        assertEquals(matches, 0);
    }

    @Test
    public void givenBraceQuantifier_whenMatches_thenCorrect() {
        int matches = runTest("a{3}", "aaaaaa");

        assertEquals(matches, 2);
    }

    @Test
    public void givenBraceQuantifier_whenFailsToMatch_thenCorrect() {
        int matches = runTest("a{3}", "aa");

        assertFalse(matches > 0);
    }

    @Test
    public void givenBraceQuantifierWithRange_whenMatches_thenCorrect() {
        int matches = runTest("a{2,3}", "aaaa");

        assertEquals(matches, 1);
    }

    @Test
    public void givenBraceQuantifierWithRange_whenMatchesLazily_thenCorrect() {
        int matches = runTest("a{2,3}?", "aaaa");

        assertEquals(matches, 2);
    }

    @Test
    public void givenCapturingGroup_whenMatches_thenCorrect() {
        int matches = runTest("(\\d\\d)", "12");

        assertEquals(matches, 1);
    }

    @Test
    public void givenCapturingGroup_whenMatches_thenCorrect2() {
        int matches = runTest("(\\d\\d)", "1212");

        assertEquals(matches, 2);
    }

    @Test
    public void givenCapturingGroup_whenMatchesWithBackReference_thenCorrect() {
        int matches = runTest("(\\d\\d)\\1", "1212");

        assertEquals(matches, 1);
    }

    @Test
    public void givenCapturingGroup_whenMatches_thenCorrect3() {
        int matches = runTest("(\\d\\d)(\\d\\d)", "1212");

        assertEquals(matches, 1);
    }

    @Test
    public void givenCapturingGroup_whenMatchesWithBackReference_thenCorrect2() {
        int matches = runTest("(\\d\\d)\\1\\1\\1", "12121212");

        assertEquals(matches, 1);
    }

    @Test
    public void givenCapturingGroupAndWrongInput_whenMatchFailsWithBackReference_thenCorrect() {
        int matches = runTest("(\\d\\d)\\1", "1213");

        assertFalse(matches > 0);
    }

    @Test
    public void givenText_whenMatchesAtBeginning_thenCorrect() {
        int matches = runTest("^dog", "dogs are friendly");

        assertTrue(matches > 0);
    }

    @Test
    public void givenTextAndWrongInput_whenMatchFailsAtBeginning_thenCorrect() {
        int matches = runTest("^dog", "are dogs are friendly?");

        assertFalse(matches > 0);
    }

    @Test
    public void givenText_whenMatchesAtEnd_thenCorrect() {
        int matches = runTest("dog$", "Man's best friend is a dog");

        assertTrue(matches > 0);
    }

    @Test
    public void givenTextAndWrongInput_whenMatchFailsAtEnd_thenCorrect() {
        int matches = runTest("dog$", "is a dog man's best friend?");

        assertFalse(matches > 0);
    }

    @Test
    public void givenText_whenMatchesAtWordBoundary_thenCorrect() {
        int matches = runTest("\\bdog\\b", "a dog is friendly");

        assertEquals(matches, 1);
    }

    @Test
    public void givenText_whenMatchesAtWordBoundary_thenCorrect2() {
        int matches = runTest("\\bdog\\b", "dog is man's best friend");

        assertEquals(matches, 1);
    }

    @Test
    public void givenWrongText_whenMatchFailsAtWordBoundary_thenCorrect() {
        int matches = runTest("\\bdog\\b", "snoop dogg is a rapper");

        assertEquals(matches, 0);
    }

    @Test
    public void givenText_whenMatchesAtWordAndNonBoundary_thenCorrect() {
        int matches = runTest("\\bdog\\B", "snoop dogg is a rapper");
        assertEquals(matches, 1);
    }

    @Test
    public void givenRegexWithoutCanonEq_whenMatchFailsOnEquivalentUnicode_thenCorrect() {
        int matches = runTest("\u00E9", "\u0065\u0301");

        assertEquals(matches, 0);
    }

    @Test
    public void givenRegexWithCanonEq_whenMatchesOnEquivalentUnicode_thenCorrect() {
        int matches = runTest("\u00E9", "\u0065\u0301", Pattern.CANON_EQ);

        assertTrue(matches > 0);
    }

    @Test
    public void givenRegexWithDefaultMatcher_whenMatchFailsOnDifferentCases_thenCorrect() {
        int matches = runTest("dog", "This is a Dog");

        assertEquals(matches, 0);
    }

    @Test
    public void givenRegexWithCaseInsensitiveMatcher_whenMatchesOnDifferentCases_thenCorrect() {
        int matches = runTest("dog", "This is a Dog", Pattern.CASE_INSENSITIVE);

        assertTrue(matches > 0);
    }

    @Test
    public void givenRegexWithEmbeddedCaseInsensitiveMatcher_whenMatchesOnDifferentCases_thenCorrect() {
        int matches = runTest("(?i)d(?-i)og", "This is a Dog");

        assertTrue(matches > 0);
    }

    @Test
    public void givenRegexWithComments_whenMatchFailsWithoutFlag_thenCorrect() {
        int matches = runTest("dog$  #check for word dog at end of text", "This is a dog");

        assertEquals(matches, 0);
    }

    @Test
    public void givenRegexWithComments_whenMatchesWithFlag_thenCorrect() {
        int matches = runTest("dog$  #check end of text", "This is a dog", Pattern.COMMENTS);

        assertTrue(matches > 0);
    }

    @Test
    public void givenRegexWithComments_whenMatchesWithEmbeddedFlag_thenCorrect() {
        int matches = runTest("(?x)dog$  #check end of text", "This is a dog");

        assertTrue(matches > 0);
    }

    @Test
    public void givenRegexWithLineTerminator_whenMatchFails_thenCorrect() {
        Pattern pattern = Pattern.compile("(.*)");
        Matcher matcher = pattern.matcher("this is a text" + System.getProperty("line.separator")
                + " continued on another line");
        matcher.find();

        assertEquals("this is a text", matcher.group(1));
    }

    @Test
    public void givenRegexWithLineTerminator_whenMatchesWithDotall_thenCorrect() {
        Pattern pattern = Pattern.compile("(.*)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher("this is a text" + System.getProperty("line.separator")
                + " continued on another line");
        matcher.find();
        assertEquals("this is a text" + System.getProperty("line.separator")
                + " continued on another line", matcher.group(1));
    }

    @Test
    public void givenRegexWithLineTerminator_whenMatchesWithEmbeddedDotall_thenCorrect() {
        Pattern pattern = Pattern.compile("(?s)(.*)");
        Matcher matcher = pattern.matcher("this is a text" + System.getProperty("line.separator")
                + " continued on another line");
        matcher.find();
        assertEquals("this is a text" + System.getProperty("line.separator")
                + " continued on another line", matcher.group(1));
    }

    @Test
    public void givenRegex_whenMatchesWithoutLiteralFlag_thenCorrect() {
        int matches = runTest("(.*)", "text");

        assertTrue(matches > 0);
    }

    @Test
    public void givenRegex_whenMatchFailsWithLiteralFlag_thenCorrect() {
        int matches = runTest("(.*)", "text", Pattern.LITERAL);

        assertEquals(matches, 0);
    }

    @Test
    public void givenRegex_whenMatchesWithLiteralFlag_thenCorrect() {
        int matches = runTest("(.*)", "text(.*)", Pattern.LITERAL);

        assertTrue(matches > 0);
    }

    @Test
    public void givenRegex_whenMatchFailsWithoutMultilineFlag_thenCorrect() {
        int matches = runTest("dog$",
                "This is a dog" + System.getProperty("line.separator") + "this is a fox");

        assertEquals(matches, 0);
    }

    @Test
    public void givenRegex_whenMatchesWithMultilineFlag_thenCorrect() {
        int matches = runTest("dog$",
                "This is a dog" + System.getProperty("line.separator") + "this is a fox",
                Pattern.MULTILINE);

        assertTrue(matches > 0);
    }

    @Test
    public void givenRegex_whenMatchesWithEmbeddedMultilineFlag_thenCorrect() {
        int matches = runTest("(?m)dog$",
                "This is a dog" + System.getProperty("line.separator") + "this is a fox");

        assertTrue(matches > 0);
    }

    @Test
    public void givenMatch_whenGetsIndices_thenCorrect() {
        Pattern pattern = Pattern.compile("dog");
        Matcher matcher = pattern.matcher("This dog is mine");
        matcher.find();

        assertEquals(5, matcher.start());
        assertEquals(8, matcher.end());
    }

    @Test
    public void whenStudyMethodsWork_thenCorrect() {
        Pattern pattern = Pattern.compile("dog");
        Matcher matcher = pattern.matcher("dogs are friendly");

        assertTrue(matcher.lookingAt());
        assertFalse(matcher.matches());
    }

    @Test
    public void whenMatchesStudyMethodWorks_thenCorrect() {
        Pattern pattern = Pattern.compile("dog");
        Matcher matcher = pattern.matcher("dog");

        assertTrue(matcher.matches());
    }

    @Test
    public void whenReplaceFirstWorks_thenCorrect() {
        Pattern pattern = Pattern.compile("dog");
        Matcher matcher = pattern.matcher("dogs are domestic animals, dogs are friendly");
        String newStr = matcher.replaceFirst("cat");

        assertEquals("cats are domestic animals, dogs are friendly", newStr);
    }

    @Test
    public void whenReplaceAllWorks_thenCorrect() {
        Pattern pattern = Pattern.compile("dog");
        Matcher matcher = pattern.matcher("dogs are domestic animals, dogs are friendly");
        String newStr = matcher.replaceAll("cat");

        assertEquals("cats are domestic animals, cats are friendly", newStr);
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

    public static int runTest(String regex, String text, int flags) {
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }
}
