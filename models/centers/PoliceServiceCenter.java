package models.centers;

public class PoliceServiceCenter extends BaseEmergencyCenter {
    private Integer currentEmergencyAmount;

    public PoliceServiceCenter(String name, Integer amountOfMaximumEmergencies) {
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
