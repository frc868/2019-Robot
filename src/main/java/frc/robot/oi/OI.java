package frc.robot.oi;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.carriage.ballintake.IntakeUntilBallDetected;
import frc.robot.carriage.hatchclaw.GrabWhenDetected;
import frc.robot.carriage.hatchclaw.ToggleClaw;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.forks.DeployForks;
import frc.robot.climberelevator.powerpack.AutoClimb;
import frc.robot.climberelevator.powerpack.SmartSetElevatorPosition;
import frc.robot.climberelevator.ramps.DeployRamps;
import frc.robot.helpers.oi.XboxControllerPlus;
// import frc.robot.sensors.camera.FollowVision;

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
        // driver.a.whileHeld(new FollowVision());
        driver.b.pressToStartReleaseToStop(new GrabWhenDetected());
        driver.x.pressToStartReleaseToStop(new IntakeUntilBallDetected());
        // driver.y.and(driver.rb).whenPressed(new AutoClimb(true));

        driver.lb.whenPressed(new ToggleClaw());

        driver.start.whenPressed(new DeployForks());
        driver.menu.whenPressed(new DeployRamps());
        driver.menu.whenPressed(new ManualClimber());

        // OPERATOR CONTROLS
        operator.a.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.lower));
        operator.b.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.middle));
        operator.y.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.upper));
        operator.x.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.ballIntake));

        operator.lb.whenPressed(new ToggleClaw());
        operator.start.pressToStartReleaseToStop(new ManualTilt());
        operator.menu.pressToStartReleaseToStop(new ManualElevator());

        // operator.start.whenPressed(new DeployForks());
        // operator.menu.whenPressed(new DeployRamps());
        // operator.menu.whenPressed(new ManualClimber());

        // operator.povN.whenPressed(new SetTiltPosition(Tilt.UPPER));
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
