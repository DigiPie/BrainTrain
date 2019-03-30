package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Quiz UI should be shown to the user. */
    private final boolean showQuiz;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /**
     * If lesson is changed, update UI and save to disk.
     */
    private final boolean isLessonListChanged;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showQuiz = false;
        this.showHelp = false;
        this.exit = false;
        this.isLessonListChanged = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showQuiz, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showQuiz = showQuiz;
        this.showHelp = showHelp;
        this.exit = exit;
        this.isLessonListChanged = false;
    }

    public CommandResult (String feedbackToUser, boolean isLessonChanged) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showQuiz = false;
        this.showHelp = false;
        this.exit = false;
        this.isLessonListChanged = isLessonChanged;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowQuiz() {
        return showQuiz;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    /**
     * If lesson is changed, update UI and save to disk.
     */
    public boolean isLessonListChanged() {
        return isLessonListChanged;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }
}
