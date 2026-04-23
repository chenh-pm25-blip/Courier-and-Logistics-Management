import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User.initializeUserData(); 
        
        boolean exit = false;

        System.out.println("==============================================");
        System.out.println("      COURIER & LOGISTICS MANAGEMENT SYSTEM   ");
        System.out.println("==============================================");

        while (!exit) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. System Login (Staff & Customers)");
            System.out.println("2. Quick Track Parcel (No login required)");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        handleLogin();
                        break;
                    case 2:
                        handleQuickTracking();
                        break;
                    case 3:
                        System.out.println("Thank you for using the system. Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Error: Invalid choice. Please select 1-3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Numeric input required for menu selection.");
                scanner.nextLine();
            }
        }
    }

    private static void handleLogin() {
        System.out.println("\n--- USER LOGIN ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User authenticatedUser = User.authenticate(username, password);

        if (authenticatedUser != null) {
            System.out.println("\nLogin Successful! Welcome, " + authenticatedUser.getUsername());
            
            if (authenticatedUser instanceof Admin) {
                AdminMenu menu = new AdminMenu();
                menu.showMenu((Admin) authenticatedUser);
            } 
            else if (authenticatedUser instanceof Sender) {
                SenderMenu menu = new SenderMenu();
                menu.showMenu((Sender) authenticatedUser, null); 
            } 
            else if (authenticatedUser instanceof Courier) {
                CourierMenu menu = new CourierMenu();
                menu.showMenu((Courier) authenticatedUser);
            }
        } else {
            System.out.println("Error: Invalid username or password.");
        }
    }

    private static void handleQuickTracking() {
        System.out.print("\nEnter your Unique Tracking ID: ");
        String trackingId = scanner.nextLine();
        
        System.out.println("Searching for Parcel [" + trackingId + "]...");
        System.out.println("Status: In Transit - Expected delivery by end of day.");
    }
}