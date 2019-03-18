package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.Lessons;

/**
 * Represents a storage for {@link Lessons}.
 */
public interface LessonsStorage {
    /**
     * Returns the folder path of the data file.
     */
    Path getLessonsFolderPath();

    /**
     * Sets the folder path of the data file.
     */
    void setLessonsFolderPath(Path folderPath);

    /**
     * Returns Lessons data as a {@link Lessons}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<Lessons> readLessons();

    /**
     * @see #getLessonsFolderPath()
     */
    Optional<Lessons> readLessons(Path folderPath);

    /**
     * Saves the given {@link Lessons} to the storage.
     * @param lessons cannot be null.
     * @return Number of lessons successfully saved.
     */
    int saveLessons(Lessons lessons);

    /**
     * @see #saveLessons(Lessons)
     */
    int saveLessons(Lessons lessons, Path filePath);
}
