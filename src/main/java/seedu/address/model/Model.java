package seedu.address.model;

import java.util.List;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lesson.Lesson;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);


    /**
     * Gets the lesson by index.
     */
    Lesson getLesson(int index);

    /**
     * Gets the entire list of lessons.
     */
    List<Lesson> getLessons();

    /**
     * Adds the lesson.
     */
    void addLesson(Lesson lesson);

    /**
     * Updates the lesson at the given index.
     */
    void setLesson(int index, Lesson updatedLesson);

    /**
     * Removes the lesson at the given index from memory, and deletes its corresponding file.
     * @param index w
     */
    void deleteLesson(int index);
}
