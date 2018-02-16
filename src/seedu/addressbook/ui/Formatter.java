package seedu.addressbook.ui;

import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.List;

import static seedu.addressbook.common.Messages.*;

public class Formatter {
    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    static final String LS = System.lineSeparator();

    static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Formats of a comment input line. Comment lines are silently consumed when reading user input. */
    static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    /** Formats command entered by the user*/
    static String formatCommandEntered(String fullInputLine) {
        String command = "[Command entered:" + fullInputLine + "]";
        return formatMessage(command);
    }

    /** Formats welcome message*/
    static String formatWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        return formatMessage(DIVIDER, DIVIDER, MESSAGE_WELCOME, version,
                MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE, storageFileInfo, DIVIDER);
    }

    /** Formats goodbye message*/
    static String formatGoodbyeMessage() {
        return formatMessage(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    /** Formats failed initialisation message*/
    static String formatInitFailedMessage() {
        return formatMessage(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
    }

    /** Formats message(s)*/
    static String formatMessage(String... message) {
        String formattedMessage = "";
        for (String m : message) {
            formattedMessage += LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX) + "\n";
        }
        return formattedMessage;
    }

    /** Formats list to be shown.
     * @param persons*/
    static String formatList(List<? extends ReadOnlyPerson> persons) {
        return formatMessage(formatPersonListView(persons));
    }

    /**
     * Formats into an indexed list.
     * Private contact details are hidden.
     */
    private static String formatPersonListView(List<? extends ReadOnlyPerson> persons) {
        final List<String> formattedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : persons) {
            formattedPersons.add(person.getAsTextHidePrivate());
        }
        return formatIndexedListForViewing(formattedPersons);
    }

    /** Formats a list of strings as a viewable indexed list. */
    static String formatIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

    /** Formats resultshown to user */
    static String formatResult(CommandResult result) {
        return formatMessage(result.feedbackToUser, DIVIDER);
    }

}

