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
import frc.robot.drivetrain.commands.DriveStraight;
import frc.robot.powerpack.subsystems.*;
import frc.robot.sensors.subsystems.Camera;
import frc.robot.sensors.subsystems.gyro.Gyroscope;
import frc.robot.helpers.SubsystemManager;
import frc.robot.carriage.subsystems.*;

public class Robot extends TimedRobot {
  public static BallIntake ballIntake = new BallIntake();
  public static HatchClaw hatchClaw = new HatchClaw();
  public static HatchPickup hatchPickup = new HatchPickup();
  public static Tilt tilt = new Tilt();

  public static Drivetrain drivetrain = new Drivetrain();

  public static FootDrive footDrive = new FootDrive();
  public static Forks forks = new Forks();
  public static PowerPack powerPack = new PowerPack();
  public static Ramps climberRamps = new Ramps();

  public static Camera camera = new Camera();
  public static Gyroscope gyro = new Gyroscope();

  public static OI m_oi;

  public static DriveStraight test;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_oi = new OI();
    SubsystemManager.init();
    SubsystemManager.initSD();
    gyro.reset();

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
    test = new DriveStraight(24, 0.5);
    // test.start();
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
