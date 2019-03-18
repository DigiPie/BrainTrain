package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.management.DeleteLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link DeleteLessonCommand} object.
 */
public class DeleteLessonParser implements Parser<DeleteLessonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@link DeleteLessonCommand} and returns an {@link DeleteLessonCommand} object
     * for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLessonCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteLessonCommand(index);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteLessonCommand.MESSAGE_USAGE), e);
        }
    }
}
