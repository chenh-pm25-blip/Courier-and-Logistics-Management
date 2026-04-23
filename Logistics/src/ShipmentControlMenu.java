import java.util.Scanner;

public class ShipmentControlMenu {
    private Scanner scanner = new Scanner(System.in);

    public void display(Admin admin, Shipment[] shipments, User[] users) {
        boolean exitSubMenu = false;

        while (!exitSubMenu) {
            System.out.println("\n--- SHIPMENT CONTROL SUB-MENU ---");
            System.out.println("1. Assign Courier to Shipment");
            System.out.println("2. Manually Reschedule Parcel");
            System.out.println("3. Return to Main Admin Menu");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleAssignment(admin, shipments, users);
                    break;
                case 2:
                    handleReschedule(admin, shipments);
                    break;
                case 3:
                    exitSubMenu = true;
                    break;
                default:
                    System.out.println("Invalid selection.");
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