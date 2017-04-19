package models.emergencies;

import enums.EmergencyLevel;
import utils.RegistrationTime;

public class HealthEmergency extends BaseEmergency {
    private Integer casualties;


    public HealthEmergency(String description, EmergencyLevel emergencyLevel, RegistrationTime registrationTime, Integer casualties) {
        super(description, emergencyLevel, registrationTime);
        this.casualties = casualties;
    }

    @Override
    public Integer getSpecialField() {
        return this.casualties;
    }
}
