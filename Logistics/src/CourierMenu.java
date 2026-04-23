import java.util.InputMismatchException;
import java.util.Scanner;

public class CourierMenu {
    private Scanner scanner = new Scanner(System.in);
    
    private Shipment[] dailyRoute = new Shipment[10];
    private int shipmentCount = 0;

    public void showMenu(Courier courier, Shipment[] globalShipments) {
        boolean exit = false;
        
        while (!exit) {
            try {
                System.out.println("\n|==================== COURIER DASHBOARD ====================|");
                System.out.println("1. View Delivery List");
                System.out.println("2. Update Package Status");
                System.out.println("3. Logout");
                System.out.print("Select an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: 
                        if (shipmentCount == 0) {
                            loadInitialRoute();
                        }
                        viewDeliveryList(); 
                        break;
                    case 2: 
                        if (shipmentCount == 0) {
                            loadInitialRoute();
                        }
                        updatePackageStatus(); 
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
                scanner.nextLine();
            }
        }
    }

    private void loadInitialRoute() {
        Package p1 = new Package("P001", 2.5, "20x20", "Electronics");
        Package p2 = new Package("P002", 1.0, "10x10", "Documents");

        dailyRoute[shipmentCount++] = new Shipment("T1001", "Express", p1);
        dailyRoute[shipmentCount++] = new Shipment("T1002", "Standard", p2);
        
        dailyRoute[0].updateStatus("Out for Delivery");
        dailyRoute[1].updateStatus("Out for Delivery");
    }

    private void viewDeliveryList() {
        System.out.println("\n|==================== DELIVERY LIST ====================|");
        if (shipmentCount == 0) {
            System.out.println("Log: No deliveries assigned for today.");
            return;
        }
        for (int i = 0; i < shipmentCount; i++) {
            System.out.println((i + 1) + ". " + dailyRoute[i].toString());
        }
        System.out.println("---------------------------");
    }

    private void updatePackageStatus() {
        System.out.print("\nEnter Tracking ID to update: ");
        String trackingId = scanner.nextLine().trim();

        Shipment foundShipment = null;
        for (int i = 0; i < shipmentCount; i++) {
            if (dailyRoute[i].getTrackingId().equalsIgnoreCase(trackingId)) {
                foundShipment = dailyRoute[i];
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
            int statusChoice = scanner.nextInt();
            scanner.nextLine();

            if (statusChoice == 1) {
                foundShipment.updateStatus("Delivered");
                System.out.println("System: Status updated to Delivered.");
            } else if (statusChoice == 2) {
                foundShipment.reschedule();
            } else {
                System.out.println("Error: Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Update aborted.");
            scanner.nextLine();
        }
    }
}