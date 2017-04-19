package commands;

import annotations.InjectArgs;
import core.ManagementSystem;
import enums.EmergencyLevel;
import enums.Status;
import models.emergencies.Emergency;
import models.emergencies.OrderEmergency;
import utils.RegistrationTime;
import utils.RegistrationTimeImpl;

public class RegisterOrderEmergencyCommand extends BaseCommand {
    @InjectArgs
    private String[] arguments;

    private Emergency emergency;
    private RegistrationTime registrationTime;

    public RegisterOrderEmergencyCommand(ManagementSystem emergencyManagmentSystem) {
        super(emergencyManagmentSystem);
    }

    @Override
    public String execute() {
        String description = this.arguments[1];
        EmergencyLevel level = EmergencyLevel.valueOf(this.arguments[2].toUpperCase());
        this.setRegistrationTime(this.arguments[3]);
        Status status = Status.valueOf(this.arguments[4].replaceAll("-", "_").toUpperCase());

        this.emergency = new OrderEmergency(description, level, this.registrationTime, status);

        //super.getEmergencyManagmentSystem().registerOrderEmergency(this.emergency);

        return super.getEmergencyManagmentSystem().registerOrderEmergency(this.emergency);
    }

    private void setRegistrationTime(String time){
        this.registrationTime = new RegistrationTimeImpl(time);
    }
}
