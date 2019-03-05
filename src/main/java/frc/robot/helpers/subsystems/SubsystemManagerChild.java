package frc.robot.helpers.subsystems;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public abstract class SubsystemManagerChild extends Subsystem {  
  private String subsystemName; 
  private final String DEBUG = "Debug";

  public SubsystemManagerChild(String subsystemName) {
    SubsystemManager.subsystems.add(this);
    this.subsystemName = subsystemName;
  }

  public void init() {

  }

  public void initDisabled() {

  }

  public void initEnabled() {

  } 

  public void initSD() {

  }

  public void update() {

  }

  public void updateDisabled() {

  }

  public void updateEnabled() {
    
  } 

  public void updateSD() {
 
  }

  protected void addTab(String name, Sendable sendable) {
    Shuffleboard.getTab(subsystemName).add(subsystemName + ": " + name, sendable);
  }

  protected void addTab(String name, Object value) {
    Shuffleboard.getTab(subsystemName).add(subsystemName + ": " + name, value);
  }

  protected void addDebug(String name, Sendable sendable) {
    Shuffleboard.getTab(DEBUG).add(DEBUG + ": " + name, sendable);
  }

  protected void addDebug(String name, Object value) {
    Shuffleboard.getTab(DEBUG).add(DEBUG + ": " + name, value);
  }

  @Override
  public void initDefaultCommand() {

  }
}