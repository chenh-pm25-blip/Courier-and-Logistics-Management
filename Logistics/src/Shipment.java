import java.util.Date;

public class Shipment {
    private String trackingId;
    private String shippingSpeed;
    private double shippingFee;
    private String status;
    private Date createdAt;
    private int failedAttempts;
    
    private Package pkg;
    private Address originAddress;
    private Address destinationAddress;

    private static Shipment[] shipmentList = new Shipment[100]; 
    private static int shipmentCount = 0;

    public Shipment(String trackingId, String shippingSpeed, Package pkg) {
        this.trackingId = trackingId;
        this.shippingSpeed = shippingSpeed;
        this.pkg = pkg;
        this.status = "Picked Up";
        this.createdAt = new Date();
        this.failedAttempts = 0;
        this.shippingFee = calculateFee();
    }

    public static void initializeShipmentData() {
        Package p1 = new Package("P001", 1.2, "20x15x10", "Documents");
        Package p2 = new Package("P002", 5.5, "40x30x20", "Electronics");
        Package p3 = new Package("P003", 10.0, "60x60x40", "Household Items");

        addShipment(new Shipment("T001", "Standard", p1));
        addShipment(new Shipment("T002", "Express", p2));
        addShipment(new Shipment("T003", "Standard", p3));
    }

    public static void addShipment(Shipment s) {
        if (shipmentCount < shipmentList.length) {
            shipmentList[shipmentCount++] = s;
        } else {
            System.out.println("Error: Shipment storage is full.");
        }
    }

    public static Shipment findShipment(String id) {
        for (int i = 0; i < shipmentCount; i++) {
            if (shipmentList[i].getTrackingId().equalsIgnoreCase(id)) {
                return shipmentList[i];
            }
        }
        return null;
    }

    public double calculateFee() {
        if (pkg == null) {
            System.out.println("Error: No package associated with this shipment.");
            return 0.0;
        }

        double baseRate = 5.0; 
        if (shippingSpeed != null && shippingSpeed.equalsIgnoreCase("Express")) {
            baseRate = 15.0;
        }

        this.shippingFee = pkg.getWeight() * baseRate; 
        return this.shippingFee;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("System: Tracking ID " + trackingId + " updated to [" + status + "]");
    }

    public void reschedule() {
        this.failedAttempts++;
        this.status = "Rescheduled - Attempt " + failedAttempts;
        System.out.println("Log: Delivery failed. Package rescheduled.");
    }

	public String getTrackingStatus() {
        return "Parcel [" + trackingId + "] is currently: " + status;
    }

    // Getters and Setters
    public String getTrackingId() { 
    	return trackingId; 
    }
    public String getStatus() { 
    	return status; 
    }
    public double getShippingFee() { 
    	return shippingFee; 
    }
    
    public static Shipment[] getShipmentList() { 
    	return shipmentList; 
    }
    public static int getShipmentCount() { 
    	return shipmentCount; 
    }
    
    @Override
    public String toString() {
        return "Tracking ID: " + trackingId + " | Status: " + status + " | Fee: RM" + shippingFee;
    }
}