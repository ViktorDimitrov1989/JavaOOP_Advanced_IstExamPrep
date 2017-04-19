package core;

import collection.EmergencyRegister;
import collection.Register;
import models.centers.EmergencyCenter;
import models.emergencies.Emergency;

import java.util.HashMap;
import java.util.Map;

public class EmergencyManagementSystem implements ManagementSystem {
    private static final String PROPERTY = "Property";
    private static final String HEALTH = "Health";
    private static final String ORDER = "Order";

    private Map<String, Register<Emergency>> emergencies;
    private Map<String, Register<EmergencyCenter>> emergencyCenters;
    private Map<String, Long> processedEmergencies;
    private Long totalProcessedEmergencies = 0L;

    public EmergencyManagementSystem() {
        this.initEmergencies();
        this.initEmergencyCenters();
        this.initProcessedEmergencies();
    }

    private void initProcessedEmergencies() {
        this.processedEmergencies = new HashMap<>();
        this.processedEmergencies.put(PROPERTY, 0l);
        this.processedEmergencies.put(HEALTH, 0l);
        this.processedEmergencies.put(ORDER, 0l);
    }

    private void initEmergencyCenters() {
        this.emergencyCenters = new HashMap<>();
        this.emergencyCenters.put(PROPERTY, new EmergencyRegister<>());
        this.emergencyCenters.put(HEALTH, new EmergencyRegister<>());
        this.emergencyCenters.put(ORDER, new EmergencyRegister<>());
    }

    private void initEmergencies() {
        this.emergencies = new HashMap<>();
        this.emergencies.put(PROPERTY, new EmergencyRegister<>());
        this.emergencies.put(HEALTH, new EmergencyRegister<>());
        this.emergencies.put(ORDER, new EmergencyRegister<>());
    }


    @Override
    public String registerPropertyEmergency(Emergency emergency) {
        this.emergencies.get(PROPERTY).enqueueEmergency(emergency);
        return String.format("Registered Public Property Emergency of level %s at %s.",
                emergency.getEmergencyLevel().toString(), emergency.getRegistrationTime());
    }

    @Override
    public String registerHealthEmergency(Emergency emergency) {
        this.emergencies.get(HEALTH).enqueueEmergency(emergency);
        return String.format("Registered Public Health Emergency of level %s at %s.",
                emergency.getEmergencyLevel().toString(), emergency.getRegistrationTime());
    }

    @Override
    public String registerOrderEmergency(Emergency emergency) {
        this.emergencies.get(ORDER).enqueueEmergency(emergency);
        return String.format("Registered Public Order Emergency of level %s at %s.",
                emergency.getEmergencyLevel().toString(), emergency.getRegistrationTime());
    }

    @Override
    public String registerFireServiceCenter(EmergencyCenter emergencyCenter) {
        this.emergencyCenters.get(PROPERTY).enqueueEmergency(emergencyCenter);
        return String.format("Registered Fire Service Emergency Center - %s.",
                emergencyCenter.getName());
    }

    @Override
    public String registerMedicalServiceCenter(EmergencyCenter emergencyCenter) {
        this.emergencyCenters.get(HEALTH).enqueueEmergency(emergencyCenter);
        return String.format("Registered Medical Service Emergency Center - %s.",
                emergencyCenter.getName());
    }

    @Override
    public String registerPoliceServiceCenter(EmergencyCenter emergencyCenter) {
        this.emergencyCenters.get(ORDER).enqueueEmergency(emergencyCenter);
        return String.format("Registered Police Service Emergency Center - %s.",
                emergencyCenter.getName());
    }

    @Override
    public String processEmergencies(String type) {
        Long processed = 0L;//TODO

        while (true) {
            if (this.emergencyCenters.get(type).isEmpty() || this.emergencies.get(type).isEmpty()) {
                break;
            }
            Emergency currentEmergency = this.emergencies.get(type).dequeueEmergency();
            EmergencyCenter currentCenter = this.emergencyCenters.get(type).dequeueEmergency();

            currentCenter.processEmergency();
            this.totalProcessedEmergencies++;

            processed += currentEmergency.getSpecialField();

            this.processedEmergencies.put(type, this.processedEmergencies.get(type) + processed);

            if (!currentCenter.isForRetirement()) {
                this.emergencyCenters.get(type).enqueueEmergency(currentCenter);
            }

            processed = 0L;
        }


        if (this.emergencies.get(type).count() == 0) {
            return String.format("Successfully responded to all %s emergencies.", type);
        } else {
            return String.format("%s Emergencies left to process: %s.", type, this.emergencies.get(type).count());
        }

    }

    @Override
    public String emergencyReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("PRRM Services Live Statistics").append(System.lineSeparator());
        sb.append(String.format("Fire Service Centers: %s", this.emergencyCenters.get(PROPERTY).count())).append(System.lineSeparator());
        sb.append(String.format("Medical Service Centers: %s", this.emergencyCenters.get(HEALTH).count())).append(System.lineSeparator());
        sb.append(String.format("Police Service Centers: %s", this.emergencyCenters.get(ORDER).count())).append(System.lineSeparator());
        sb.append(String.format("Total Processed Emergencies: %s", this.totalProcessedEmergencies)).append(System.lineSeparator());
        sb.append(String.format("Currently Registered Emergencies: %s", this.emergencies.values().stream().mapToInt(Register::count).sum()))
        .append(System.lineSeparator());

        sb.append(String.format("Total Property Damage Fixed: %s", this.processedEmergencies.get(PROPERTY))).append(System.lineSeparator());
        sb.append(String.format("Total Health Casualties Saved: %s", this.processedEmergencies.get(HEALTH))).append(System.lineSeparator());
        sb.append(String.format("Total Special Cases Processed: %s", this.processedEmergencies.get(ORDER))).append(System.lineSeparator());

        return sb.toString();
    }
}
