package braintrain.logic.commands;

import static braintrain.logic.commands.CommandTestUtil.assertCommandSuccess;
import static braintrain.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.Test;

import braintrain.logic.CommandHistory;
import braintrain.model.Model;
import braintrain.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, commandHistory, expectedCommandResult, expectedModel);
    }
}
