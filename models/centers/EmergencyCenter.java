package models.centers;

public interface EmergencyCenter {

    String getName();

    Integer getAmountOfMaximumEmergencies();

    Integer getProcessedEmergencies();

    Boolean isForRetirement();

    void processEmergency();
}
