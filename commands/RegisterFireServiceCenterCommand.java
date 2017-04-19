package commands;

import annotations.InjectArgs;
import core.ManagementSystem;
import models.centers.FiremanServiceCenter;
import models.centers.EmergencyCenter;

public class RegisterFireServiceCenterCommand extends BaseCommand {
    @InjectArgs
    private String[] arguments;

    private EmergencyCenter emergencyCenter;

    public RegisterFireServiceCenterCommand(ManagementSystem emergencyManagmentSystem) {
        super(emergencyManagmentSystem);
    }

    @Override
    public String execute() {
        String name = this.arguments[1];
        Integer amountOfEmergencies = Integer.valueOf(this.arguments[2]);

        this.emergencyCenter = new FiremanServiceCenter(name, amountOfEmergencies);

        //super.getEmergencyManagmentSystem().registerFireServiceCenter(this.emergencyCenter);

        return super.getEmergencyManagmentSystem().registerFireServiceCenter(this.emergencyCenter);
    }

}
