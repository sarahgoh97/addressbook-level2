package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.ArrayList;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */

public class Birthday {
    public static final String EXAMPLE = "09-08-1965";
    public static final String MESSAGE_BIRTHDAY_CONSTRAINTS =
                "Person birthdays should be separated by '-' and in the format DD-MM-YYYY";
    public static final String BIRTHDAY_VALIDATION_REGEX = "\\d{2}\\-\\d{2}\\-\\d{4}";

    public final String value;
    private boolean isPrivate;
    public static ArrayList<Integer> months = new ArrayList<Integer>(12);
    public final int month;

    /**
     * Validates given birthday.
     *
     * @throws IllegalValueException if given birthday string is invalid.
     */
    public Birthday(String birthday, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        String trimmedBirthday = birthday.trim();
        if (!isValidBirthday(trimmedBirthday)) {
            throw new IllegalValueException(MESSAGE_BIRTHDAY_CONSTRAINTS);
        }
        this.value = trimmedBirthday;
        String month = trimmedBirthday.substring(3,5);
        this.month = Integer.parseInt(month);
        if (!months.contains(this.month)) {
            months.add(Integer.parseInt(month));
        }
    }

    /**
     * Returns true if the given string is a valid person birthday.
     */
    public static boolean isValidBirthday(String test) {
            return test.matches(BIRTHDAY_VALIDATION_REGEX);
    }
    @Override
    public String toString() {
            return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && this.value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }
}
