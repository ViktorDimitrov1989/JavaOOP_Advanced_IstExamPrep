package commands;

import annotations.InjectArgs;
import core.ManagementSystem;
import enums.EmergencyLevel;
import models.emergencies.Emergency;
import models.emergencies.PropertyEmergency;
import utils.RegistrationTime;
import utils.RegistrationTimeImpl;

public class RegisterPropertyEmergencyCommand extends BaseCommand {
    @InjectArgs
    private String[] arguments;

    private Emergency emergency;
    private RegistrationTime registrationTime;

    public RegisterPropertyEmergencyCommand(ManagementSystem emergencyManagmentSystem) {
        super(emergencyManagmentSystem);
    }

    @Override
    public String execute() {
        String description = this.arguments[1];
        EmergencyLevel level = EmergencyLevel.valueOf(this.arguments[2].toUpperCase());
        this.setRegistrationTime(this.arguments[3]);
        Integer damage = Integer.valueOf(this.arguments[4]);

        this.emergency = new PropertyEmergency(description, level, this.registrationTime, damage);

        //super.getEmergencyManagmentSystem().registerPropertyEmergency(this.emergency);

        return super.getEmergencyManagmentSystem().registerPropertyEmergency(this.emergency);
    }

    private void setRegistrationTime(String time){
        this.registrationTime = new RegistrationTimeImpl(time);
    }
}
