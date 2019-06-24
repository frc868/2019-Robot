package frc.robot.oi;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.auton.commands.RHab2ToFrontRocket;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.hatchclaw.GrabWhenTiltUp;
import frc.robot.carriage.hatchclaw.ToggleClaw;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.powerpack.SmartSetElevatorPosition;
import frc.robot.climberelevator.ramps.DeployRamps;
import frc.robot.drivetrain.commands.TurnToAngleGyro;
import frc.robot.helpers.oi.XboxControllerPlus;
public class OI {
    public static XboxControllerPlus driver;
    public static XboxControllerPlus operator;

    public static void init(){
        driver = new XboxControllerPlus(RobotMap.Controls.DRIVER);
        operator = new XboxControllerPlus(RobotMap.Controls.OPERATOR);

        // MANUAL CONTROLS
        Robot.drivetrain.setDefaultCommand(new ArcadeDrive());
        Robot.ballIntake.setDefaultCommand(new ManualIntake());

        // DRIVER CONTROLS 
        driver.a.pressToStartReleaseToStop(new ManualFollowVision());
        // driver.x.pressToStartReleaseToStop(new IntakeUntilBallDetected());
        // driver.y.and(driver.rb).whenPressed(new AutoClimb(true));

        driver.b.whenPressed(new TurnToAngleGyro(130));
        // driver.y.whenPressed(new DriveStraightNoPID(48,0.3,0.3));
        driver.y.whenPressed(new RHab2ToFrontRocket(1));
        driver.x.whenPressed(new TurnToAngleGyro(-130));

        driver.povW.whenPressed(new TurnToAngleGyro(-90));
        driver.povE.whenPressed(new TurnToAngleGyro(90));

        driver.lb.whenPressed(new ToggleClaw());

        driver.menu.whenPressed(new DeployRamps());
        driver.menu.whenPressed(new SetTiltPosition(Tilt.UPPER));
        driver.menu.whenPressed(new Grab());
        driver.start.whenPressed(new ManualClimber());

        // OPERATOR CONTROLS
        operator.a.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.lower));
        operator.b.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.middle));
        operator.y.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.upper));
        operator.x.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.ballIntake));

        operator.lstk.pressToStartReleaseToStop(new IntakeAssistTilt());

        // METHOD ONE
        // operator.x.whenActive(new ElevatorToBallHeight());

        // operator.a.and(Robot.ballIntake.detection_limit.getTrigger()).whenActive(new ElevatorToBallBottomHeight());
        // operator.a.not(Robot.ballIntake.detection_limit.getTrigger()).whenActive(new ElevatorToHatchBottomHeight());

        // operator.b.and(Robot.ballIntake.detection_limit.getTrigger()).whenActive(new ElevatorToBallMiddleHeight());
        // operator.b.not(Robot.ballIntake.detection_limit.getTrigger()).whenActive(new ElevatorToHatchMiddleHeight());

        // operator.y.and(Robot.ballIntake.detection_limit.getTrigger()).whenActive(new ElevatorToBallUpperHeight());
        // operator.y.not(Robot.ballIntake.detection_limit.getTrigger()).whenActive(new ElevatorToHatchUpperHeight());
       
        // METHOD TWO 
        // operator.a.whenPressed(new OtherSetElevatorPosition(OtherSetElevatorPosition.Height.lower));
        // operator.b.whenPressed(new OtherSetElevatorPosition(OtherSetElevatorPosition.Height.middle));
        // operator.y.whenPressed(new OtherSetElevatorPosition(OtherSetElevatorPosition.Height.upper));
        // operator.x.whenPressed(new OtherSetElevatorPosition(OtherSetElevatorPosition.Height.intake));

        // METHOD THREE
        // // operator.a.whenPressed(new BallOrHatch(new ElevatorToHatchBottomHeight(), new ElevatorToBallBottomHeight()));
        // operator.b.whenPressed(new BallOrHatch(new ElevatorToHatchMiddleHeight(), new ElevatorToBallMiddleHeight()));
        // operator.y.whenPressed(new BallOrHatch(new ElevatorToHatchUpperHeight(), new ElevatorToBallUpperHeight()));
        // operator.x.whenPressed(new ElevatorToBallHeight());

        operator.lb.whenPressed(new ToggleClaw());
        operator.rb.pressToStartReleaseToStop(new ManualElevator());
        operator.menu.pressToStartReleaseToStop(new ManualTilt());
        operator.start.pressToStartReleaseToStop(new GrabWhenTiltUp());
        // operator.start.whenPressed(new DeployForks());
        // operator.menu.whenPressed(new DeployRamps());
        // operator.menu.whenPressed(new ManualClimber());

        operator.povN.whenPressed(new SetTiltPosition(Tilt.UPPER));
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
