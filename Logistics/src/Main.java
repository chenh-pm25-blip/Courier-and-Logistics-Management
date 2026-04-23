import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    private static User[] users;
    private static Shipment[] shipments = new Shipment[100];

    public static void main(String[] args) {
        User.initializeUserData(); 
        Shipment.initializeShipmentData();
        Vehicle.initializeVehicleData();
        
        users = User.getUserList(); 
        
        boolean exit = false;

        System.out.println("===================================================");
        System.out.println("#                                                 #");
        System.out.println("#       COURIER & LOGISTICS MANAGEMENT SYSTEM     #");
        System.out.println("#                                                 #");
        System.out.println("===================================================");

        while (!exit) {
            System.out.println("\n|==================== MAIN MENU ====================|");
            System.out.println("1. Login");
            System.out.println("2. Track Parcel");
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
        System.out.println("\n|==================== USER LOGIN ====================|");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User authenticatedUser = User.authenticate(username, password);

        if (authenticatedUser != null) {
            System.out.println("\nLogin Successful! Welcome, " + authenticatedUser.getUsername());
            
            if (authenticatedUser instanceof Admin) {
                AdminMenu menu = new AdminMenu();
                menu.showMenu((Admin) authenticatedUser, shipments, users);
            } 
            else if (authenticatedUser instanceof Sender) {
                SenderMenu menu = new SenderMenu();
                menu.showMenu((Sender) authenticatedUser, shipments); 
            } 
            else if (authenticatedUser instanceof Courier) {
                CourierMenu menu = new CourierMenu();
                menu.showMenu((Courier) authenticatedUser, shipments);
            }
        } else {
            System.out.println("Error: Invalid username or password.");
        }
    }

    private static void handleQuickTracking() {
        System.out.print("\nEnter your Unique Tracking ID: ");
        String trackId = scanner.nextLine();
        Shipment found = Shipment.findShipment(trackId);

			if (found != null) {
                System.out.println(found.getTrackingStatus());
                } else {
                	System.out.println("Error: Tracking ID not found in our system.");
                	}
    }
}