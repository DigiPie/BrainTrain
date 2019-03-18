package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.lesson.Lesson;

public class LessonsTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Lessons lessons = new Lessons();

    private void addTestLesson() {
        lessons.addLesson(getTestLesson());
    }

    private Lesson getTestLesson() {
        ArrayList<String> testFields = new ArrayList<>();
        testFields.add("test 1");
        testFields.add("test 2");
        Lesson lesson = new Lesson("test", 2, testFields);

        return lesson;
    }
    @Test
    public void getLessons_lessonsNotNull_getsLessonsList() {
        assertNotNull(lessons.getLessons());
    }

    @Test
    public void getLesson_indexOutOfBounds_throwsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        lessons.getLesson(0);
        lessons.getLesson(-1);
        lessons.getLesson(999);
    }

    @Test
    public void getLesson_validLesson_getsLesson() {
        addTestLesson();
        assertEquals(getTestLesson(), lessons.getLesson(0));
    }

    @Test
    public void addLesson_validLesson_addsLesson() {
        addTestLesson();
        assertEquals(1, lessons.getLessons().size());
    }

    @Test
    public void addLesson_nullLesson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        lessons.addLesson(null);
    }

    @Test
    public void setLesson_nullLesson_throwsNullPointerException() {
        addTestLesson();
        thrown.expect(NullPointerException.class);
        lessons.setLesson(0, null);
    }

    @Test
    public void setLesson_invalidIndex_throwsIndexOutOfBoundsException() {
        thrown.expect(IndexOutOfBoundsException.class);
        lessons.setLesson(0, getTestLesson());
    }

    @Test
    public void setLesson_validData_updatesLesson() {
        addTestLesson();
        Lesson newLesson = getTestLesson();
        newLesson.addCard(Arrays.asList("test1", "test2"));
        assertNotEquals(newLesson, getTestLesson());
        lessons.setLesson(0, newLesson);
        assertEquals(newLesson, lessons.getLesson(0));
    }

    @Test
    public void deleteLesson_invalidIndex_throwsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        lessons.deleteLesson(0);
        lessons.deleteLesson(-1);
        lessons.deleteLesson(1);
    }

    @Test
    public void deleteLesson_validIndex_deletesLesson() {
        addTestLesson();
        assertEquals(1, lessons.getLessons().size());
        assertEquals(getTestLesson(), lessons.getLesson(0));
        lessons.deleteLesson(0);
        assertEquals(0, lessons.getLessons().size());
        thrown.expect(IllegalArgumentException.class);
        lessons.getLesson(0);
    }

    @Test
    public void equals() {
        Lessons diffLessons = new Lessons();
        diffLessons.addLesson(getTestLesson());
        assertEquals(lessons, lessons);
        assertNotEquals(lessons, diffLessons);
        assertNotEquals(lessons, new Object());
        Lessons diffLessonsCopy = new Lessons();
        for (Lesson l : diffLessons.getLessons()) {
            diffLessonsCopy.addLesson(l);
        }
        assertEquals(diffLessons, diffLessonsCopy);
    }
}
