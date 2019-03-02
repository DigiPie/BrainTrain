package braintrain.quiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import braintrain.testutil.Assert;

public class QuizModelManagerTest {
    private static final QuizCard QUIZCARD_1 = new QuizCard("Japan", "Tokyo", Arrays.asList("JP", "Asia"));
    private static final QuizCard QUIZCARD_2 = new QuizCard("Hungary", "Budapest");
    private static final List<QuizCard> VALID_QUIZCARD = Arrays.asList(QUIZCARD_1, QUIZCARD_2);
    private static final Quiz QUIZ = new Quiz(VALID_QUIZCARD, Quiz.Mode.LEARN);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private QuizModelManager modelManager = new QuizModelManager();

    @Test
    public void getNextCard_notInitialised_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () ->
            modelManager.getNextCard());
    }

    @Test
    public void getNextCard() {
        Quiz quiz = QUIZ;
        modelManager.init(quiz);

        // get first card
        assertEquals(new QuizCard(0, QUIZCARD_1.getQuestion(), QUIZCARD_1.getAnswer()), modelManager.getNextCard());

        // get the rest
        assertTrue(modelManager.hasCardLeft());
        modelManager.getNextCard();
        modelManager.getNextCard();
        modelManager.getNextCard();

        // no cards left
        assertFalse(modelManager.hasCardLeft());
    }

    @Test
    public void getCurrentQuizCard() {
        final QuizCard card1 = new QuizCard("Japan", "Tokyo", Arrays.asList("JP", "Asia"));
        final QuizCard card2 = new QuizCard("Hungary", "Budapest");
        final List<QuizCard> quizCards = new ArrayList<>(Arrays.asList(card1, card2));

        modelManager.init(new Quiz(quizCards, Quiz.Mode.LEARN));
        QuizCard expected = modelManager.getNextCard();

        assertEquals(new QuizCard(0, "Japan", "Tokyo"), modelManager.getCurrentQuizCard());
        assertEquals(expected, modelManager.getCurrentQuizCard());
    }

    @Test
    public void isEnd() {
        assertTrue(modelManager.isEnd());

        modelManager.init(QUIZ);
        assertFalse(modelManager.isEnd());

        modelManager.end();
        assertTrue(modelManager.isEnd());
    }

    @Test
    public void end() {
        final QuizCard card1 = new QuizCard("Japan", "Tokyo", Arrays.asList("JP", "Asia"));
        final QuizCard card2 = new QuizCard("Hungary", "Budapest");
        final List<QuizCard> quizCards = new ArrayList<>(Arrays.asList(card1, card2));
        final Quiz quiz = new Quiz(quizCards, Quiz.Mode.LEARN);
        modelManager.init(quiz);

        assertTrue(modelManager.hasCardLeft());

        // before doing any question
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(0, 0, 0));
        expected.add(Arrays.asList(1, 0, 0));

        assertEquals(expected, modelManager.end());

        // after quiz end still can ask for next card, keeps track of previous entry
        assertTrue(modelManager.hasCardLeft());
        modelManager.getNextCard();
        modelManager.updateTotalAttemptsandStreak(0, "Tokyo");
        modelManager.getNextCard();
        modelManager.updateTotalAttemptsandStreak(1, "Budapest");
        modelManager.getNextCard();
        modelManager.updateTotalAttemptsandStreak(0, "Japan");
        modelManager.getNextCard();
        modelManager.updateTotalAttemptsandStreak(1, "Hungary");

        expected = new ArrayList<>();
        expected.add(Arrays.asList(0, 2, 2));
        expected.add(Arrays.asList(1, 2, 2));

        assertEquals(expected, modelManager.end());
    }
}
