package frc.robot.helpers.subsystems;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public abstract class SubsystemManagerChild extends Subsystem {  
  private String subsystemName; 
  private final String DEBUG = "Debug";

  public SubsystemManagerChild(String subsystemName) {
    SubsystemManager.subsystems.add(this);
    this.subsystemName = subsystemName;
  }

  /**
   * code to run on robot init
   */
  public void init() {

  }

  /**
   * code to run on disabled init
   */
  public void initDisabled() {

  }

  /**
   * code to run on enabled init (test init, teleop init, auton init)
   */
  public void initEnabled() {

  } 

  /**
   * code to run on robot init, specific to SmartDashboard management
   */
  public void initSD() {

  }

  /**
   * code to run in robot periodic
   */
  public void update() {

  }

  /**
   * code to run in disabled periodic
   */
  public void updateDisabled() {

  }

  /**
   * code to run in enabled periodic (test periodic, teleop periodic, auton periodic)
   */
  public void updateEnabled() {
    
  } 

  /**
   * code to run in robot periodic, specific to SmartDashboard management
   */
  public void updateSD() {
 
  }
  
  protected void addTab(String name, Sendable sendable) {
    // Shuffleboard.getTab(subsystemName).add(subsystemName + ": " + name, sendable);
  }

  protected void addTab(String name, Object value) {
    // Shuffleboard.getTab(subsystemName).add(subsystemName + ": " + name, value);
  }

  protected void addDebug(String name, Sendable sendable) {
    // Shuffleboard.getTab(DEBUG).add(DEBUG + ": " + name, sendable);
  }

  protected void addDebug(String name, Object value) {
    // Shuffleboard.getTab(DEBUG).add(DEBUG + ": " + name, value);
  }

  @Override
  public void initDefaultCommand() {

  }
}