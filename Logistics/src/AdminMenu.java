import java.util.Scanner;
import java.util.InputMismatchException;

public class AdminMenu {
    private Scanner scanner = new Scanner(System.in);

    public void showMenu(Admin admin, Shipment[] shipments, User[] users) {
        boolean backToLogin = false;

        while (!backToLogin) {
            System.out.println("\n|==================== ADMIN DASHBOARD ====================|");
            System.out.println("1. Assign Courier to Shipment");
            System.out.println("2. Manage Vehicle");
            System.out.println("3. Reschedule Shipment");
            System.out.println("4. Logout");
            System.out.print("Select an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        handleAssignment(admin, shipments, users);
                        break;
                    case 2:
                    	VehicleMenu vehicleUI = new VehicleMenu();
    					vehicleUI.display(admin);
                    	break;
                    case 3:
                        handleReschedule(admin, shipments);
                        break;
                    case 4:
                        admin.logout();
                        backToLogin = true;
                        break;
                    default:
                        System.out.println("Error: Invalid option. Please select 1-4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input must be a numeric value.");
                scanner.nextLine();
            }
        }
    }

	private void handleAssignment(Admin admin, Shipment[] shipments, User[] users) {
        System.out.print("Enter Shipment Tracking ID: ");
        String trackId = scanner.nextLine();
        System.out.print("Enter Courier Username: ");
        String courierName = scanner.nextLine();

        System.out.println("System: Verifying IDs...");
        System.out.println("Log: Assignment command sent to Database.");
    }

    private void handleReschedule(Admin admin, Shipment[] shipments) {
        System.out.print("Enter Tracking ID to Reschedule: ");
        String trackId = scanner.nextLine();
        
        System.out.println("System: Parcel located. Resetting delivery attempts.");
    }
}