import java.util.Scanner;

public class VehicleMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();

        int choice;

        do {
            System.out.println("\n=== COURIER SYSTEM ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicles");
            System.out.println("3. Delete Vehicle");
            System.out.println("4. Add Maintenance Record");
            System.out.println("5. View Maintenance Record");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Vehicle ID: ");
                        String id = sc.nextLine();

                        System.out.print("Type: ");
                        String type = sc.nextLine();

                        System.out.print("Status: ");
                        String status = sc.nextLine();

                        admin.addVehicle(new Vehicle(id, type, status));
                        break;

                    case 2:
                        admin.viewVehicles();
                        break;

                    case 3:
                        System.out.print("Enter Vehicle ID to delete: ");
                        String delID = sc.nextLine();
                        admin.deleteVehicle(delID);
                        break;

                    case 4:
                        System.out.print("Record ID: ");
                        String rid = sc.nextLine();

                        System.out.print("Vehicle ID: ");
                        String vid = sc.nextLine();

                        System.out.print("Issue: ");
                        String issue = sc.nextLine();

                        System.out.print("Date: ");
                        String date = sc.nextLine();

                        admin.addRecord(new MaintenanceRecord(rid, vid, issue, date));
                        break;

                    case 5:
                        admin.viewRecords();
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");

                }

            } catch (Exception e) {
                System.out.println("Error occurred! Please try again.");
                sc.nextLine();
            }

        } while (choice != 0);

    }
}