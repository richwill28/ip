package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /** Initializes UI, storage, and task list. */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(TaskList.deserialize(storage.loadData()));
        } catch (DukeException | IOException e) {
            ui.printMessage(e.getMessage());
            taskList = new TaskList();
            storage.createNewData(ui);
        }
    }

    /** Starts the main functionality of Duke. */
    public void startDuke() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userResponse = ui.readUserResponse();
                Command command = Parser.parse(userResponse);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    /** Runs Duke. */
    public void run() {
        ui.printGreeting();
        this.startDuke();
        ui.printGoodbye();
    }

    /**
     * Starts an instance of Duke.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
