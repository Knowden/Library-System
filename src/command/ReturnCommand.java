package command;

import main.Book;
import main.Library;
import main.Record;
import main.User;

/**
 * 21-oo
 * Created on:      2019-03-20 08:56
 * Original author: Nocturne
 */
public class ReturnCommand extends Command{

    public ReturnCommand(User commander, Library recipient) {
        super(commander, recipient);
    }

    @Override
    public void execute(Object obj) throws IllegalAccessError {
        if (!(obj instanceof Book)) {
            throw new IllegalAccessError("BOOK IS NEED!");
        }
        Book returnBook = (Book) obj;
        Record borrowRecord = commander.getRecordByBook(returnBook);
        recipient.returnBook(borrowRecord);
        commander.returnBook(borrowRecord);
    }
}
