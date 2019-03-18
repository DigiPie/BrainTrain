package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.lesson.Lesson;

/**
 * Wraps all lesson data in memory.
 */
public class Lessons {
    //Static fields
    public static final String EXCEPTION_INVALID_INDEX = "Invalid index: ";

    private List<Lesson> lessons;

    public Lessons () {
        lessons = new ArrayList<>();
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Lesson getLesson(int index) {
        try {
            return lessons.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(EXCEPTION_INVALID_INDEX + index);
        }
    }

    /**
     * Removes lesson at index.
     * @param index
     */
    public void deleteLesson(int index) {
        try {
            lessons.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(EXCEPTION_INVALID_INDEX + index);
        }
    }

    /**
     * Adds a lesson to the current list.
     */
    public void addLesson(Lesson lesson) {
        requireNonNull(lesson);
        lessons.add(lesson);
    }

    public void setLesson(int index, Lesson lesson) {
        requireNonNull(lesson);
        lessons.set(index, lesson);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lessons)) {
            return false;
        }

        Lessons otherLesson = (Lessons) other;
        return otherLesson.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }
}
