package commands;

import annotations.InjectArgs;
import core.ManagementSystem;
import enums.EmergencyLevel;
import models.emergencies.HealthEmergency;
import models.emergencies.Emergency;
import utils.RegistrationTime;
import utils.RegistrationTimeImpl;

public class RegisterHealthEmergencyCommand extends BaseCommand {
    @InjectArgs
    private String[] arguments;

    private Emergency emergency;
    private RegistrationTime registrationTime;

    public RegisterHealthEmergencyCommand(ManagementSystem emergencyManagmentSystem) {
        super(emergencyManagmentSystem);
    }

    @Override
    public String execute() {
        String description = this.arguments[1];
        EmergencyLevel level = EmergencyLevel.valueOf(this.arguments[2].toUpperCase());
        this.setRegistrationTime(this.arguments[3]);
        Integer casualties = Integer.valueOf(this.arguments[4]);

        this.emergency = new HealthEmergency(description, level, this.registrationTime, casualties);

        //super.getEmergencyManagmentSystem().registerHealthEmergency(this.emergency);

        return super.getEmergencyManagmentSystem().registerHealthEmergency(this.emergency);
    }

    private void setRegistrationTime(String time){
        this.registrationTime = new RegistrationTimeImpl(time);
    }
}
