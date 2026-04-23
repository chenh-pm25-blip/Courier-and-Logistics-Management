import java.util.Date;

public class MaintenanceRecord {
    private String recordId;
    private String vehicleId;
    private String serviceType;
    private String dateString;
    private Date serviceDate;
    private double cost;

    public MaintenanceRecord(String recordId, String vehicleId, Date serviceDate, String serviceType, double cost) {
        this.recordId = recordId;
        this.vehicleId = vehicleId;
        this.serviceDate = serviceDate;
        this.serviceType = serviceType;
        this.cost = cost;
    }

    public MaintenanceRecord(String recordId, String vehicleId, String serviceType, String dateString) {
        this.recordId = recordId;
        this.vehicleId = vehicleId;
        this.serviceType = serviceType;
        this.dateString = dateString;
    }

    @Override
    public String toString() {
        return "RecordID: " + recordId + " | Vehicle: " + vehicleId + " | Issue: " + serviceType + " | Date: " + (dateString != null ? dateString : serviceDate);
    }
}