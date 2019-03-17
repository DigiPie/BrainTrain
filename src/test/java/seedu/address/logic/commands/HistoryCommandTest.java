package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.management.HistoryCommand;
import seedu.address.model.modelmanager.management.ManagementModel;
import seedu.address.model.modelmanager.management.ManagementModelManager;

public class HistoryCommandTest {
    private CommandHistory history = new CommandHistory();
    private ManagementModel managementModel = new ManagementModelManager();
    private ManagementModel expectedManagementModel = new ManagementModelManager();

    @Test
    public void execute() {
        assertCommandSuccess(new HistoryCommand(), managementModel, history,
                HistoryCommand.MESSAGE_NO_HISTORY, expectedManagementModel);

        String command1 = "clear";
        history.add(command1);
        assertCommandSuccess(new HistoryCommand(), managementModel, history,
                String.format(HistoryCommand.MESSAGE_SUCCESS, command1), expectedManagementModel);

        String command2 = "randomCommand";
        String command3 = "select 1";
        history.add(command2);
        history.add(command3);

        String expectedMessage = String.format(HistoryCommand.MESSAGE_SUCCESS,
                String.join("\n", command3, command2, command1));
        assertCommandSuccess(new HistoryCommand(), managementModel, history, expectedMessage, expectedManagementModel);
    }

}
