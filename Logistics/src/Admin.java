public class Admin extends User {
    private String employeeId;

    public Admin(String userId, String username, String password, String employeeId) {
        super(userId, username, password); 
        this.employeeId = employeeId;
    }

    public void assignDriver(Courier courier, Shipment shipment) {
        System.out.println("Admin [" + employeeId + "] is assigning Courier " + 
                           courier.getUsername() + " to Shipment " + shipment.getTrackingId());
    }

    public void manageFleet() {
        System.out.println("\n--- Global Fleet Status Report ---");
        if (Vehicle.getFleet().length == 0 || Vehicle.getVehicleCount() == 0) {
            System.out.println("Log: No vehicles registered in the system.");
        } else {
            for (int i = 0; i < Vehicle.getVehicleCount(); i++) {
                System.out.println(Vehicle.getFleet()[i].toString());
            }
        }
    }

    public void scheduleMaintenance(String vehicleId) {
        Vehicle v = Vehicle.findVehicle(vehicleId);
        
        if (v != null) {
            v.setMaintenanceDate(new java.util.Date());
            System.out.println("System: Maintenance scheduled for Vehicle " + vehicleId);
        } else {
            System.out.println("Error: Vehicle ID [" + vehicleId + "] not found.");
        }
    }

    public void rescheduleShipment(Shipment shipment) {
        System.out.println("Admin Override: Manually rescheduling Shipment " + shipment.getTrackingId());
        shipment.reschedule();
    }

    public void addVehicle(Vehicle v) {
        Vehicle.addVehicleToFleet(v);
        System.out.println("System: Vehicle " + v.getVehicleId() + " added to central fleet records.");
    }

    // Getters and Setters
    public String getEmployeeId() { 
        return employeeId; 
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Employee ID: " + employeeId + 
               " | Fleet Size: " + Vehicle.getVehicleCount();
    }
}