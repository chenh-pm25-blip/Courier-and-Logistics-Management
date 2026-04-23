import java.util.Date;

public class MaintenanceRecord {
    private String recordId;
    private String vehicleId;
    private Date serviceDate;
    private String serviceType;
    private double cost;

    public MaintenanceRecord(String recordId, String vehicleId, Date serviceDate, String serviceType, double cost) {
        this.recordId = recordId;
        this.vehicleId = vehicleId;
        this.serviceDate = serviceDate;
        this.serviceType = serviceType;
        this.cost = cost;
    }

    public String getSummary() {
        return "RecordID: " + recordId + ", Vehicle: " + vehicleId + ", Cost: " + cost;
    }

    public void updateCost() { }
    public Date getRecordDate() { return serviceDate; }

    @Override
    public String toString() {
        return getSummary() + " | Type: " + serviceType + " | Date: " + serviceDate;
    }
}