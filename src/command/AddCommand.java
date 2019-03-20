package command;

import main.Book;
import main.Library;
import main.User;

/**
 * 21-oo
 * Created on:      2019-03-20 10:07
 * Original author: Nocturne
 */
public class AddCommand extends Command{

    public AddCommand(User commander, Library recipient) {
        super(commander, recipient);
    }

    public AddCommand(Library recipient) {
        this.recipient = recipient;
    }

    @Override
    public void execute(Object obj) throws IllegalArgumentException {
        if (!(obj instanceof Book)) {
            throw new IllegalArgumentException("A BOOK IS IN NEED!");
        }
        Book toAdd = (Book) obj;
        recipient.addBook(toAdd);
    }
}
