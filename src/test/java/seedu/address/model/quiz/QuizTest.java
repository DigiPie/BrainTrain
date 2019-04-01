package seedu.address.model.quiz;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class QuizTest {
    private static final QuizCard QUIZCARD_1 = new QuizCard("Japan", "Tokyo", Arrays.asList("JP", "Asia"));
    private static final QuizCard QUIZCARD_2 = new QuizCard("Hungary", "Budapest");
    private static final List<QuizCard> VALID_QUIZCARD = Arrays.asList(QUIZCARD_1, QUIZCARD_2);
    private static final int VALID_QUIZCARD_SIZE = VALID_QUIZCARD.size();
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () ->
            new Quiz(null, QuizMode.LEARN));
    }

    @Test
    public void constructor_invalidQuizCard_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
            new Quiz(VALID_QUIZCARD, null));

        Assert.assertThrows(IllegalArgumentException.class, () ->
            new Quiz(Arrays.asList(new QuizCard("", "")), QuizMode.LEARN));
    }

    @Test
    public void generate() {
        List<QuizCard> expectedPreview = new ArrayList<>();
        List<QuizCard> expectedReview = new ArrayList<>();
        List<QuizCard> expectedLearn = new ArrayList<>();

        QuizCard expectedCurrentCard;
        for (int i = 0; i < VALID_QUIZCARD_SIZE; i++) {
            expectedCurrentCard = VALID_QUIZCARD.get(i);
            expectedPreview.add(new QuizCard(i, expectedCurrentCard.getQuestion(), expectedCurrentCard.getAnswer(),
                QuizMode.PREVIEW));
        }

        for (int i = 0; i < VALID_QUIZCARD_SIZE; i++) {
            expectedCurrentCard = VALID_QUIZCARD.get(i);
            expectedReview.add(new QuizCard(i, expectedCurrentCard.getQuestion(), expectedCurrentCard.getAnswer(),
                QuizMode.REVIEW));
        }

        for (int i = 0; i < VALID_QUIZCARD_SIZE; i++) {
            expectedCurrentCard = VALID_QUIZCARD.get(i);
            expectedReview.add(new QuizCard(i, expectedCurrentCard.getAnswer(), expectedCurrentCard.getQuestion(),
                QuizMode.REVIEW));
        }

        expectedLearn.addAll(expectedPreview);
        expectedLearn.addAll(expectedReview);

        // learn
        List<QuizCard> actualLearn = new Quiz(VALID_QUIZCARD, QuizMode.LEARN).generate();
        assertEquals(6, actualLearn.size());
        assertEquals(expectedLearn, actualLearn);

        // review
        List<QuizCard> actualReview = new Quiz(VALID_QUIZCARD, QuizMode.REVIEW).generate();
        assertEquals(4, actualReview.size());
        assertEquals(expectedReview, actualReview);

        // preview
        List<QuizCard> actualPreview = new Quiz(VALID_QUIZCARD, QuizMode.PREVIEW).generate();
        assertEquals(2, actualPreview.size());
        assertEquals(expectedPreview, actualPreview);

        // difficult == preview
        List<QuizCard> actualDifficult = new Quiz(VALID_QUIZCARD, QuizMode.DIFFICULT).generate();
        assertEquals(2, actualDifficult.size());
        assertEquals(expectedPreview, actualDifficult);
    }

    @Test
    public void isNextCard() {
        Quiz quiz = new Quiz(VALID_QUIZCARD, QuizMode.LEARN);
        assertTrue(quiz.hasCardLeft());

        // get all cards
        quiz.getNextCard();
        quiz.getNextCard();
        quiz.getNextCard();
        quiz.getNextCard();
        quiz.getNextCard();
        quiz.getNextCard();

        // no cards left
        assertFalse(quiz.hasCardLeft());

    }

    @Test
    public void getNextCard() {
        // ------- learn -------
        Quiz quiz = new Quiz(VALID_QUIZCARD, QuizMode.LEARN);

        // normal
        List<QuizCard> generated = quiz.generate();
        for (int i = 0; i < VALID_QUIZCARD_SIZE * 3; i++) {
            assertEquals(generated.get(i), quiz.getNextCard());
            assertEquals((i + 1) + "/" + VALID_QUIZCARD_SIZE * 3, quiz.getCurrentProgress());
        }

        // no more cards
        Assert.assertThrows(IndexOutOfBoundsException.class, () ->
            quiz.getNextCard());

        // ------- review -------
        Quiz quizReview = new Quiz(VALID_QUIZCARD, QuizMode.REVIEW);

        // normal
        List<QuizCard> generatedReview = quizReview.generate();
        for (int i = 0; i < VALID_QUIZCARD_SIZE * 2; i++) {
            assertEquals(generatedReview.get(i), quizReview.getNextCard());
            assertEquals((i + 1) + "/" + VALID_QUIZCARD_SIZE * 2, quizReview.getCurrentProgress());
        }

        // no more cards
        Assert.assertThrows(IndexOutOfBoundsException.class, () ->
            quizReview.getNextCard());

        // ------- preview -------
        Quiz quizPreview = new Quiz(VALID_QUIZCARD, QuizMode.PREVIEW);

        // normal
        List<QuizCard> generatedPreview = quizPreview.generate();
        assertEquals(generatedPreview.get(0), quizPreview.getNextCard());
        assertEquals(generatedPreview.get(1), quizPreview.getNextCard());
        assertEquals(2 + "/" + VALID_QUIZCARD_SIZE, quizPreview.getCurrentProgress());


        // no more cards
        Assert.assertThrows(IndexOutOfBoundsException.class, () ->
            quizPreview.getNextCard());
    }

    @Test
    public void getCurrentQuizCard_null_throwsNullPointerException() {
        Quiz quiz = new Quiz(VALID_QUIZCARD, QuizMode.LEARN);

        Assert.assertThrows(NullPointerException.class, () ->
            quiz.getCurrentQuizCard());
    }

    @Test
    public void getCurrentQuizCard() {
        Quiz quiz = new Quiz(VALID_QUIZCARD, QuizMode.LEARN);
        QuizCard expected = quiz.getNextCard();

        assertEquals(new QuizCard(0, "Japan", "Tokyo", QuizMode.PREVIEW), quiz.getCurrentQuizCard());
        assertEquals(expected, quiz.getCurrentQuizCard());
    }

    @Test
    public void updateTotalAttemptsAndStreak() {
        final int index = 0;
        final String correctAnswer = "Tokyo";
        final String wrongAnswer = "wrong answer";
        final QuizCard card1 = new QuizCard("Japan", "Tokyo", Arrays.asList("JP", "Asia"));
        final QuizCard card2 = new QuizCard("Hungary", "Budapest");
        final List<QuizCard> quizCards = new ArrayList<>(Arrays.asList(card1, card2));

        // ------- learn -------
        List<QuizCard> expected = quizCards;
        QuizCard expectedCard1 = expected.get(index);
        expectedCard1.updateTotalAttemptsAndStreak(expectedCard1.isCorrect(correctAnswer));

        Quiz quiz = new Quiz(quizCards, QuizMode.LEARN);
        quiz.getNextCard();

        assertTrue(quiz.updateTotalAttemptsAndStreak(index, correctAnswer));
        assertEquals(expected, quiz.getCurrentSession());
        assertEquals(1, quiz.getQuizTotalAttempts());
        assertEquals(1, quiz.getQuizTotalCorrectQuestions());

        // test wrong answer
        expectedCard1.updateTotalAttemptsAndStreak(expectedCard1.isCorrect(wrongAnswer));
        quiz.getNextCard();
        assertFalse(quiz.updateTotalAttemptsAndStreak(index, wrongAnswer));
        assertEquals(expected, quiz.getCurrentSession());
        assertEquals(2, quiz.getQuizTotalAttempts());
        assertEquals(1, quiz.getQuizTotalCorrectQuestions());

        // ------- Review -------
        Quiz quizReview = new Quiz(quizCards, QuizMode.REVIEW);
        quizReview.getNextCard();
        quizReview.updateTotalAttemptsAndStreak(index, correctAnswer);

        assertEquals(expected, quizReview.getCurrentSession());

        // ------- Preview -------
        Quiz quizPreview = new Quiz(quizCards, QuizMode.REVIEW);
        quizPreview.getNextCard();
        quizPreview.updateTotalAttemptsAndStreak(index, correctAnswer);

        assertEquals(expected, quizPreview.getCurrentSession());
    }

    @Test
    public void end() {
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(0, 1, 1, 0));
        expected.add(Arrays.asList(1, 1, 0, 0));

        // quiz just started
        Quiz quiz = new Quiz(VALID_QUIZCARD, QuizMode.LEARN);
        assertFalse(quiz.isQuizDone());

        // test 2 question
        quiz.getNextCard();
        quiz.updateTotalAttemptsAndStreak(0, "Tokyo");
        quiz.getNextCard();
        quiz.updateTotalAttemptsAndStreak(1, "wrong answer");

        assertEquals(expected, quiz.end());
        assertTrue(quiz.isQuizDone());
    }

    @Test
    public void toggleIsCardDifficult() {
        final int index = 0;
        final QuizCard card1 = new QuizCard("Japan", "Tokyo", Arrays.asList("JP", "Asia"));
        final QuizCard card2 = new QuizCard("Hungary", "Budapest");
        final List<QuizCard> quizCards = new ArrayList<>(Arrays.asList(card1, card2));

        Quiz quiz = new Quiz(quizCards, QuizMode.LEARN);
        quiz.getNextCard();

        assertTrue(quiz.toggleIsCardDifficult(index));
        assertFalse(quiz.toggleIsCardDifficult(index));
    }

    @Test
    public void equals() {
        Quiz quiz = new Quiz(VALID_QUIZCARD, QuizMode.LEARN);

        // same value -> returns true
        Quiz quizCopy = new Quiz(VALID_QUIZCARD, QuizMode.LEARN);
        assertTrue(quiz.equals(quizCopy));

        // same object -> returns true
        assertTrue(quiz.equals(quiz));

        // null -> returns false
        assertFalse(quiz == null);

        // different types -> returns false
        assertFalse(quiz.equals(5));
    }

    @Test
    public void completeFlow() {
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(0, 2, 2, 0));
        expected.add(Arrays.asList(1, 2, 1, 0));

        final QuizCard card1 = new QuizCard("Japan", "Tokyo", Arrays.asList("JP", "Asia"));
        final QuizCard card2 = new QuizCard("Hungary", "Budapest");
        final List<QuizCard> quizCards = new ArrayList<>(Arrays.asList(card1, card2));

        Quiz quiz = new Quiz(quizCards, QuizMode.LEARN);
        // Preview questions and answers
        quiz.getNextCard();
        quiz.getNextCard();

        assertTrue(quiz.getNextCard().isCorrect("Tokyo"));
        quiz.updateTotalAttemptsAndStreak(0, "Tokyo");

        assertFalse(quiz.getNextCard().isCorrect("wrong answer"));
        quiz.updateTotalAttemptsAndStreak(1, "wrong answer");

        assertTrue(quiz.getNextCard().isCorrect("Japan"));
        quiz.updateTotalAttemptsAndStreak(0, "Japan");

        assertTrue(quiz.getNextCard().isCorrect("Hungary"));
        quiz.updateTotalAttemptsAndStreak(1, "Hungary");

        assertEquals(expected, quiz.end());
    }

}
