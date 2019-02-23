package frc.robot.helpers.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class SubsystemManagerChild extends Subsystem {
    public SubsystemManagerChild() {
      SubsystemManager.subsystems.add(this);
    }

    public void init() {

    }

    public void initSD() {

    }

    public void update() {

    }

    public void updateSD() {
      
    }

    @Override
    public void initDefaultCommand() {

    }
}