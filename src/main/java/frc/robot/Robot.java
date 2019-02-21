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

import frc.robot.oi.OI;
import frc.robot.helpers.SubsystemManager;

import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.DrivetrainNEO;

import frc.robot.carriage.ballintake.*;
import frc.robot.carriage.hatchclaw.*;
import frc.robot.carriage.tilt.*;
import frc.robot.carriage.groundpickup.*;

import frc.robot.climberelevator.powerpack.*;
import frc.robot.climberelevator.ramps.*;
import frc.robot.climberelevator.forks.*;
import frc.robot.climberelevator.footdrive.*;

// import frc.robot.sensors.camera.*;

public class Robot extends TimedRobot {
  public static BallIntake ballIntake = new BallIntake();
  public static HatchClaw hatchClaw = new HatchClaw();
  public static GroundPickup groundPickup = new GroundPickup();
  public static Tilt tilt = new Tilt();

  public static Drivetrain drivetrain = new Drivetrain();
  // public static DrivetrainNEO drivetrain = new DrivetrainNEO();

  public static FootDrive footDrive = new FootDrive();
  public static Forks forks = new Forks();
  public static PowerPack powerPack = new PowerPack();
  public static Ramps climberRamps = new Ramps();

  // public static Camera camera = new Camera();

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    SubsystemManager.init();
    SubsystemManager.initSD();
  }

  @Override
  public void robotPeriodic() {
    SubsystemManager.update();
    SubsystemManager.updateSD();
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
    OI.init();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    OI.update();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
