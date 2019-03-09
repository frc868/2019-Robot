package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.carriage.ballintake.IntakeUntilBallDetected;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.hatchclaw.GrabWhenDetected;
import frc.robot.carriage.hatchclaw.ToggleClaw;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.climberelevator.footdrive.SetFootDriveSpeed;
import frc.robot.climberelevator.forks.DeployForks;
import frc.robot.climberelevator.powerpack.AutoClimb;
import frc.robot.climberelevator.powerpack.PowerPack;
import frc.robot.climberelevator.powerpack.SetClimberSpeed;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
import frc.robot.climberelevator.powerpack.SwitchToClimber;
import frc.robot.climberelevator.powerpack.TogglePowerpackMode;
import frc.robot.climberelevator.ramps.DeployRamps;
import frc.robot.helpers.oi.XboxControllerPlus;
import frc.robot.carriage.tilt.Tilt;

public class OI {
  
  public static XboxControllerPlus driver;
  public static XboxControllerPlus operator;

  public static void init(){
    driver = new XboxControllerPlus(RobotMap.Controls.DRIVER);
    operator = new XboxControllerPlus(RobotMap.Controls.OPERATOR); 

    //commands involving manual control and variable input
    Robot.drivetrain.setDefaultCommand(new ArcadeDrive());
    Robot.ballIntake.setDefaultCommand(new ManualIntake());   
    Robot.tilt.setDefaultCommand(new ManualTilt());
    Robot.powerPack.setDefaultCommand(new ManualPowerpack());  //powerpack so we can toggle elevator vs climber instead of just ManualElevator



    //MAIN OPERATOR CONTROLS
    operator.povN.whenPressed(new SetTiltPosition(Tilt.UPPER));
    operator.povE.whenPressed(new SetTiltPosition(Tilt.MIDDLE));
    operator.povS.whenPressed(new SetTiltPosition(Tilt.LOWER));
    
    operator.a.whenPressed(new SetElevatorPosition(PowerPack.LOWER));
    operator.b.whenPressed(new SetElevatorPosition(PowerPack.MIDDLE));
    // operator.x.whenPressed(new SetElevatorPosition(PowerPack.INTAKE_BALL));  //TODO: fix PID so it doesn't slam into the bottom
    operator.y.whenPressed(new SetElevatorPosition(PowerPack.UPPER));
    
    operator.lb.whenPressed(new ToggleClaw());
  

    //ENDGAME
    driver.start.whenPressed(new DeployForks());
    driver.menu.whenPressed(new DeployRamps());
    driver.menu.whenPressed(new SetFootDriveSpeed(0));
    driver.menu.whenPressed(new SetTiltPosition(Tilt.UPPER));
    driver.menu.whenPressed(new Grab());
    driver.menu.whenPressed(new SwitchToClimber());
    driver.rb.and(driver.y).whenPressed(new AutoClimb(true));


    // STUFF WE'RE NOT USING RIGHT NOW
    // Operator GrountHatch control, not implemented on robot
    // operator.start.whenPressed(new OpenAndIntake());
    // operator.menu.whenPressed(new GiveToClaw());

    // operator.lstk.pressToStartReleaseToStop(new ManualElevator(), new SetElevatorSpeed(0));
    // operator.rstk.pressToStartReleaseToStop(new ManualTilt(), new SetTiltSpeed(0));

    //Toggle powerpack mode, use for debug only
    // operator.rb.whenPressed(new TogglePowerpackMode());
    
    // driver.a.whileHeld(new FollowVision());
    // driver.b.pressToStartReleaseToStop(new GrabWhenDetected());
    // driver.x.pressToStartReleaseToStop(new IntakeUntilBallDetected());

  }

  public static void update() {

  }

}
