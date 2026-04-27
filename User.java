import java.util.Date;
import java.io.*;

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
    private boolean isPaid;

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
        this.isPaid = false;
    }

    public static void initializeShipmentData() {
        File file = new File("shipments.txt");
        if (!file.exists()) {
            System.out.println("System: No shipment data found. Starting with empty list.");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 8) {
                        String trackingId = parts[0];
                        String speed = parts[1];
                        String status = parts[2];
                        boolean isPaid = Boolean.parseBoolean(parts[3]);
                        String pkgId = parts[4];
                        double weight = Double.parseDouble(parts[5]);
                        String dims = parts[6];
                        String content = parts[7];
                        
                        Package pkg = new Package(pkgId, weight, dims, content);
                        Shipment s = new Shipment(trackingId, speed, pkg);
                        s.status = status;
                        if (isPaid) {
                            s.markAsPaid();
                        }
                        addShipment(s);
                    }
                }
                System.out.println("System: Shipment data loaded.");
            } catch (Exception e) {
                System.out.println("Error reading shipments: " + e.getMessage());
            }
        }
    }

    public static void saveShipments() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("shipments.txt"))) {
            for (int i = 0; i < shipmentCount; i++) {
                if (shipmentList[i] != null) {
                    Shipment s = shipmentList[i];
                    Package p = s.pkg;
                    if (p != null) {
                        bw.write(s.getTrackingId() + "|" + s.shippingSpeed + "|" + s.getStatus() + "|" + 
                                 s.getIsPaid() + "|" + p.getPackageId() + "|" + p.getWeight() + "|" + 
                                 p.getDimensions() + "|" + p.getContentType() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error saving shipments: " + e.getMessage());
        }
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
    public boolean getIsPaid() {
        return isPaid;
    }
    public void markAsPaid() {
        this.isPaid = true;
    }
    
    public static Shipment[] getShipmentList() { 
    	return shipmentList; 
    }
    public static int getShipmentCount() { 
    	return shipmentCount; 
    }
    
    @Override
    public String toString() {
        String paidStatus = isPaid ? "Paid" : "Unpaid";
        return "Tracking ID: " + trackingId + " | Status: " + status + " | Fee: RM" + shippingFee + " [" + paidStatus + "]";
    }
}