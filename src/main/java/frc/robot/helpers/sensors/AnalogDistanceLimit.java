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

    public AnalogDistanceLimit(int port, double LIMIT) {
        this(new AnalogDistanceSensor(port), LIMIT);
    }

    public boolean get() {
        return sensor.getDistance() < LIMIT && sensor.getDistance() > 0;
    }

    public LimitTrigger getTrigger() {
        return limitTrigger;
    }

    public AnalogDistanceSensor getRaw() {
        return sensor;
    }
}