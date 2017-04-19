package commands;

import annotations.InjectArgs;
import core.ManagementSystem;
import models.centers.EmergencyCenter;
import models.centers.PoliceServiceCenter;

public class RegisterPoliceServiceCenterCommand extends BaseCommand {
    @InjectArgs
    private String[] arguments;

    private EmergencyCenter emergencyCenter;

    public RegisterPoliceServiceCenterCommand(ManagementSystem emergencyManagmentSystem) {
        super(emergencyManagmentSystem);
    }

    @Override
    public String execute() {
        String name = this.arguments[1];
        Integer amountOfEmergencies = Integer.valueOf(this.arguments[2]);

        this.emergencyCenter = new PoliceServiceCenter(name, amountOfEmergencies);

        //super.getEmergencyManagmentSystem().registerPoliceServiceCenter(this.emergencyCenter);

        return super.getEmergencyManagmentSystem().registerPoliceServiceCenter(this.emergencyCenter);
    }

}
