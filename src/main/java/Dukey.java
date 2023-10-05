import Tasks.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Dukey {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected String filePath = "docs/dukey.txt";

    public Dukey(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> array1 = new ArrayList<>();
            storage.fileToTaskArray(filePath, array1);
            tasks = new TaskList(array1); // Assigning the TaskList to the class-level variable
        } catch (Exception e) {
            ui.showLoadingError();
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() throws IOException {
        ui.showWelcomeMessage();
        Scanner in = new Scanner(System.in);
        String line;
    //    ArrayList<Task> tasks = new ArrayList<>();
    //    File file = new File(filePath);
        // Check if the file or directory exists, and create it if it doesn't
        storage.checkFileExists(filePath);
    /*    if (file.createNewFile()) {
            System.out.println("File created");
        } else {
            System.out.println("File already exists");
        } */
  /*      try {
            storage.fileToTaskArray(filePath, tasks); // Changed Storage to storage
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } */
        while (true) {
            line = in.nextLine();
            String[] input = Parser.parseUserInput(line);
            String command = input[0];
            switch (command) {
                case "bye":
                    Ui.showExitMessage();
                    return;
                case "list":
                    TaskList.printTaskList(tasks.tasks);
                    break;
                case "mark":
                    TaskList.markTask(line, tasks.tasks);
                    break;
                case "unmark":
                    TaskList.unmarkTask(line, tasks.tasks);
                    break;
                case "deadline":
                    String[] deadline = Parser.parseCommandInput(command, input[1]);
                    TaskList.addDeadline(deadline[0], deadline[1], tasks.tasks);
                    storage.taskArrayToFile(filePath, tasks.tasks);
                    break;
                case "event":
                    String[] event = Parser.parseCommandInput(command, input[1]);
                    TaskList.addEvent(event[0], event[1], event[2], tasks.tasks);
                    storage.taskArrayToFile(filePath, tasks.tasks);
                    break;
                case "todo":
                    String[] todo = Parser.parseCommandInput(command, input[1]);
                    TaskList.addTodo(todo[0], tasks.tasks);
                    storage.taskArrayToFile(filePath, tasks.tasks);
                    break;
                case "delete":
                    TaskList.deleteTask(line, tasks.tasks);
                    break;
                case "find":
                    storage.searchKeyword(line, tasks.tasks);
                    break;
                default:
                    if (line.trim().isEmpty()) {
                        Ui.printLine();
                        System.out.println(dukey.DukeyException.EmptyInputError());
                        Ui.printLine();
                    } else {
                        Ui.printLine();
                        System.out.println(dukey.DukeyException.EmptyInputError());
                        Ui.printLine();
                    }
            }
        }
    }
    public static void main(String[] args) {
        try {
            new Dukey("docs/dukey.txt").run();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}



