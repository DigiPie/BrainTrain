package braintrain.storage;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import braintrain.commons.core.GuiSettings;
import braintrain.model.Lessons;
import braintrain.model.UserPrefs;

public class StorageManagerTest {
    private static final Path NO_VALID_FILES_FOLDER = Paths.get("src", "test", "data",
        "CsvLessonsStorageTest", "noValidFiles");

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    private StorageManager storageManager;


    @Before
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        CsvLessonsStorage lessonsStorage = new CsvLessonsStorage(getTempFilePath("data"));
        CsvLessonImportExport lessonImportExport = new CsvLessonImportExport(getTempFilePath("import_export"));
        storageManager = new StorageManager(userPrefsStorage, lessonsStorage, lessonImportExport);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.getRoot().toPath().resolve(fileName);
    }


    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void getUserPrefsFilePath() {
        JsonUserPrefsStorage expected = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        assertEquals(expected.getUserPrefsFilePath(), storageManager.getUserPrefsFilePath());
    }

    @Test
    public void lessonsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link CsvLessonsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link CsvLessonsStorage} class.
         */
        Lessons original = new Lessons();
        storageManager.setLessonsFolderPath(NO_VALID_FILES_FOLDER);
        Lessons retrieved = storageManager.readLessons().get();
        assertEquals(original, retrieved);
        retrieved = storageManager.readLessons(NO_VALID_FILES_FOLDER).get();
        assertEquals(original, retrieved);
    }
    @Test
    public void getLessonsFolderPath() {
        CsvLessonsStorage expected = new CsvLessonsStorage(getTempFilePath("data"));
        assertEquals(expected.getLessonsFolderPath(), storageManager.getLessonsFolderPath());
    }

}
