package frc.robot.helpers.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;

public abstract class TriggerGroup extends TriggerPlus{
    
    Trigger a, b;
    
    public TriggerGroup(Trigger a, Trigger b){
        this.a = a;
        this.b = b;
    }
    
    @Override
    public abstract boolean get();
}
