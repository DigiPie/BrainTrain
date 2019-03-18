package seedu.address.model.modelmanager.management;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Lessons;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.storage.Storage;

/**
 * Represents the in-memory management of BrainTrain data.
 */
public class ManagementModelManager implements ManagementModel {
    private static final Logger logger = LogsCenter.getLogger(ManagementModelManager.class);

    private final Storage storage;
    private final Lessons lessons;
    private final UserPrefs userPrefs;
    /**
     * Initializes a ManagementModelManager with the given userPrefs.
     */
    public ManagementModelManager(ReadOnlyUserPrefs userPrefs, Storage storage) {
        super();
        requireAllNonNull(userPrefs);

        logger.fine("Initializing with user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.storage = storage;
        this.lessons = readLessons().orElse(new Lessons());
    }

    public ManagementModelManager(Storage storage) {
        this(new UserPrefs(), storage);
        this.userPrefs.setLessonsFolderPath(Paths.get(""));
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    //=========== Lessons ==================================================================================

    /**
     * Reads in lessons from file through StorageManager.
     *
     * @return
     */
    public Optional<Lessons> readLessons() {
        return storage.readLessons();
    }

    /**
     * TODO
     * @return
     */
    public int saveLessons() {
        requireAllNonNull(storage, lessons);
        return storage.saveLessons(lessons);
    }


    @Override
    public List<Lesson> getLessons() {
        return lessons.getLessons();
    }

    @Override
    public Lesson getLesson(int index) {
        Lesson lesson = lessons.getLessons().get(index);
        return lesson;
    }

    @Override
    public void addLesson(Lesson lesson) {
        lessons.addLesson(lesson);
    }

    @Override
    public void setLesson(int index, Lesson updatedLesson) {
        lessons.setLesson(index, updatedLesson);
    }

    @Override
    public void deleteLesson(int index) {
        lessons.deleteLesson(index);
    }


    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ManagementModelManager)) {
            return false;
        }

        // state check
        ManagementModelManager other = (ManagementModelManager) obj;
        return userPrefs.equals(other.userPrefs)
                && lessons.equals(other.lessons)
                && storage.equals(other.storage);
    }

}
