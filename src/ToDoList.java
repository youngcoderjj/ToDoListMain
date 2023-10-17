import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.Iterator;

public class ToDoList {
    int taskId = 0;
    String taskTitle;
    boolean status;

    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        ToDoList toDoList = new ToDoList();
        try (Scanner scanner = new Scanner(System.in)) {
            toDoList.userInfo(scanner);
            toDoList.yesNoQuestion(scanner);
            // toDoList.addTask();
            toDoList.quitApp();
            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    public void userInfo(Scanner scanner) {

        UserToDoList user = new UserToDoList();
        
            try {
                String name;
                while (true) {
                    System.out.println("Hello, welcome to 'ToDoList' app ");

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

    

    private void yesNoQuestion(Scanner scanner) {
        while (true) {
            System.out.println("Do you want to add a new task? Enter y/n: ");
            String userResponse = scanner.nextLine();

            if (userResponse.equals("y")) {
                this.taskId = readTaskId();
                this.taskTitle = readTaskTitle();

                Task task = new Task(status, taskId, taskTitle);
                tasks.add(task);

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
            // scanner.nextLine();

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
                scanner.nextLine(); // Consume the newline character left in the buffer
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for task ID.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        return taskId;
    }

    public void displayAllTasks() {
        System.out.println("\nAll Tasks:");
        System.out.println("\nAll Tasks: " + tasks.size());

        for (Task task : tasks) {
            System.out.println(
                    "Task ID: " + task.getId() + ", Title: " + task.getTask_title() + " Status: " + task.isStatus());
        }
    }

    public void removeTask(int taskId) {

        boolean correctID = false;

        while (!correctID) {
            System.out.println("Enter an ID of the task you want to delete: ");
            int chosenID = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left in the buffer

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
            int changedId = -1; // Initialize to an invalid value
            try {
                System.out.println("Enter an ID of the task you want to change the status of: ");
                changedId = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character left in the buffer

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
                scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
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
                            "Do you want to display all tasks or mark as completed? Enter 'display' or 'complete' or 'add task' or 'quit' or 'delete': ");
            String choice = scanner.nextLine();

            if (choice.equals("display")) {
                displayAllTasks();
                validChoice = true;
            } else if (choice.equals("complete")) {
                markAsCompletedTask(taskId);
                validChoice = true;
            } else if (choice.equals("quit")) {
                quitApp();
                validChoice = true;
            } else if (choice.equals("add task")) {
                yesNoQuestion();
                validChoice = true;

            } else if (choice.equals("delete")) {
                removeTask(taskId);
                validChoice = true; // Break the loop if a valid choice is entered

            } else {
                System.out.println("Invalid choice, type 'display' or 'complete'.");
            }
        }
    }

    public void quitApp() {

        boolean validInput = false;

        while (!validInput) {
            System.out.println("Do you want to quit the app? Enter y or n: ");
            String quitChoice = scanner.nextLine().toLowerCase(); // Convert the input to lowercase for case-insensitive
                                                                  // comparison

            if (quitChoice.equals("y")) {
                System.out.println("Exiting the app");
                validInput = true; // Set validInput to true to exit the loop
                break;
            } else if (quitChoice.equals("n")) {
                System.out.println("Continuing the app");
                validInput = true; // Set validInput to true to exit the loop
                performUserChoice();
            } else {
                System.out.println("Invalid input. Please enter y or n.");
            }
        }

    }
}
