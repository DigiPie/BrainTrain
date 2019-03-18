package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.management.HistoryCommand;
import seedu.address.model.modelmanager.management.ManagementModel;
import seedu.address.model.modelmanager.management.ManagementModelManager;
import seedu.address.storage.CsvLessonsStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

public class HistoryCommandTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private CommandHistory history = new CommandHistory();
    private Storage storage;
    private ManagementModel managementModel;
    private ManagementModel expectedManagementModel;

    @Before
    public void setUp() throws Exception {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.newFile().toPath());
        CsvLessonsStorage lessonsStorage = new CsvLessonsStorage(temporaryFolder.newFolder().toPath());
        storage = new StorageManager(userPrefsStorage, lessonsStorage);
        managementModel = new ManagementModelManager(storage);
        expectedManagementModel = new ManagementModelManager(storage);
    }


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
