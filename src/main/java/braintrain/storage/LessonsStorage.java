package braintrain.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import braintrain.model.Lessons;

/**
 * Represents a storage for {@link braintrain.model.Lessons}.
 */
public interface LessonsStorage {
    /**
     * Returns the folder path of the data file.
     */
    Path getLessonsFolderPath();

    /**
     * Returns Lessons data as a {@link Lessons}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<Lessons> readLessons() throws IOException;

    /**
     * @see #getLessonsFolderPath()
     */
    Optional<Lessons> readLessons(Path folderPath) throws IOException;

    /**
     * Saves the given {@link Lessons} to the storage.
     * @param lessons cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLessons(Lessons lessons) throws IOException;

    /**
     * @see #saveLessons(Lessons)
     */
    void saveLessons(Lessons lessons, Path filePath) throws IOException;
}
