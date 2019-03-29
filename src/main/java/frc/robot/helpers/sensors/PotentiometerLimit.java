package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * *Currently Unused*
 */
public class PotentiometerLimit{
    
    private final double FORWARD_LIMIT, REVERSE_LIMIT;
    private AnalogPotentiometer potentiometer;
    private LimitTrigger limitTrigger;
    
    public PotentiometerLimit(AnalogPotentiometer potentiometer, double FORWARD_LIMIT, double REVERSE_LIMIT){
        this.potentiometer = potentiometer;
        this.FORWARD_LIMIT = FORWARD_LIMIT;
        this.REVERSE_LIMIT = REVERSE_LIMIT;
        limitTrigger = new LimitTrigger(this);
    }
    
    public boolean get(){
        return getForwardLimit() || getReverseLimit();
    }
    
    public boolean getForwardLimit(){
        return potentiometer.get() > FORWARD_LIMIT;
    }
    
    public boolean getReverseLimit(){
        return potentiometer.get() < REVERSE_LIMIT;
    }
    
    public LimitTrigger getTrigger(){
        return limitTrigger;
    }
}