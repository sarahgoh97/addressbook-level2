package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContact(String)}
 */
public class Phone extends Contact{

    public static final String EXAMPLE = "123456789";
    public static final String MESSAGE_PHONE_CONSTRAINTS = "Person phone numbers should only contain numbers";
    public static final String PHONE_VALIDATION_REGEX = "\\d+";

    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Phone(String phone, boolean isPrivate) throws IllegalValueException {
        super(MESSAGE_PHONE_CONSTRAINTS, PHONE_VALIDATION_REGEX, phone, isPrivate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && this.getValue().equals(((Phone) other).getValue())); // state check
    }
}
