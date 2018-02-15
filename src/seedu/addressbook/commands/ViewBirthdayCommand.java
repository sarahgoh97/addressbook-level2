package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

/**
 * Shows list of birthdays in a month identified using the number of the month.
 */
public class ViewBirthdayCommand extends Command{
    public static final String COMMAND_WORD = "viewbirthday";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views all birthdays in the month"
            + "identified by the number of the month (1 for January, 12 for December).\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public final int month;

    public ViewBirthdayCommand(String month) {
        this.month = Integer.parseInt(month.trim());
    }

    //taken from ListCommand
    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
