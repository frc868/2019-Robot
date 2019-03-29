package frc.robot.helpers.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;

public class NotGroup extends TriggerGroup{
    
    /**
     * @param trueTrigger  the trigger that must be true
     * @param falseTrigger the trigger that must be false
     */
    public NotGroup(Trigger trueTrigger, Trigger falseTrigger){
        super(trueTrigger, falseTrigger);
    }
    
    /**
     * @return true if a is true but b is false
     */
    @Override
    public boolean get(){
        return a.get() && (!b.get());
    }
}
