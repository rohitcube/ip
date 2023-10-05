package Tasks;

import Tasks.Task;

public class Event extends Task {
    private String from;
    private String by;

    public Event(String from, String by, String description) {
        // call by the superclass (task) constructor
        super(description);
        this.from = from;
        this.by = by;
        this.type = 'E';
    }
    // appends "[R]" to the beginning of the string.
    // Then, it calls super.toString(), ie. it calls the toString() method of the superclass
    // then adds the from and to timings
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.by + ")";
    }
}