package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.carriage.ballintake.BallIntake;
import frc.robot.carriage.hatchclaw.HatchClaw;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.footdrive.FootDrive;
import frc.robot.climberelevator.forks.Forks;
import frc.robot.climberelevator.powerpack.PowerPack;
import frc.robot.climberelevator.ramps.Ramps;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.helpers.subsystems.SubsystemManager;
import frc.robot.oi.OI;
import frc.robot.sensors.camera.Camera;
import frc.robot.sensors.gyro.Gyro;
// import frc.robot.sensors.ultrasonic.UltrasonicArray;

public class Robot extends TimedRobot {
  public static BallIntake ballIntake = new BallIntake();
  public static HatchClaw hatchClaw = new HatchClaw();
  // public static GroundPickup groundPickup = new GroundPickup();
  public static Tilt tilt = new Tilt();

  public static Drivetrain drivetrain = new Drivetrain();

  public static FootDrive footDrive = new FootDrive();
  public static Forks forks = new Forks();
  public static PowerPack powerPack = new PowerPack();
  public static Ramps climberRamps = new Ramps();

  public static Camera camera = new Camera();
  public static Compressor compressor = new Compressor();
  public static Gyro gyro = new Gyro();
  // public static UltrasonicArray ultrasonic = new UltrasonicArray();
  
  @Override
  public void robotInit() {
    SubsystemManager.init();
    SubsystemManager.initSD();
    compressor.setClosedLoopControl(true);
  }

  @Override
  public void robotPeriodic() {
    SubsystemManager.update();
    SubsystemManager.updateSD();
  }
  
  @Override
  public void disabledInit() {
    SubsystemManager.initDisabled();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    SubsystemManager.updateDisabled();
  }


  @Override
  public void autonomousInit() {
    SubsystemManager.initEnabled();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    SubsystemManager.updateEnabled();
  }

  @Override
  public void teleopInit() {
    SubsystemManager.initEnabled();
    OI.init();

  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SubsystemManager.updateEnabled();
    OI.update();
  }

  @Override
  public void testInit() {
    SubsystemManager.initEnabled();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
    SubsystemManager.updateEnabled();
  }
}
