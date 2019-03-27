package frc.robot.helpers.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;

public class AndGroup extends TriggerGroup{
    
    public AndGroup(Trigger a, Trigger b){
        super(a, b);
    }
    
    @Override
    public boolean get(){
        return a.get() && b.get();
    }
}
