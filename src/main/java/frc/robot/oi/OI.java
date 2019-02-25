package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.carriage.ballintake.IntakeUntilBallDetected;
import frc.robot.carriage.groundpickup.GiveToClaw;
import frc.robot.carriage.groundpickup.OpenAndIntake;
import frc.robot.carriage.hatchclaw.GrabWhenDetected;
import frc.robot.carriage.hatchclaw.ToggleClaw;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.SetTiltSpeed;
import frc.robot.climberelevator.footdrive.SetFootDriveSpeed;
import frc.robot.climberelevator.forks.DeployForks;
import frc.robot.climberelevator.powerpack.AutoElevatorTilt;
import frc.robot.climberelevator.powerpack.SetClimberSpeed;
import frc.robot.climberelevator.powerpack.SetElevatorSpeed;
import frc.robot.climberelevator.powerpack.ThirdLevelClimb;
import frc.robot.climberelevator.ramps.DeployRamps;
import frc.robot.helpers.oi.XboxControllerPlus;

public class OI {
  
  public static XboxControllerPlus driver;
  public static XboxControllerPlus operator;

  public static void init(){
    driver = new XboxControllerPlus(RobotMap.Controls.DRIVER);
    operator = new XboxControllerPlus(RobotMap.Controls.OPERATOR); 

    SmartDashboard.putBoolean("has OI", true);
    (new ArcadeDrive()).start();
    (new TriggerIntakeControl()).start();

    driver.lb.whenPressed(new ToggleClaw());
    operator.lb.whenPressed(new ToggleClaw());

    // driver.a.whileHeld(new FollowVision());
    driver.b.pressToStartReleaseToStop(new GrabWhenDetected());
    driver.x.pressToStartReleaseToStop(new IntakeUntilBallDetected());

    operator.lstk.pressToStartReleaseToStop(new ManualElevator(), new SetElevatorSpeed(0));
    operator.rstk.pressToStartReleaseToStop(new ManualTilt(), new SetTiltSpeed(0));

    operator.x.whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.intakeBall));

    operator.povN.whenPressed(new SetTiltPosition(SetTiltPosition.UPPER));
    operator.povE.whenPressed(new SetTiltPosition(SetTiltPosition.MIDDLE));
    operator.povS.whenPressed(new SetTiltPosition(SetTiltPosition.LOWER));

    operator.start.whenPressed(new OpenAndIntake());
    operator.menu.whenPressed(new GiveToClaw());

    driver.rb.and(driver.start).whenPressed(new DeployForks());
    driver.rb.and(driver.menu).whenPressed(new DeployRamps());
    driver.rb.and(driver.y).whenPressed(new ThirdLevelClimb(false));

    driver.rb.and(driver.povN).pressToStartReleaseToStop(new SetClimberSpeed(0.5), new SetClimberSpeed(0));
    driver.rb.and(driver.povS).pressToStartReleaseToStop(new SetClimberSpeed(-0.5), new SetClimberSpeed(0));

    driver.rb.and(driver.povE).pressToStartReleaseToStop(new SetFootDriveSpeed(0.5), new SetFootDriveSpeed(0));
    driver.rb.and(driver.povW).pressToStartReleaseToStop(new SetFootDriveSpeed(-0.5), new SetFootDriveSpeed(0));
  
    operator.rb.and(operator.a).whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.ballLower));
    operator.rb.and(operator.b).whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.ballMiddle));
    operator.rb.and(operator.y).whenPressed(new AutoElevatorTilt(AutoElevatorTilt.State.ballUpper));
  }

  public static void update() {

  }

}
