package commands;

import core.ManagementSystem;

public class EmergencyReportCommand extends BaseCommand{

    public EmergencyReportCommand(ManagementSystem emergencyManagmentSystem) {
        super(emergencyManagmentSystem);
    }

    @Override
    public String execute() {
        return super.getEmergencyManagmentSystem().emergencyReport();
    }
}
