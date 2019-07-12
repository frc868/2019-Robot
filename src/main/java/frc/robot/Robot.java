package frc.robot;

import java.util.HashMap;
// import java.util.function.IntConsumer;
// import java.util.stream.IntStream;
// import frc.robot.sensors.ultrasonic.UltrasonicArray;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.auton.commands.DoNothing;
import frc.robot.auton.commands.LHab1ToFrontRocket;
import frc.robot.auton.commands.LHab2ToFrontRocket;
import frc.robot.auton.commands.RHab1ToFrontRocket;
import frc.robot.auton.commands.RHab2ToFrontRocket;
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
import frc.robot.oi.ArcadeDrive;
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

    private final SendableChooser<String> chooser = new SendableChooser<>();
    private String selectedAuto;
    private static final String nothing = "Nothing";
    private static final String left = "Left rocket single";
    private static final String left2 = "Left hab 2 rocket single";
    private static final String right = "Right rocket single";
    private static final String right2 = "Right hab 2 rocket single";

    private Command leftRocketAuto, rightRocketAuto, noAuto, leftRocketHab2Auto, rightRocketHab2Auto;
    private Command selectedCommand;

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
        chooser.setDefaultOption("Nothing", nothing);
        chooser.addOption("Left Rocket", left);
        chooser.addOption("Right Rocket", right);
        chooser.addOption("Left Rocket Hab 2", left2);
        chooser.addOption("Right Rocket Hab 2", right2);
        SmartDashboard.putData(chooser);
        leftRocketAuto = new LHab1ToFrontRocket(1);
        rightRocketAuto = new RHab1ToFrontRocket(1);
        leftRocketHab2Auto = new LHab2ToFrontRocket(1);
        rightRocketHab2Auto = new RHab2ToFrontRocket(1);
        noAuto = new DoNothing();

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
        selectedAuto = chooser.getSelected();
        SmartDashboard.putString("Selected autonomous:", selectedAuto);
        SubsystemManager.initEnabled();
        switch(selectedAuto) {
            case(nothing): {
                // System.out.println(leftRocketAuto.isRunning() + "  ====LEFT RUNNING"); // both return false when testing
                // System.out.println(rightRocketAuto.isRunning() + "  ====RIGHT RUNNING");
                // noAuto.start();
                OI.init();
                break;
            }
            case(left): {
                selectedCommand = leftRocketAuto;
                leftRocketAuto.start();
                // OI.init();
                break;
            }
            case(right): {
                selectedCommand = rightRocketAuto;
                rightRocketAuto.start();
                // OI.init();
                break;
            }
            case(left2): {
                selectedCommand = leftRocketHab2Auto;
                leftRocketHab2Auto.start();
                break;
            }
            case(right2): {
                selectedCommand = rightRocketHab2Auto;
                rightRocketHab2Auto.start();
                break;
            }
        }
        OI.init();
    }
    
    @Override
    public void autonomousPeriodic() {
        if(OI.driver.getAButton()) {
            selectedCommand.cancel();
            OI.init();
        }
        Scheduler.getInstance().run();
        SubsystemManager.updateEnabled();
        OI.update();
    }
    
    @Override
    public void teleopInit() {
        SubsystemManager.initEnabled();
        selectedCommand.cancel();
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
