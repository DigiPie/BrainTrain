package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.modelmanager.management.ManagementModelManager;
import seedu.address.storage.CsvLessonsStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

public class ManagementModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private Storage storage;
    private ManagementModelManager modelManager;

    @Before
    public void setUp() throws Exception {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.newFile().toPath());
        CsvLessonsStorage lessonsStorage = new CsvLessonsStorage(temporaryFolder.newFolder().toPath());
        storage = new StorageManager(userPrefsStorage, lessonsStorage);
        modelManager = new ManagementModelManager(storage);
    }

    private void addTestLesson() {
        modelManager.addLesson(getTestLesson());
    }

    private Lesson getTestLesson() {
        ArrayList<String> testFields = new ArrayList<>();
        testFields.add("test 1");
        testFields.add("test 2");
        Lesson lesson = new Lesson("test", 2, testFields);

        return lesson;
    }

    @Test
    public void constructor() {
        UserPrefs expectedUserPrefs = new UserPrefs();
        expectedUserPrefs.setLessonsFolderPath(Paths.get(""));
        assertEquals(expectedUserPrefs, modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Lessons().getLessons(), modelManager.getLessons());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.setUserPrefs(null);
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.setGuiSettings(null);
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }


    @Test
    public void lessonsReadSaveEdit() throws Exception {
        /*
         * Note: This is an integration test that verifies the ManagementModelManager is properly wired to the
         * {@link Lessons} class and {@link StorageManager}.
         * More extensive testing of Lessons editing is done in {@link Lessons} class, and Lessons saving/editing
         * in the {@link StorageManager} class.
         */
        Lessons expectedLessons = modelManager.readLessons().get();
        modelManager.saveLessons();
        Lessons actualLessons = modelManager.readLessons().get();
        assertEquals(expectedLessons, actualLessons);
        modelManager.addLesson(getTestLesson());
        modelManager.saveLessons();
        actualLessons = modelManager.readLessons().get();
        assertEquals(getTestLesson(), actualLessons.getLesson(0));
        assertEquals(getTestLesson(), modelManager.getLesson(0));
        Lesson lesson = getTestLesson();
        lesson.setName("Test2");

        modelManager.setLesson(0, lesson);
        assertEquals(lesson, modelManager.getLesson(0));

        modelManager.deleteLesson(0);
        assertEquals(0, modelManager.getLessons().size());
    }
    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();
        Lessons lessons = new Lessons();

        // same values -> returns true
        modelManager = new ManagementModelManager(userPrefs, storage);
        ManagementModelManager modelManagerCopy = new ManagementModelManager(userPrefs, storage);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager == null);

        // different types -> returns false
        assertFalse(modelManager.equals(5));
    }
}
