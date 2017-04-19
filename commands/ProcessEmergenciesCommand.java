package commands;

import annotations.InjectType;
import core.ManagementSystem;

public class ProcessEmergenciesCommand extends BaseCommand{

    @InjectType
    private String type;

    public ProcessEmergenciesCommand(ManagementSystem emergencyManagmentSystem) {
        super(emergencyManagmentSystem);
    }

    @Override
    public String execute() {
        return super.getEmergencyManagmentSystem().processEmergencies(this.type);
    }
}
