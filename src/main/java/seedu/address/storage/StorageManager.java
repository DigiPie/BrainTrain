package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Lessons;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.user.User;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private UserPrefsStorage userPrefsStorage;
    private LessonsStorage lessonsStorage;
    private LessonImportExport lessonImportExport;


    public StorageManager(UserPrefsStorage userPrefsStorage,
                          LessonsStorage lessonsStorage,
                          LessonImportExport lessonImportExport) {
        super();
        this.userPrefsStorage = userPrefsStorage;
        this.lessonsStorage = lessonsStorage;
        this.lessonImportExport = lessonImportExport;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ Lessons methods ==============================

    @Override
    public Path getLessonsFolderPath() {
        return lessonsStorage.getLessonsFolderPath();
    }

    @Override
    public void setLessonsFolderPath(Path folderPath) {
        lessonsStorage.setLessonsFolderPath(folderPath);
    }

    @Override
    public Optional<Lessons> readLessons() throws IOException {
        return lessonsStorage.readLessons();
    }

    @Override
    public Optional<Lessons> readLessons(Path filePath) throws IOException {
        return lessonsStorage.readLessons(filePath);
    }

    @Override
    public int saveLessons(Lessons lessons) throws IOException {
        return lessonsStorage.saveLessons(lessons);
    }

    @Override
    public int saveLessons(Lessons lessons, Path filePath) throws IOException {
        return lessonsStorage.saveLessons(lessons, filePath);
    }

    @Override
    public Path getImportExportFilePath() {
        return lessonImportExport.getImportExportFilePath();
    }

    @Override
    public Optional<Lesson> importLesson(Path filePath) throws IOException {
        return Optional.empty();
    }

    @Override
    public void exportLesson(Lesson lesson, Path filePath) throws IOException {

    }

    @Override
    public Optional<User> importUser(Path filePath) throws IOException {
        return Optional.empty();
    }

    @Override
    public void exportUser(User user, Path filePath) throws IOException {

    }


}
