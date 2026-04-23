import java.util.InputMismatchException;
import java.util.Scanner;

public class CourierMenu {
    // Data Storage: Fixed-size array as per requirement
    private static final int MAX_COURIERS = 100;
    private static Courier[] courierDB = new Courier[MAX_COURIERS];
    private static int courierCount = 0; // Tracks the number of items in the array
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("\n=== COURIER & LOGISTICS MANAGEMENT SYSTEM ===");
                System.out.println("1. Add New Courier (Create)");
                System.out.println("2. View All Couriers (Read)");
                System.out.println("3. Update Courier Vehicle (Update)");
                System.out.println("4. Remove a Courier (Delete)");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1: createCourier(); break;
                    case 2: readCouriers(); break;
                    case 3: updateCourier(); break;
                    case 4: deleteCourier(); break;
                    case 5: exit = true; System.out.println("Exiting System. Goodbye!"); break;
                    default: System.out.println("Invalid choice. Please select 1-5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Invalid input type. Please enter a number.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("\n[UNEXPECTED ERROR] " + e.getMessage());
            }
        }
        scanner.close();
    }

    // --- CRUD: CREATE ---
    private static void createCourier() {
        if (courierCount >= MAX_COURIERS) {
            System.out.println("[ERROR] Database is full! Cannot add more couriers.");
            return;
        }

        System.out.println("\n--- Add New Courier ---");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Courier ID: ");
        String courierId = scanner.nextLine();
        
        System.out.print("Enter Assigned Vehicle ID: ");
        String vehicleId = scanner.nextLine();

        Vehicle assignedVehicle = new Vehicle(vehicleId);
        Courier newCourier = new Courier(userId, username, password, fullName, email, courierId, assignedVehicle);
        
        // Add to array and increment counter
        courierDB[courierCount] = newCourier;
        courierCount++;
        
        System.out.println("[SUCCESS] Courier added successfully!");
    }

    // --- CRUD: READ ---
    private static void readCouriers() {
        System.out.println("\n--- Registered Couriers ---");
        if (courierCount == 0) {
            System.out.println("No couriers found in the database.");
            return;
        }
        for (int i = 0; i < courierCount; i++) {
            System.out.println("\nCourier #" + (i + 1));
            System.out.println(courierDB[i].toString());
            System.out.println("---------------------------");
        }
    }

    // --- CRUD: UPDATE ---
    private static void updateCourier() {
        System.out.print("\nEnter the Courier ID to update: ");
        String idToUpdate = scanner.nextLine();

        try {
            Courier courier = findCourierById(idToUpdate);
            System.out.println("Updating Courier: " + courier.getFullName());
            
            System.out.print("Enter new Assigned Vehicle ID: ");
            String newVehicleId = scanner.nextLine();
            
            courier.setVehicleAssigned(new Vehicle(newVehicleId));
            System.out.println("[SUCCESS] Courier vehicle updated successfully!");
            
        } catch (CourierNotFoundException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // --- CRUD: DELETE (Array Shift Logic) ---
    private static void deleteCourier() {
        System.out.print("\nEnter the Courier ID to remove: ");
        String idToDelete = scanner.nextLine();

        int indexToRemove = -1;
        for (int i = 0; i < courierCount; i++) {
            if (courierDB[i].getCourierId().equalsIgnoreCase(idToDelete)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("[ERROR] Courier with ID '" + idToDelete + "' does not exist.");
            return;
        }

        String removedName = courierDB[indexToRemove].getFullName();

        // Shift elements to the left to fill the gap
        for (int i = indexToRemove; i < courierCount - 1; i++) {
            courierDB[i] = courierDB[i + 1];
        }
        
        // Nullify the last element and decrement the count
        courierDB[courierCount - 1] = null;
        courierCount--;

        System.out.println("[SUCCESS] Courier '" + removedName + "' has been removed.");
    }

    // --- HELPER METHOD (Search) ---
    private static Courier findCourierById(String id) throws CourierNotFoundException {
        for (int i = 0; i < courierCount; i++) {
            if (courierDB[i].getCourierId().equalsIgnoreCase(id)) {
                return courierDB[i];
            }
        }
        throw new CourierNotFoundException("Courier with ID '" + id + "' does not exist.");
    }
}