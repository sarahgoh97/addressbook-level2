package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, some unit, 117417";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS =
            "Person addresses require a block number, a street name, a unit number and a 6-digit postal code number " +
                    "in this format: a/BLOCK, STREET, UNIT, POSTALCODE";
    private static final String ADDRESS_VALIDATION_REGEX = "\\d+,\\s.+,\\s.+,\\s(\\d{6}$)";
    private static final String COMMA = ", ";

    public final String value;
    private boolean isPrivate;
    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        } else {
            breakUpAddress(address);
        }
        this.value = block.getBlock() + COMMA + street.getStreet() + COMMA + unit.getUnit() + COMMA + postalCode.getPostalCode();
    }

    /**
     * Breaking up the address into its attributes: Block, Street, Unit and PostalCode
     * @param address
     */
    private void breakUpAddress(String address) {
        int firstCommaIndex = address.indexOf(COMMA);
        block = new Block(address.substring(0,firstCommaIndex));
        int secondCommaIndex = address.indexOf(COMMA, firstCommaIndex+1);
        street = new Street(address.substring(firstCommaIndex+2, secondCommaIndex));
        int thirdCommaIndex = address.indexOf(COMMA, secondCommaIndex+1);
        unit = new Unit(address.substring(secondCommaIndex+2, thirdCommaIndex));
        postalCode = new PostalCode(address.substring(thirdCommaIndex+2));
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
/**
 * Represents a Person's address's block number in the address book.
 */
class Block {
    private int blockNumber;

    public Block(String blockEntered) {
        blockNumber = (int) Integer.parseInt(blockEntered);
    }

    public int getBlock() {
        return blockNumber;
    }
}
/**
 * Represents a Person's address's street name in the address book.
 */
class Street {
    private String streetName;

    public Street(String streetEntered) {
        streetName = streetEntered;
    }

    public String getStreet() {
        return streetName;
    }
}
/**
 * Represents a Person's address's unit in the address book.
 */
class Unit {
    private String unitName;

    public Unit(String unitEntered) {
        unitName = unitEntered;
    }

    public String getUnit() {
        return unitName;
    }
}
/**
 * Represents a Person's address's postal code in the address book.
 */
class PostalCode {
    private int postalCodeNumber;

    public PostalCode(String postalCodeEntered) {
        postalCodeNumber = (int) Integer.parseInt(postalCodeEntered);
    }

    public int getPostalCode() {
        return postalCodeNumber;
    }
}

