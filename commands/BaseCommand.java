package commands;

import core.ManagementSystem;

public abstract class BaseCommand implements Executable{
    private ManagementSystem emergencyManagmentSystem;

    protected BaseCommand(ManagementSystem emergencyManagmentSystem) {
        this.emergencyManagmentSystem = emergencyManagmentSystem;
    }

    protected ManagementSystem getEmergencyManagmentSystem() {
        return this.emergencyManagmentSystem;
    }

}
