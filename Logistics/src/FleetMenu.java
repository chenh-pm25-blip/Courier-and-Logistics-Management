import java.util.Scanner;
import java.util.InputMismatchException;

public class FleetMenu {
    private Scanner scanner = new Scanner(System.in);

    public void display(Admin admin) {
        boolean exitSubMenu = false;

        while (!exitSubMenu) {
            System.out.println("\n--- FLEET MANAGEMENT SUB-MENU ---");
            System.out.println("1. View Full Fleet (Read)");
            System.out.println("2. Register New Vehicle (Create)");
            System.out.println("3. Update Vehicle Mileage (Update)");
            System.out.println("4. Schedule Maintenance");
            System.out.println("5. Return to Main Admin Menu");
            System.out.print("Choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        admin.manageFleet(); 
                        break;
                    case 2:
                        handleAddVehicle(admin);
                        break;
                    case 3:
                        handleUpdateMileage();
                        break;
                    case 4:
                        System.out.print("Enter Vehicle ID for Service: ");
                        String id = scanner.nextLine();
                        admin.scheduleMaintenance(id);
                        break;
                    case 5:
                        exitSubMenu = true;
                        break;
                    default:
                        System.out.println("Invalid selection. Range: 1-5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Numeric input required.");
                scanner.nextLine(); 
            }
        }
    }

    private void handleAddVehicle(Admin admin) {
        System.out.print("Enter New Vehicle ID (e.g., VND-202): ");
        String id = scanner.nextLine();
        System.out.print("Enter Initial Mileage: ");
        double miles = scanner.nextDouble();
        
        Vehicle newV = new Vehicle(id, miles);
        admin.addVehicle(newV);
    }

    private void handleUpdateMileage() {
        System.out.print("Enter Vehicle ID: ");
        String id = scanner.nextLine();
        Vehicle v = Vehicle.findVehicle(id);
        
        if (v != null) {
            System.out.print("Enter additional distance traveled (km): ");
            double dist = scanner.nextDouble();
            v.updateMileage(dist);
        } else {
            System.out.println("Error: Vehicle ID not found in records.");
        }
    }
}