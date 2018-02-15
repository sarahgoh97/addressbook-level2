package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.Birthday;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.List;

/**
 * Shows list of birthdays in a month identified using the number of the month.
 */
public class ViewBirthdayCommand extends Command{
    public static final String COMMAND_WORD = "viewbirthday";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views all birthdays in the month "
            + "identified by the number of the month (1 for January, 12 for December).\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final int month;

    public ViewBirthdayCommand(int month) {
        this.month = month;
    }

    //taken from ListCommand
    @Override
    public CommandResult execute() throws UniquePersonList.DuplicatePersonException {
        if (!Birthday.isValidMonth(month)) {
            return new CommandResult(Messages.MESSAGE_INVALID_MONTH);
        }
        if (!Birthday.months.contains(month)) {
            return new CommandResult(Messages.MESSAGE_NO_PERSONS_BIRTHDAY_MONTH);
        }
        List<ReadOnlyPerson> birthdayPersons = addressBook.getBirthdayPersons(month).immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(birthdayPersons), birthdayPersons);
    }
}
