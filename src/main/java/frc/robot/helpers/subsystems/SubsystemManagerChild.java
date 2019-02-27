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

  public void initSD() {

  }

  public void initTab() {
    
  }

  public void initDebug() {

  }

  public void update() {

  }

  public void updateSD() {
 
  }

  public void updateTab() {

  }

  public void updateDebug() {

  }

  protected void addTab(String name, Sendable sendable) {
    Shuffleboard.getTab(subsystemName).add(name, sendable);
  }

  protected void addTab(String name, Object value) {
    Shuffleboard.getTab(subsystemName).add(name, value);
  }

  protected void addDebug(String name, Sendable sendable) {
    Shuffleboard.getTab(DEBUG).add(name, sendable);
  }

  protected void addDebug(String name, Object value) {
    Shuffleboard.getTab(DEBUG).add(name, value);
  }

  @Override
  public void initDefaultCommand() {

  }
}