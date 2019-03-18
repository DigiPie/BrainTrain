package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.modelmanager.management.ManagementModelManager;

public class ManagementModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ManagementModelManager modelManager = new ManagementModelManager();

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
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
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
    public void getLessons_lessonsNotNull_getsLessonsList() {
        assertNotNull(modelManager.getLessons());
    }

    @Test
    public void getLesson_indexOutOfBounds_throwsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        modelManager.getLesson(0);
        modelManager.getLesson(-1);
        modelManager.getLesson(999);
    }

    @Test
    public void getLesson_validLesson_getsLesson() {
        addTestLesson();
        Assert.assertEquals(getTestLesson(), modelManager.getLesson(0));
    }

    @Test
    public void addLesson_validLesson_addsLesson() {
        addTestLesson();
        assertEquals(1, modelManager.getLessons().size());
    }

    @Test
    public void addLesson_nullLesson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.addLesson(null);
    }

    @Test
    public void setLesson_nullLesson_throwsNullPointerException() {
        addTestLesson();
        thrown.expect(NullPointerException.class);
        modelManager.setLesson(0, null);
    }

    @Test
    public void setLesson_invalidIndex_throwsIndexOutOfBoundsException() {
        thrown.expect(IndexOutOfBoundsException.class);
        modelManager.setLesson(0, getTestLesson());
    }

    @Test
    public void setLesson_validData_updatesLesson() {
        addTestLesson();
        Lesson newLesson = getTestLesson();
        newLesson.addCard(Arrays.asList("test1", "test2"));
        assertNotEquals(newLesson, getTestLesson());
        modelManager.setLesson(0, newLesson);
        Assert.assertEquals(newLesson, modelManager.getLesson(0));
    }

    @Test
    public void deleteLesson_invalidIndex_throwsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        modelManager.deleteLesson(0);
        modelManager.deleteLesson(-1);
        modelManager.deleteLesson(1);
    }

    @Test
    public void deleteLesson_validIndex_deletesLesson() {
        addTestLesson();
        assertEquals(1, modelManager.getLessons().size());
        Assert.assertEquals(getTestLesson(), modelManager.getLesson(0));
        modelManager.deleteLesson(0);
        assertEquals(0, modelManager.getLessons().size());
        thrown.expect(IllegalArgumentException.class);
        modelManager.getLesson(0);
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();
        Lessons lessons = new Lessons();

        // same values -> returns true
        modelManager = new ManagementModelManager(userPrefs, lessons);
        ManagementModelManager modelManagerCopy = new ManagementModelManager(userPrefs, lessons);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager == null);

        // different types -> returns false
        assertFalse(modelManager.equals(5));
    }
}