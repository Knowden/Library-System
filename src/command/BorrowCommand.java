package command;

import main.*;

/**
 * 21-oo
 * Created on:      2019-03-20 08:55
 * Original author: Nocturne
 */
public class BorrowCommand extends Command {

    public BorrowCommand(User commander, Library recipient) {
        super(commander, recipient);
    }

    @Override
    public void execute(Object obj) throws IllegalArgumentException {
        if (!(obj instanceof Book)) {
            throw new IllegalArgumentException("A BOOK IS IN NEED!");
        }
        Book toBorrow = (Book) obj;
        Record borrowRecord = recipient.borrowBook(commander, toBorrow, Server.today);
        commander.borrowBook(borrowRecord);
    }
}
