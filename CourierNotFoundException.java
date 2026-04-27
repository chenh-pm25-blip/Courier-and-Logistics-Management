import java.util.InputMismatchException;
import java.util.Scanner;

public class CourierMenu {

    public void showMenu(Courier courier, Shipment[] globalShipments) {
        boolean exit = false;
        
        while (!exit) {
            try {
                System.out.println("\n|==================== COURIER DASHBOARD ====================|");
                System.out.println("1. View Delivery List");
                System.out.println("2. Update Package Status");
                System.out.println("3. Logout");
                System.out.print("Select an option: ");
                
                int choice = Main.scanner.nextInt();
                Main.scanner.nextLine();

                switch (choice) {
                    case 1: 
                        viewDeliveryList(courier); 
                        break;
                    case 2: 
                        updatePackageStatus(courier); 
                        break;
                    case 3: 
                        exit = true; 
                        courier.logout(); 
                        break;
                    default: 
                        System.out.println("Error: Invalid choice. Please select 1-3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError: Invalid input type. Please enter a numeric choice.");
                Main.scanner.nextLine();
            }
        }
    }

    private void viewDeliveryList(Courier courier) {
        System.out.println("\n|==================== DELIVERY LIST ====================|");
        int count = courier.getAssignedCount();
        if (count == 0) {
            System.out.println("Log: No deliveries assigned for today.");
            return;
        }
        Shipment[] shipments = courier.getAssignedShipments();
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + shipments[i].toString());
        }
        System.out.println("---------------------------");
    }

    private void updatePackageStatus(Courier courier) {
        System.out.print("\nEnter Tracking ID to update: ");
        String trackingId = Main.scanner.nextLine().trim();

        Shipment foundShipment = null;
        int count = courier.getAssignedCount();
        Shipment[] shipments = courier.getAssignedShipments();
        for (int i = 0; i < count; i++) {
            if (shipments[i] != null && shipments[i].getTrackingId().equalsIgnoreCase(trackingId)) {
                foundShipment = shipments[i];
                break;
            }
        }

        if (foundShipment == null) {
            System.out.println("Error: Tracking ID '" + trackingId + "' not found.");
            return;
        }

        System.out.println("\nCurrent Status: " + foundShipment.getTrackingStatus());
        System.out.println("1. Mark as 'Delivered'");
        System.out.println("2. Mark as 'Failed / Reschedule'");
        System.out.print("Update Selection: ");
        
        try {
            int statusChoice = Main.scanner.nextInt();
            Main.scanner.nextLine();

            if (statusChoice == 1) {
                foundShipment.updateStatus("Delivered");
                System.out.println("System: Status updated to Delivered.");
                Shipment.saveShipments();
            } else if (statusChoice == 2) {
                foundShipment.reschedule();
                Shipment.saveShipments();
            } else {
                System.out.println("Error: Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Update aborted.");
            Main.scanner.nextLine();
        }
    }
}