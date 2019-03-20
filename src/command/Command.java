package command;

import main.Library;
import main.User;

/**
 * 21-oo
 * Created on:      2019-03-20 08:55
 * Original author: Nocturne
 */
abstract public class Command {
    protected User commander;
    protected Library recipient;

    public Command() {
        super();
    }

    public Command(User commander, Library recipient) {
        this.commander = commander;
        this.recipient = recipient;
    }

    public abstract void execute(Object obj);
}
