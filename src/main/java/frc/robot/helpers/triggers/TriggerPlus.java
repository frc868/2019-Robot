package frc.robot.helpers.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;

public abstract class TriggerPlus extends Trigger {

    public AndGroup and(Trigger trigger) {
        return new AndGroup(this, trigger);
    }

    public OrGroup or(Trigger trigger) {
        return new OrGroup(this, trigger);
    }

    public NotGroup not(Trigger trigger) {
        return new NotGroup(this, trigger);
    }
}
