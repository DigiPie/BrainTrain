package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.modelManager.managementModel.ManagementModel;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting BrainTrain as requested ...";

    @Override
    public CommandResult execute(ManagementModel managementModel, CommandHistory history) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
