import java.util.Scanner;
import java.util.InputMismatchException;

public class SenderMenu {

    public void showMenu(Sender sender, Shipment[] shipments) {
        boolean logout = false;

        while (!logout) {
            System.out.println("\n|==================== SENDER DASHBOARD ====================|");
            System.out.println("1. Create New Shipment Request");
            System.out.println("2. View Shipments");
            System.out.println("3. Track Parcel");
            System.out.println("4. Make Payment");
            System.out.println("5. Logout");
            System.out.print("Select an option: ");

            try {
                int choice = Main.scanner.nextInt();
                Main.scanner.nextLine();

                switch (choice) {
                    case 1:
                        handleCreateShipment(sender, shipments);
                        break;
                    case 2:
                        viewAllShipments(shipments);
                        break;
                    case 3:
                        handleTracking(sender);
                        break;
                    case 4:
                        System.out.print("Enter Tracking ID to pay: ");
                        String payId = Main.scanner.nextLine();
                        Shipment payShipment = Shipment.findShipment(payId);
                        if (payShipment != null) {
                            System.out.println("Shipping Fee: RM" + payShipment.getShippingFee());
                            System.out.print("Enter payment amount: ");
                            double amt = Main.scanner.nextDouble();
                            Main.scanner.nextLine();
                            sender.makePayment(payShipment, amt);
                            Shipment.saveShipments();
                        } else {
                            System.out.println("Error: Shipment not found.");
                        }
                        break;
                    case 5:
                        sender.logout();
                        logout = true;
                        break;
                    default:
                        System.out.println("Error: Invalid choice! Please select 1-5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a numeric value.");
                Main.scanner.nextLine();
            }
        }
    }

    private void handleCreateShipment(Sender sender, Shipment[] shipments) {
        int index = -1;
        for (int i = 0; i < shipments.length; i++) {
            if (shipments[i] == null) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Error: System storage is full. Cannot add more shipments.");
            return;
        }

        System.out.println("\n--- New Shipment Details ---");
        System.out.print("Enter Package Weight (kg): ");
        double weight = Main.scanner.nextDouble();
        Main.scanner.nextLine();

        System.out.print("Enter Content Type (e.g., Electronics): ");
        String content = Main.scanner.nextLine();

        System.out.print("Enter Shipping Speed (Standard/Express): ");
        String speed = Main.scanner.nextLine();

        Package newPkg = new Package("PKG-" + System.currentTimeMillis(), weight, "Unknown", content);
        
        shipments[index] = sender.createShipmentRequest(newPkg, sender.getDefaultPickupAddress(), speed);
        Shipment.saveShipments();
        
        System.out.println("Success: Shipment " + shipments[index].getTrackingId() + " has been registered.");
    }

    private void viewAllShipments(Shipment[] shipments) {
        System.out.println("\n--- Registered Shipments ---");
        boolean found = false;
        for (Shipment s : shipments) {
            if (s != null) {
                System.out.println(s.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No shipments found in the system.");
    }

    private void handleTracking(Sender sender) {
        System.out.print("Enter Tracking ID: ");
        String tid = Main.scanner.nextLine();
        System.out.println(sender.trackParcel(tid));
    }
}