package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.quiz.QuizStartCommand;
import seedu.address.model.modelmanager.management.ManagementModel;
import seedu.address.model.modelmanager.management.ManagementModelManager;
import seedu.address.model.session.Session;
import seedu.address.testutil.SessionBuilder;

public class QuizStartCommandTest {

    private static final CommandHistory commandHistory = new CommandHistory();

    /*@Test
    public void execute_success() throws Exception {
        ManagementModel managementModel = new ManagementModelManager();
        Session session = new SessionBuilder().build();
        CommandResult commandResult = new QuizStartCommand(session).execute(managementModel, commandHistory);

        assertEquals(QuizStartCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }*/

    @Test
    public void executeActual_success() {
        // this hardcoded values matched QuizStartCommand
        // when session is implemented then this will change to session instead
        /*final QuizCard card1 = new QuizCard("Japan", "Tokyo");
        final QuizCard card2 = new QuizCard("Hungary", "Budapest");
        final QuizCard card3 = new QuizCard("Christmas Island", "The Settlement");
        final QuizCard card4 = new QuizCard("中国", "北京");
        final List<QuizCard> quizCards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4));
        final Quiz quiz = new Quiz(quizCards, Quiz.Mode.LEARN);

        QuizModelManager expectedModel = new QuizModelManager();
        expectedModel.init(quiz);
        expectedModel.getNextCard();
        CommandResult expectedCommandResult = new QuizStartCommand().executeActual(expectedModel, commandHistory);

        QuizModel actualModel = new QuizModelManager();
        QuizStartCommand quizStartCommand = new QuizStartCommand();

        CommandHistory expectedCommandHistory = new CommandHistory(commandHistory);
        CommandResult result = quizStartCommand.executeActual(actualModel, commandHistory);
        assertEquals(expectedCommandResult, result);
        assertEquals(expectedModel, actualModel);
        assertEquals(expectedCommandHistory, commandHistory);*/
    }
}
