import java.util.Scanner;
import java.util.InputMismatchException;

public class VehicleMenu {
    private Scanner scanner = new Scanner(System.in);

    public void display(Admin admin) {
        boolean exitSubMenu = false;

        while (!exitSubMenu) {
            System.out.println("\n|==================== VEHICLE MANAGEMENT ====================|");
            System.out.println("1. View All Vehicles");
            System.out.println("2. Register New Vehicle");
            System.out.println("3. Update Vehicle Mileage");
            System.out.println("4. Schedule Maintenance");
            System.out.println("5. Return to Admin Menu");
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
                System.out.println("Error: Please enter a valid number (1-5).");
                scanner.nextLine();
            }
        }
    }

    private void handleAddVehicle(Admin admin) {
        try {
            System.out.print("Enter New Vehicle ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Initial Mileage: ");
            double miles = scanner.nextDouble();
            scanner.nextLine();
            
            Vehicle newV = new Vehicle(id, miles);
            admin.addVehicle(newV);
        } catch (InputMismatchException e) {
            System.out.println("Error: Mileage must be a numeric value.");
            scanner.nextLine(); 
        }
    }

    private void handleUpdateMileage() {
        try {
            System.out.print("Enter Vehicle ID: ");
            String id = scanner.nextLine();
            Vehicle v = Vehicle.findVehicle(id);
            
            if (v != null) {
                System.out.print("Enter additional distance traveled (km): ");
                double dist = scanner.nextDouble();
                scanner.nextLine();
                v.updateMileage(dist);
            } else {
                System.out.println("Error: Vehicle ID not found in records.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Distance must be a numeric value.");
            scanner.nextLine();
        }
    }
}