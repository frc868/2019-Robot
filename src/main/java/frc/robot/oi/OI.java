package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.carriage.ballintake.IntakeUntilBallDetected;
import frc.robot.carriage.hatchclaw.GrabWhenDetected;
import frc.robot.carriage.hatchclaw.ToggleClaw;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.climberelevator.footdrive.SetFootDriveSpeed;
import frc.robot.climberelevator.forks.DeployForks;
import frc.robot.climberelevator.powerpack.AutoClimb;
import frc.robot.climberelevator.powerpack.PowerPack;
import frc.robot.climberelevator.powerpack.SetClimberSpeed;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
import frc.robot.climberelevator.ramps.DeployRamps;
import frc.robot.helpers.oi.XboxControllerPlus;
import frc.robot.carriage.tilt.Tilt;

public class OI {
  
  public static XboxControllerPlus driver;
  public static XboxControllerPlus operator;

  public static void init(){
    driver = new XboxControllerPlus(RobotMap.Controls.DRIVER);
    operator = new XboxControllerPlus(RobotMap.Controls.OPERATOR); 

    Robot.drivetrain.setDefaultCommand(new ArcadeDrive());
    Robot.ballIntake.setDefaultCommand(new ManualIntake());
    Robot.tilt.setDefaultCommand(new ManualTilt());
    Robot.powerPack.setDefaultCommand(new ManualElevator());

    driver.lb.whenPressed(new ToggleClaw());
    operator.lb.whenPressed(new ToggleClaw());

    // driver.a.whileHeld(new FollowVision());
    driver.b.pressToStartReleaseToStop(new GrabWhenDetected());
    driver.x.pressToStartReleaseToStop(new IntakeUntilBallDetected());

    // operator.lstk.pressToStartReleaseToStop(new ManualElevator(), new SetElevatorSpeed(0));
    // operator.rstk.pressToStartReleaseToStop(new ManualTilt(), new SetTiltSpeed(0));

    operator.povN.whenPressed(new SetTiltPosition(Tilt.UPPER));
    operator.povE.whenPressed(new SetTiltPosition(Tilt.MIDDLE));
    operator.povS.whenPressed(new SetTiltPosition(Tilt.LOWER));
  
    // operator.start.whenPressed(new OpenAndIntake());
    // operator.menu.whenPressed(new GiveToClaw());

    driver.rb.and(driver.start).whenPressed(new DeployForks());
    driver.rb.and(driver.menu).whenPressed(new DeployRamps());
    driver.rb.and(driver.y).whenPressed(new AutoClimb(true));

    driver.rb.and(driver.povN).pressToStartReleaseToStop(new SetClimberSpeed(0.5), new SetClimberSpeed(0));
    driver.rb.and(driver.povS).pressToStartReleaseToStop(new SetClimberSpeed(-0.5), new SetClimberSpeed(0));

    driver.rb.and(driver.povE).pressToStartReleaseToStop(new SetFootDriveSpeed(0.5), new SetFootDriveSpeed(0));
    driver.rb.and(driver.povW).pressToStartReleaseToStop(new SetFootDriveSpeed(-0.5), new SetFootDriveSpeed(0));
  
    operator.a.whenPressed(new SetElevatorPosition(PowerPack.LOWER));
    operator.b.whenPressed(new SetElevatorPosition(PowerPack.MIDDLE));
    // operator.x.whenPressed(new SetElevatorPosition(PowerPack.INTAKE_BALL));
    operator.y.whenPressed(new SetElevatorPosition(PowerPack.UPPER));
  }

  public static void update() {
  }

}
