package seedu.address.model.modelmanager;

import java.util.List;

import seedu.address.model.quiz.Quiz;
import seedu.address.model.quiz.QuizCard;
import seedu.address.model.quiz.QuizMode;
import seedu.address.model.session.Session;
import seedu.address.model.srscard.SrsCard;
import seedu.address.model.user.User;

/**
 * The API of the QuizModel component.
 */
public interface QuizModel extends Model {

    /**
     * Return mode of {@code session}.
     */
    QuizMode getMode();

    /**
     * Return card count of {@code session}.
     */
    int getCount();

    /**
     * Return name of {@code session}.
     */
    String getName();

    /**
     * Return a list of SrsCards for updating user progress.
     */
    List<SrsCard> getQuizSrsCards();

    /**
     * Sets the {@code Quiz} and {@code Session} information.
     */
    void init(Quiz quiz, Session session);

    /**
     * Returns if there is still card left in {@code Quiz}.
     */
    boolean hasCardLeft();

    /**
     * Returns the next card in line for {@code Quiz}.
     */
    QuizCard getNextCard();

    /**
     * Returns the user current progress in {@code Quiz}.
     */
    String getCurrentProgress();

    /**
     * Returns the current QuizCard in {@code Quiz}.
     */
    QuizCard getCurrentQuizCard();

    /**
     * Update the totalAttempts and streak of a specified card in the current session.
     * @param index of the current {@code QuizCard}
     * @param answer user input
     * @return true if correct
     */
    boolean updateTotalAttemptsAndStreak(int index, String answer);

    /**
     * Returns total attempts in this {@code Quiz}.
     */
    int getQuizTotalAttempts();

    /**
     * Returns the total correct questions attempted in this {code Quiz}.
     */
    int getQuizTotalCorrectQuestions();

    /**
     * Toggles between if the card labeled difficult.
     * @param index of the current {@code QuizCard}
     * @return result after toggling
     */
    boolean toggleIsCardDifficult(int index);

    /**
     * Returns list of optional of the original {@code QuizCard} with index != -1.
     */
    List<String> getOpt();

    /**
     * Returns if User is done with {@code Quiz}.
     */
    boolean isQuizDone();

    /**
     * Returns data needed by {@code Session} when {@code Quiz} end.
     */
    List<List<Integer>> end();

    /**
     * Updates user profile after quiz ends.
     * @param quizInformation from quiz.
     */
    void updateUserProfile(List<List<Integer>> quizInformation);

    /**
     * Returns the list of original {@link QuizCard} that includes streak and total attempts.
     */
    List<QuizCard> getQuizCardList();

    /**
     * Returns true to display quiz result.
     */
    boolean isResultDisplay();

    /**
     * Sets result display to display quiz result or not.
     */
    void setResultDisplay(boolean resultDisplay);

    /**
     * Returns user {@link User} from {@link ManagementModel}.
     */
    User getManagementModelUser();
}
