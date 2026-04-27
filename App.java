import java.util.Scanner;
import java.util.InputMismatchException;

public class AdminMenu {

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
                int choice = Main.scanner.nextInt();
                Main.scanner.nextLine();

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
                Main.scanner.nextLine();
            }
        }
    }

	private void handleAssignment(Admin admin, Shipment[] shipments, User[] users) {
        System.out.print("Enter Shipment Tracking ID: ");
        String trackId = Main.scanner.nextLine();
        System.out.print("Enter Courier Username: ");
        String courierName = Main.scanner.nextLine();

        System.out.println("System: Verifying IDs...");
        
        Shipment foundShipment = Shipment.findShipment(trackId);
        if (foundShipment == null) {
            System.out.println("Error: Shipment Tracking ID not found.");
            return;
        }

        Courier foundCourier = null;
        for (User u : users) {
            if (u != null && u instanceof Courier && u.getUsername().equals(courierName)) {
                foundCourier = (Courier) u;
                break;
            }
        }

        if (foundCourier == null) {
            System.out.println("Error: Courier Username not found.");
            return;
        }

        admin.assignDriver(foundCourier, foundShipment);
        foundCourier.assignShipment(foundShipment);
        foundShipment.updateStatus("Assigned to Courier");
        Shipment.saveShipments();
        System.out.println("Log: Assignment command sent to Database.");
    }

    private void handleReschedule(Admin admin, Shipment[] shipments) {
        System.out.print("Enter Tracking ID to Reschedule: ");
        String trackId = Main.scanner.nextLine();
        Shipment s = Shipment.findShipment(trackId);
        if (s != null) {
            admin.rescheduleShipment(s);
            Shipment.saveShipments();
        } else {
            System.out.println("Error: Tracking ID not found.");
        }
        System.out.println("System: Parcel located. Resetting delivery attempts.");
    }
}