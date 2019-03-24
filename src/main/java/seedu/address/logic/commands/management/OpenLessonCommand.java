package seedu.address.logic.commands.management;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.modelmanager.ManagementModel;
import seedu.address.model.modelmanager.Model;

/**
 * This implements a {@link Command} which executes a command to 'open' a {@link Lesson} from the
 * {@code List<Lesson> lessons} loaded in memory. It requires a {@link ManagementModel}
 * to be passed into the {@link #execute(Model, CommandHistory)} command. The purpose of 'opening' a
 * lesson is to be able to edit it and manage its cards.
 */
public class OpenLessonCommand extends ManagementCommand {
    /**
     * The word a user must enter to call this command.
     */
    public static final String COMMAND_WORD = "openLesson";
    /**
     * Instructions on command usage and parameters.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the lesson identified by its index number in the numbered list"
            + " shown when the command \'listLessons\' is entered.\n"
            + "Parameter: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    /**
     * Feedback message displayed to the user upon successful execution of this command
     */
    public static final String MESSAGE_SUCCESS = "Opened lesson: %1$s";
    /**
     * The index of the lesson to be opened when {@link #execute(Model, CommandHistory)}
     * is called.
     */
    private final Index targetIndex;

    /**
     * Creates an OpenLessonCommand to delete the specified {@link Lesson}
     *
     * @param targetIndex the index of the {@link Lesson} to be opened
     */
    public OpenLessonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model which the command should operate on.
     * @param history {@code CommandHistory} which the command should operate on.
     *
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        ManagementModel mgtModel = requireManagementModel(model);

        int toOpenIndex = targetIndex.getZeroBased();

        String lessonName;

        try {
            lessonName = mgtModel.openLesson(toOpenIndex);
        } catch (IllegalArgumentException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    OpenLessonCommand.MESSAGE_USAGE), e);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, lessonName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenLessonCommand // instanceof handles nulls
                && targetIndex.getZeroBased() == ((OpenLessonCommand) other).targetIndex.getZeroBased());
    }
}
