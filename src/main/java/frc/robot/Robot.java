package frc.robot;

import java.util.HashMap;
// import java.util.function.IntConsumer;
// import java.util.stream.IntStream;
// import frc.robot.sensors.ultrasonic.UltrasonicArray;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.auton.commands.LHab2ToFrontRocket;
import frc.robot.auton.commands.LHab2ToFrontRocketDouble;
// import frc.robot.auton.commands.LHab2ToFrontRocket;
// import frc.robot.auton.commands.DriveAndScoreHatch;
// import frc.robot.auton.paths.RightRocketFront;
import frc.robot.carriage.ballintake.BallIntake;
import frc.robot.carriage.hatchclaw.HatchClaw;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.footdrive.FootDrive;
import frc.robot.climberelevator.forks.Forks;
import frc.robot.climberelevator.powerpack.PowerPack;
import frc.robot.climberelevator.ramps.Ramps;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.helpers.motionprofiling.TrajectoryPair;
import frc.robot.helpers.subsystems.SubsystemManager;
import frc.robot.oi.OI;
import frc.robot.sensors.camera.LimeLight;
import frc.robot.sensors.gyro.Gyro;

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
    
    public static LimeLight camera = new LimeLight();
    // public static Camera camera = new Camera();
    public static Compressor compressor = new Compressor();
    public static Gyro gyro = new Gyro();
    public static HashMap<String, TrajectoryPair> paths = new HashMap<>();

    // public LHab2ToFrontRocket auton;
    
    // public final String[] pathNames = {"StartToRightCloseRocket"};
    // public final boolean[] pathDirection = {false};
    
    // public static PDP
    
    // public static UltrasonicArray ultrasonic = new UltrasonicArray();
    
    @Override
    public void robotInit() {
        SubsystemManager.init();
        SubsystemManager.initSD();
        // IntStream.range(0, pathNames.length - 1).forEach(new IntConsumer(){
        //     @Override
        //     public void accept(int i){
        //         paths.put(pathNames[i], new TrajectoryPair(pathNames[i], pathDirection[i]));
        //     }
        // });
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
        OI.init();
        (new LHab2ToFrontRocket(1)).start();
        // Scheduler.getInstance().add(new DriveAndScoreHatch("StartToRightCloseRocket", DriveAndScoreHatch.Height.lower));
        // Scheduler.getInstance().add(new RightRocketFront());
    }
    
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SubsystemManager.updateEnabled();
        OI.update();
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
