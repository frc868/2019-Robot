package frc.robot.helpers.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;

public class OrGroup extends TriggerGroup{
    
    public OrGroup(Trigger a, Trigger b){
        super(a, b);
    }
    
    @Override
    public boolean get(){
        return a.get() || b.get();
    }
}
