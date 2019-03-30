package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.LessonList;
import seedu.address.model.user.CardSrsData;
import seedu.address.model.user.User;
import seedu.address.testutil.TypicalLessonList;

public class StorageManagerTest {
    private static final Path NO_VALID_FILES_FOLDER = Paths.get("src", "test", "data",
        "CsvLessonListStorageTest", "noValidFiles");
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    private StorageManager storageManager;


    @Before
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        CsvLessonListStorage lessonListStorage = new CsvLessonListStorage(getTempFilePath("data"));
        CsvUserStorage userStorage = new CsvUserStorage(getTempFilePath("data\\user"));
        storageManager = new StorageManager(userPrefsStorage, lessonListStorage, userStorage);
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
    public void lessonListReadSave() {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link CsvLessonListStorage} class.
         * More extensive testing of LessonList saving/reading is done in {@link CsvLessonListStorage} class.
         */
        LessonList original = new LessonList();
        storageManager.setLessonListFolderPath(NO_VALID_FILES_FOLDER);
        LessonList retrieved = storageManager.readLessonList().get();
        assertEquals(original, retrieved);
        retrieved = storageManager.readLessonList(NO_VALID_FILES_FOLDER).get();
        assertEquals(original, retrieved);
        assertEquals(0, storageManager.saveLessonList(retrieved));
        assertEquals(0, storageManager.saveLessonList(retrieved, NO_VALID_FILES_FOLDER));
    }
    @Test
    public void getLessonListFolderPath() {
        CsvLessonListStorage expected = new CsvLessonListStorage(getTempFilePath("data"));
        assertEquals(expected.getLessonListFolderPath(), storageManager.getLessonListFolderPath());
    }
    @Test
    public void userReadSave() throws IOException {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link CsvUserStorage} class.
         * More extensive testing of User saving/reading is done in {@link CsvUserStorage} class.
         *
         * TODO
         */
        User original = new User();
        Path testFile = testFolder.newFile("user.csv").toPath();
        storageManager.setUserFilePath(testFile);
        assertEquals(original, storageManager.readUser().orElse(new User()));
        assertEquals(original, storageManager.readUser(testFile).orElse(new User()));
        original.addCard(new CardSrsData(1, 1, 1, Instant.now()));
        storageManager.saveUser(original);
        assertEquals(1 , storageManager.readUser().get().getCards().size());
        storageManager.saveUser(original, testFile);
        assertEquals(1 , storageManager.readUser(testFile).get().getCards().size());
    }

    @Test
    public void getUserFilePath() {
        CsvUserStorage expected = new CsvUserStorage(getTempFilePath("data\\user"));
        assertEquals(expected.getUserFilePath(), storageManager.getUserFilePath());
    }
}
