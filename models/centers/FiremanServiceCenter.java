package models.centers;

public class FiremanServiceCenter extends BaseEmergencyCenter {
    private Integer currentEmergencyAmount;

    public FiremanServiceCenter(String name, Integer amountOfMaximumEmergencies) {
        super(name, amountOfMaximumEmergencies);
        this.currentEmergencyAmount = 0;
    }

    @Override
    public Integer getProcessedEmergencies() {
        return this.currentEmergencyAmount;
    }

    @Override
    public Boolean isForRetirement() {
        return this.currentEmergencyAmount >= super.getAmountOfMaximumEmergencies();
    }

    @Override
    public void processEmergency() {
        this.currentEmergencyAmount++;
    }
}
