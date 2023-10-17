import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.Iterator;

public class MainApp {
    int taskId = 0;
    String taskTitle;
    boolean status;

    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        MainApp MainApp = new MainApp();
        try (Scanner scanner = new Scanner(System.in)) {
            MainApp.userInfo(scanner);
            MainApp.AddTask(scanner);
            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    public void userInfo(Scanner scanner) {

        User user = new User();
        System.out.println("Hello, welcome to 'MainApp' app ");
        try {
            String name;
            while (true) {

                System.out.println("Enter your name: ");
                name = scanner.nextLine();
                if (!containsDigit(name)) {
                    break;
                }
                System.out.println("Invalid name. Name cannot contain digits. Please try again.");
            }
            user.setName(name);

            int age;
            while (true) {
                System.out.println("Enter your age: ");
                String ageString = scanner.nextLine();
                if (ageString.matches("\\d+")) {
                    age = Integer.parseInt(ageString);
                    break;
                }
                System.out.println("Invalid age. Age must be a positive integer. Please try again.");
            }
            user.setAge(age);

            System.out.println("Hello, " + user.getName() + "! You are " + user.getAge() + " years old.");

        } catch (

        Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void AddTask(Scanner scanner) {
        while (true) {
            System.out.println("\nDo you want to add a new task? Enter y/n: ");
            String userResponse = scanner.nextLine();

            if (userResponse.equals("y")) {
                this.taskId = readTaskId();
                this.taskTitle = readTaskTitle();

                Task task = new Task(status, taskId, taskTitle);
                tasks.add(task);

                System.out.println("You've added task with id: " + taskId + " \nTitle is: " + taskTitle);

                performUserChoice();
                break;

            } else if (userResponse.equals("n")) {
                performUserChoice();
                break;
            } else {
                System.out.println("Invaild input ");
            }

        }

    }

    public String readTaskTitle() {
        String taskTitle;
        while (true) {
            System.out.print("Enter the task title: ");
            taskTitle = scanner.nextLine();

            if (!taskTitle.isEmpty()) {
                break;
            } else {
                System.out.println("Invalid input. Task title cannot be empty.");
            }
        }
        return taskTitle;
    }

    public int readTaskId() {
        int taskId;
        while (true) {
            System.out.println("Enter an id of task: ");
            if (scanner.hasNextInt()) {
                taskId = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for task ID.");
                scanner.nextLine();
            }
        }
        return taskId;
    }

    public void displayAllTasks() {

        System.out.println("\nList Size is: " + tasks.size());

        for (Task task : tasks) {
            System.out.println(
                    "Task ID: " + task.getId() + "\nTitle: " + task.getTask_title() + " \nStatus: " + task.isStatus());
        }
        performUserChoice();

    }

    public void removeTask(int taskId) {

        boolean correctID = false;

        while (!correctID) {
            System.out.println("Enter an ID of the task you want to delete: ");
            int chosenID = scanner.nextInt();
            scanner.nextLine();

            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.getId() == chosenID) {
                    iterator.remove();
                    System.out.println("Task with ID " + chosenID + " has been deleted.");
                    correctID = true;
                    break;
                }
            }

            if (!correctID) {
                System.out.println("Task with ID " + chosenID + " not found. No changes were made.");
                System.out.println("Do you want to try another ID? (yes/no): ");
                String tryAgain = scanner.nextLine();
                if (!tryAgain.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }

        performUserChoice();
    }

    public void markAsCompletedTask(int taskId) {

        boolean correctID = false;

        while (!correctID) {
            int changedId = -1;
            try {
                System.out.println("Enter an ID of the task you want to change the status of: ");
                changedId = scanner.nextInt();
                scanner.nextLine();

                for (Task task : tasks) {
                    if (task.getId() == changedId) {
                        task.setStatus(true);
                        System.out.println("Task with ID " + changedId + " has been marked as completed.");
                        correctID = true;
                        performUserChoice();
                        break;
                    }
                }

                if (!correctID) {
                    System.out.println("Task with ID " + changedId + " not found. No changes were made.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid task ID.");
                scanner.nextLine();
            }
        }
    }

    private boolean containsDigit(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private void performUserChoice() {
        boolean validChoice = false;

        while (!validChoice) {
            System.out
                    .println(
                            " \nChoose an action: \n'display tasks'\n'complete task'\n'add task'\n'delete task'\n'quit app': ");
            String choice = scanner.nextLine();

            if (choice.equals("display tasks")) {
                displayAllTasks();
                validChoice = true;
            } else if (choice.equals("complete task")) {
                markAsCompletedTask(taskId);
                validChoice = true;

            } else if (choice.equals("add task")) {
                AddTask(scanner);
                validChoice = true;

            } else if (choice.equals("delete task")) {
                removeTask(taskId);
                validChoice = true;
            } else if (choice.equals("quit app")) {
                quitApp();
                validChoice = true;

            } else {
                System.out.println("\nInvalid choice,  Choose an action: \\n" + //
                        "'display tasks'\\n" + //
                        "'complete task'\\n" + //
                        "'add task'\\n" + //
                        "'delete task'\\n" + //
                        "'quit app':");
            }
        }
    }

    public void quitApp() {

        boolean validInput = false;

        while (!validInput) {
            System.out.println("\nDo you want to quit the app? Enter y or n: ");
            String quitChoice = scanner.nextLine().toLowerCase();
            if (quitChoice.equals("y")) {
                validInput = true;
                System.out.println("\nExiting the app \nHave a productive day!");
                break;

            } else if (quitChoice.equals("n")) {
                System.out.println("\nContinuing the app");
                validInput = true;
                performUserChoice();
            } else {
                System.out.println("\nInvalid input. Please enter y or n.");
            }
        }

    }
}
