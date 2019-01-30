/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.elevator.subsystems.*;
import frc.robot.helpers.SubsystemManager;
import frc.robot.climber.subsystems.*;

public class Robot extends TimedRobot {
  public static Drivetrain drivetrain = new Drivetrain();
  public static HatchClaw hatchClaw = new HatchClaw();
  public static Drive climberDrive = new Drive();
  public static Foot climberFoot = new Foot();
  public static Ramp climberRamp = new Ramp();
  public static Forks forks = new Forks();
  public static OI m_oi;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_oi = new OI();
    SubsystemManager.init();
    SubsystemManager.initSD();

  }

  @Override
  public void robotPeriodic() {
    SubsystemManager.update();
    SubsystemManager.updateSD();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }


  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
