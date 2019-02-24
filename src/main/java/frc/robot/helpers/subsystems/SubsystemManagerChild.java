package frc.robot.helpers.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class SubsystemManagerChild extends Subsystem {
  private boolean debug = false; 
  
  public SubsystemManagerChild() {
    SubsystemManager.subsystems.add(this);
  }

  public void debugOn() {
    debug = true;
  }

  public void init() {

  }

  public void initSD() {

  }

  public void update() {

  }

  public void updateSD() {
    if (debug) {
      debug();
    }
  }

  public void debug() {

  }

  @Override
  public void initDefaultCommand() {

  }
}