package info.kgeorgiy.ja.фамилия.student;

import info.kgeorgiy.java.advanced.student.Group;
import info.kgeorgiy.java.advanced.student.GroupName;
import info.kgeorgiy.java.advanced.student.GroupQuery;
import info.kgeorgiy.java.advanced.student.Student;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

/**
 * Tests for hard version
 * of <a href="https://www.kgeorgiy.info/courses/java-advanced/homeworks.html#homework-student">Student</a> homework
 * for <a href="https://www.kgeorgiy.info/courses/java-advanced/">Java Advanced</a> course.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OldGroupQueryTest extends OldStudentQueryTest implements GroupQuery {
    protected final GroupQuery db = createCUT();

    @Test
    public void test21_testGetGroupsByName() {
        test(this::getGroupsByName, db::getGroupsByName);
    }

    @Test
    public void test22_testGetGroupsById() {
        test(this::getGroupsById, db::getGroupsById);
    }

    @Test
    public void test23_testGetLargestGroup() {
        test(this::getLargestGroup, db::getLargestGroup);
    }

    @Test
    public void test24_testGetLargestGroupByFirstName() {
        test(this::getLargestGroupFirstName, db::getLargestGroupFirstName);
    }

    // Reference implementation follows
    // This implementation is intentionally poorly-written and contains a lot of copy-and-paste

    @Override
    public List<Group> getGroupsByName(final Collection<Student> students) {
        final Map<GroupName, Group> groups = new TreeMap<>();
        for (final Student student : students) {
            groups.computeIfAbsent(student.getGroup(), group -> new Group(group, findStudentsByGroup(students, group)));
        }
        return List.copyOf(groups.values());
    }

    @Override
    public List<Group> getGroupsById(final Collection<Student> students) {
        final Map<GroupName, Group> groups = new TreeMap<>();
        for (final Student student : students) {
            groups.computeIfAbsent(student.getGroup(), group -> {
                final ArrayList<Student> result = new ArrayList<>(students);
                result.removeIf(s -> !s.getGroup().equals(group));
                Collections.sort(result);
                return new Group(group, result);
            });
        }
        return List.copyOf(groups.values());
    }

    @Override
    public GroupName getLargestGroup(final Collection<Student> students) {
        if (students.size() == 0) {
            return null;
        }

        int maxSize = -1;
        GroupName name = GroupName.M3234;
        for (final Group group : getGroupsByName(students)) {
            if (maxSize < group.getStudents().size() || maxSize == group.getStudents().size() && name.compareTo(group.getName()) < 0) {
                maxSize = group.getStudents().size();
                name = group.getName();
            }
        }
        return name;
    }

    @Override
    public GroupName getLargestGroupFirstName(final Collection<Student> students) {
        if (students.size() == 0) {
            return null;
        }

        int maxSize = -1;
        GroupName name = GroupName.M3239;
        for (final Group group : getGroupsByName(students)) {
            final Set<String> firstNames = new HashSet<>();
            group.getStudents().forEach(student -> firstNames.add(student.getFirstName()));
            if (maxSize < firstNames.size() || maxSize == firstNames.size() && name.compareTo(group.getName()) > 0) {
                maxSize = firstNames.size();
                name = group.getName();
            }
        }
        return name;
    }
}
