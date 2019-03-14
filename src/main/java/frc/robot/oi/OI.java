package frc.robot.oi;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.carriage.ballintake.IntakeUntilBallDetected;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.hatchclaw.GrabWhenDetected;
import frc.robot.carriage.hatchclaw.ToggleClaw;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.footdrive.SetFootDriveSpeed;
import frc.robot.climberelevator.forks.DeployForks;
import frc.robot.climberelevator.powerpack.AutoClimb;
import frc.robot.climberelevator.powerpack.PowerPack;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
import frc.robot.climberelevator.powerpack.SwitchToClimber;
import frc.robot.climberelevator.powerpack.TogglePowerpackMode;
import frc.robot.climberelevator.ramps.DeployRamps;
import frc.robot.helpers.oi.XboxControllerPlus;
// import frc.robot.sensors.camera.FollowVision;
import frc.robot.drivetrain.commands.DriveStraight;
import frc.robot.drivetrain.commands.RunProfile;

public class OI {
    public static XboxControllerPlus driver;
    public static XboxControllerPlus operator;

    public static void init(){
        driver = new XboxControllerPlus(RobotMap.Controls.DRIVER);
        operator = new XboxControllerPlus(RobotMap.Controls.OPERATOR);

        // MANUAL CONTROLS
        Robot.drivetrain.setDefaultCommand(new ArcadeDrive());      //driving controls
        Robot.ballIntake.setDefaultCommand(new ManualIntake());     //ball intake

        Robot.powerPack.setDefaultCommand(new ManualPowerpack());

        // DRIVER CONTROLS 
        // driver.a.whileHeld(new FollowVision());
        driver.b.pressToStartReleaseToStop(new GrabWhenDetected());     //grabs hatch when detected by both sensors
        driver.x.pressToStartReleaseToStop(new IntakeUntilBallDetected());  //runs intake until ball is detected
        // driver.y.and(driver.rb).whenPressed(new AutoClimb(true));       //automatic climbing, UNTESTED

        // driver.rb.whenPressed(new ManualClimber());     //switches to climber for endgame
        driver.lb.whenPressed(new ToggleClaw());        //toggles hatch manipulator

        driver.start.whenPressed(new DeployForks());    //deploys forks for endgame use
        driver.menu.whenPressed(new DeployRamps());     //deploys ramps for endgame use
        driver.menu.whenPressed(new SetFootDriveSpeed(0));      //TODO: TEMP FIX FOR CLIMBING
        driver.menu.whenPressed(new SetTiltPosition(Tilt.UPPER));       //TODO: TEMP FIX FOR CLIMBING
        driver.menu.whenPressed(new Grab());        //TODO: TEMP FIX FOR CLIMBING
        driver.menu.whenPressed(new SwitchToClimber());     //TODO: TEMP FIX FOR CLIMBING
        // driver.rb.and(driver.y).whenPressed(new AutoClimb(true));       //TODO: TEMP FIX FOR CLIMBING

        // driver.a.and(driver.b).whenPressed(new DriveStraight(24,0.5)); //TODO: delete this at some point it's just for testing
        driver.a.and(driver.b.and(driver.rb)).whenPressed(new RunProfile("StartToRightFrontShip.pf1")); //for testing 3/14/19


        // OPERATOR CONTROLS
        operator.a.whenPressed(new SetElevatorPosition(PowerPack.LOWER));       //Elevator height
        operator.b.whenPressed(new SetElevatorPosition(PowerPack.MIDDLE));
        operator.x.whenPressed(new SetElevatorPosition(PowerPack.INTAKE_BALL));  //TODO: fix PID so it doesn't slam into the bottom
        // operator.x.whenPressed(new Grab());
        // operator.x.whenReleased(new SetTiltPosition(Tilt.LOWER));
        operator.y.whenPressed(new SetElevatorPosition(PowerPack.UPPER));
        // operator.y.whenPressed(new SetTiltPosition(Tilt.MIDDLE));

        operator.lb.whenPressed(new ToggleClaw());      //toggles hatch manipulator
        // operator.rb.whenPressed(new ManualTilt());      //take over manual tilt control
        operator.rb.whenPressed(new TogglePowerpackMode());  //TODO: TEMP FIX FOR CLIMBING

        operator.povN.whenPressed(new SetTiltPosition(Tilt.UPPER));     //Carriage tilt
        operator.povE.whenPressed(new SetTiltPosition(Tilt.MIDDLE));
        operator.povS.whenPressed(new SetTiltPosition(Tilt.LOWER));

        // STUFF WE'RE NOT USING RIGHT NOW
        // Operator GrountHatch control, not implemented on robot
        // operator.start.whenPressed(new OpenAndIntake());
        // operator.menu.whenPressed(new GiveToClaw());
    }

    public static void update() {

    }

}
