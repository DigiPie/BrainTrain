package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.management.HelpCommand.MESSAGE_SUCCESS;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.management.HelpCommand;
import seedu.address.model.modelmanager.management.ManagementModel;
import seedu.address.model.modelmanager.management.ManagementModelManager;
import seedu.address.storage.CsvLessonsStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

public class HelpCommandTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private Storage storage;
    private ManagementModel managementModel;
    private ManagementModel expectedManagementModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() throws Exception {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.newFile().toPath());
        CsvLessonsStorage lessonsStorage = new CsvLessonsStorage(temporaryFolder.newFolder().toPath());
        storage = new StorageManager(userPrefsStorage, lessonsStorage);
        managementModel = new ManagementModelManager(storage);
        expectedManagementModel = new ManagementModelManager(storage);
    }
    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, true, false);
        assertCommandSuccess(new HelpCommand(), managementModel, commandHistory,
                expectedCommandResult, expectedManagementModel);
    }
}
