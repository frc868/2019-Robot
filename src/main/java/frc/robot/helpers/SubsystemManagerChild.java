package frc.robot.helpers;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.helpers.SubsystemManager;

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