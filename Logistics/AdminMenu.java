import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("U001", "admin01", "123456", "System Manager", "admin@courier.com", "EMP1001");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int choice;

        do {
            System.out.println("\n=== COURIER SYSTEM (UML Compliant) ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Manage Fleet (View Vehicles)");
            System.out.println("3. Delete Vehicle");
            System.out.println("4. Add Maintenance Record");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Vehicle ID: ");
                        String vid = sc.nextLine();

                        System.out.print("Mileage (double): ");
                        double mileage = sc.nextDouble();
                        sc.nextLine(); 

                        admin.addVehicle(new Vehicle(vid, mileage, new Date()));
                        System.out.println("Vehicle added successfully!");
                        break;

                    case 2:
                        admin.manageFleet();
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
                        String targetVid = sc.nextLine();

                        System.out.print("Service Type: ");
                        String type = sc.nextLine();

                        System.out.print("Cost (double): ");
                        double cost = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Date (yyyy-MM-dd): ");
                        String dateStr = sc.nextLine();
                        Date sDate = dateFormat.parse(dateStr);

                        admin.addRecord(new MaintenanceRecord(rid, targetVid, sDate, type, cost));
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". Please check your input format.");
            }

        } while (choice != 0);

        sc.close();
    }
}