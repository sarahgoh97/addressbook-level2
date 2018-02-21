package seedu.addressbook.common;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void elementsAreUnique() throws Exception {
        // empty list
        assertAreUnique();

        // only one object
        assertAreUnique((Object) null);
        assertAreUnique(1);
        assertAreUnique("");
        assertAreUnique("abc");

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertAreUnique(1, 2);

        // some identical objects
        assertNotUnique("abc", "abc");
        assertNotUnique("abc", "", "abc", "ABC");
        assertNotUnique("", "abc", "a", "abc");
        assertNotUnique(1, new Integer(1));
        assertNotUnique(null, 1, new Integer(1));
        assertNotUnique(null, null);
        assertNotUnique(null, "a", "b", null);
    }

    @Test
    public void isAnyNull() throws Exception {
        // empty list
        assertNoneNull();

        // only one object is null
        assertAnyNull((Object) null);
        assertAnyNull(1, null);
        assertAnyNull("", 2, null, "hello");
        assertAnyNull("abc", "hello", 1, 3, null);

        // multiple objects null
        assertAnyNull(null, null, null);
        assertAnyNull(null, null, 2, null, null);
        assertAnyNull(2, null, "groceries", null, "bananana", "yummy");

        // all objects no null
        assertNoneNull("abc", "abc");
        assertNoneNull("abc", "", "abc", "ABC");
        assertNoneNull("", "abc", "a", "abc");
        assertNoneNull(1, new Integer(1));
        assertNoneNull(1, new Integer(1));
        assertNoneNull("a", "b", 3);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertAnyNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNoneNull (Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
