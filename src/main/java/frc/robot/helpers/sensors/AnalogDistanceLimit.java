package frc.robot.helpers.sensors;


public class AnalogDistanceLimit {
    private AnalogDistanceSensor sensor;
    private final double LIMIT;
    private LimitTrigger limitTrigger;
    
    public AnalogDistanceLimit(AnalogDistanceSensor sensor, double LIMIT) {
        this.sensor = sensor;
        this.LIMIT = LIMIT;
        limitTrigger = new LimitTrigger(this);
    }

    public boolean get() {
        return sensor.getDistance() < LIMIT;
    }

    public LimitTrigger getTrigger() {
        return limitTrigger;
    }
}