package commands;

import annotations.InjectArgs;
import core.ManagementSystem;
import models.centers.EmergencyCenter;
import models.centers.MedicalServiceCenter;

public class RegisterMedicalServiceCenterCommand extends BaseCommand {
    @InjectArgs
    private String[] arguments;

    private EmergencyCenter emergencyCenter;

    public RegisterMedicalServiceCenterCommand(ManagementSystem emergencyManagmentSystem) {
        super(emergencyManagmentSystem);
    }

    @Override
    public String execute() {
        String name = this.arguments[1];
        Integer amountOfEmergencies = Integer.valueOf(this.arguments[2]);

        this.emergencyCenter = new MedicalServiceCenter(name, amountOfEmergencies);

        //super.getEmergencyManagmentSystem().registerMedicalServiceCenter(this.emergencyCenter);

        return super.getEmergencyManagmentSystem().registerMedicalServiceCenter(this.emergencyCenter);
    }

}
