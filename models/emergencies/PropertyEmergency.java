package models.emergencies;

import enums.EmergencyLevel;
import utils.RegistrationTime;

public class PropertyEmergency extends BaseEmergency {
    private Integer damage;

    public PropertyEmergency(String description, EmergencyLevel emergencyLevel, RegistrationTime registrationTime, Integer damage) {
        super(description, emergencyLevel, registrationTime);
        this.damage = damage;
    }

    @Override
    public Integer getSpecialField() {
        return this.damage;
    }
}
