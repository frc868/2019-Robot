package frc.robot.oi;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.carriage.hatchclaw.GrabWhenTiltUp;
import frc.robot.carriage.hatchclaw.ToggleClaw;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
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

        driver.lb.whenPressed(new ToggleClaw());

        driver.menu.whenPressed(new DeployRamps());
        driver.menu.whenPressed(new ManualClimber());

        // OPERATOR CONTROLS
        operator.a.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.lower));
        operator.b.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.middle));
        operator.y.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.upper));
        operator.x.whenPressed(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.ballIntake));

        operator.lb.whenPressed(new ToggleClaw());
        operator.rb.pressToStartReleaseToStop(new ManualElevator());
        operator.menu.pressToStartReleaseToStop(new ManualTilt());
        operator.start.pressToStartReleaseToStop(new GrabWhenTiltUp());

        operator.povN.whenPressed(new SetTiltPosition(Tilt.UPPER));
        operator.povE.whenPressed(new SetTiltPosition(Tilt.MIDDLE));
        operator.povS.whenPressed(new SetTiltPosition(Tilt.LOWER));
    }

    public static void update() {            

    }   

}
