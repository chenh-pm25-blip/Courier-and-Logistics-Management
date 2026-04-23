import java.util.*;

public class SenderMenu {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=== COURIER & LOGISTICS MANAGEMENT SYSTEM ===");
        System.out.println("=== Demonstrating Inheritance, Association, Composition ===\n");

        Person p = new Sender(1001, "Ahmad Faiz", "012-3456789", "No 5, Jalan SS2, PJ");
        System.out.println("*** Polymorphism Demo ***");
        System.out.println("Person reference to Sender object: " + p + "\n");

        Shipment[] shipments = new Shipment[10];

        Sender s1 = new Sender(1002, "Lim Wei", "011-1111111", "Penang");
        Sender s2 = new Sender(1003, "Devi A/P Raj", "019-2222222", "KL");

        shipments[0] = new Shipment("SHP2001", 12.5, s1, new Package("PKG5001", 2.5));
        shipments[1] = new Shipment("SHP2002", 320.0, s2, new Package("PKG5002", 5.0));

        int count = 2;

        System.out.println("*** Fixed Array Demo (2 shipments) ***");
        for (int i = 0; i < count; i++) {
            System.out.println(shipments[i]);
        }

        int choice;
        do {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Add new shipment");
            System.out.println("2. View all shipments");
            System.out.println("3. Update shipment status");
            System.out.println("4. Search shipment by ID");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (count >= shipments.length) {
                        System.out.println("Storage full!");
                        break;
                    }

                    System.out.print("Enter Shipment ID: ");
                    String sid = sc.next();

                    System.out.print("Enter Sender Name: ");
                    String name = sc.next();

                    System.out.print("Enter Distance (km): ");
                    double dist = sc.nextDouble();

                    Sender newSender = new Sender(2000 + count, name, "N/A", "N/A");
                    Package newPkg = new Package("PKG" + (6000 + count), 3.0);

                    shipments[count++] = new Shipment(sid, dist, newSender, newPkg);
                    System.out.println("Shipment added!");
                    break;

                case 2:
                    for (int i = 0; i < count; i++) {
                        System.out.println(shipments[i]);
                    }
                    break;

                case 3:
                    System.out.print("Enter Shipment ID: ");
                    String updateID = sc.next();

                    for (int i = 0; i < count; i++) {
                        if (shipments[i].shipmentID.equals(updateID)) {
                            System.out.print("Enter new status: ");
                            String newStatus = sc.next();
                            shipments[i].updateStatus(newStatus);
                            System.out.println("Updated!");
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter Shipment ID: ");
                    String searchID = sc.next();

                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (shipments[i].shipmentID.equals(searchID)) {
                            System.out.println(shipments[i]);
                            found = true;
                        }
                    }
                    if (!found) System.out.println("Not found!");
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }
}
