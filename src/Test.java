import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Please, enter any integer(or 'exit' to quit the app): ");
                String userChoice = scanner.nextLine();

                if (userChoice.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the program. Goodbye!");
                    break; // Exit the loop and end the program);
                }

                int number = Integer.parseInt(userChoice);

                String s = Integer.toString(number);
                for (int i = 0; i < s.length(); i++) {
                    System.out.println(s.charAt(i));
                  
                }
            } catch (NumberFormatException e) {
                // Handle the exception if user input cannot be parsed as an integer
                System.out.println("Invalid input. Please enter a valid integer or 'exit' to quit.");
            }
        }
       scanner.close();  
    }
    
}
