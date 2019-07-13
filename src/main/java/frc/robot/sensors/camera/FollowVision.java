package frc.robot.sensors.camera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;
import frc.robot.oi.OI;

public class FollowVision extends Command {
  public final int VISION_TARGET = 200;

  public static double k_dist = -0.011; // this is negative as a larger value means we are closer to the target 
  public static double k_pos =  0.0125;

  public static double k_angle =  1;
  // public static double l_dist = 0.25; //THIS ONE
  public static double l_dist = 0.18;
  public static double l_pos = 0.008;
  public static double areaCorrection = .1;

  public static final double END_AREA = 10;
  public double endCounts;
  // public static double l_pos = 0;
  public static double l_angle = .01;
  private boolean endable;
  // public static double k_angle = 0;
  private double area;
  private double counts;

  protected LimeData data;
 
  public FollowVision(boolean endable, double endCounts) {
    // setInterruptible(true);
    requires(Robot.drivetrain);
    requires(Robot.camera);
    this.endable = endable;
    this.endCounts = endCounts;
  }
  public FollowVision(boolean endable) {
    // setInterruptible(true);
    requires(Robot.drivetrain);
    requires(Robot.camera);
    this.endable = endable;
  }

  @Override
  protected void initialize() {
    k_dist = l_dist;
    k_pos = l_pos;
    k_angle = l_angle;
  }

  @Override
  protected void execute() {
    // if(OI.driver.getAButton()) {
    //   // OI.init();
    //   cancel();
    //   // end();
    // }
    data = Robot.camera.getData();
    counts = Robot.drivetrain.getAvgEncPosition();
    SmartDashboard.putNumber("VisionCounts", counts);

    // if (data.hasTarget()) {
    //   double distError = data.getDistance() - VISION_TARGET;
    //   // distError = data.getDistance() - VISION_TARGET;
    //   double distValue = distError * k_dist;

    //   double posError = data.getPosition();
    //   double posValue = posError * k_pos;

    //   double angleError = data.getAngle();
    //   double angleValue = 0;//angleError * k_angle;

    //   SmartDashboard.putNumber("Angle Value", angleValue);
    //   System.out.println(angleValue);
      
    //   double left = (distValue + posValue + angleValue) / 10.0;
    //   double right = (distValue - posValue - angleValue) / 10.0;

    //   SmartDashboard.putNumber("LEFT_LIME_POWER", left);
    //   SmartDashboard.putNumber("RIGHT_LIME_POWER", right);

    //   Robot.drivetrain.setSpeed(left, right);

    //   area = data.getArea();
    //   SmartDashboard.putNumber("LIME AREA", area);

    // }
    
    if (data.hasTarget()) {
      area = data.getArea();
      double posError = data.getPosition();
      double posValue = (posError * k_pos) * Math.sqrt(Helper.boundValue(area * areaCorrection, 0, 1));

      // double angleError = data.getAngle();
      // double angleValue = angleError * k_angle;

      SmartDashboard.putNumber("Pos Value", posValue);
      SmartDashboard.putNumber("Area of target", area);
      // SmartDashboard.putString("hello", "i wok");

      // double manual_y = Helper.deadzone(-OI.driver.getLY(), .03);

      // double left = manual_y + ((posValue + angleValue)); /// 10.0);
      // double right = manual_y - ((posValue + angleValue));// / 10.0);

      double left = Helper.boundValue((1/Math.sqrt(area)) * k_dist + posValue);// + posValue;
      double right = Helper.boundValue((1/Math.sqrt(area)) * k_dist - posValue);// + posValue;
      //area at placing distance is ~6

      SmartDashboard.putNumber("LEFT_LIME_POWER", left);
      SmartDashboard.putNumber("RIGHT_LIME_POWER", right);


      Robot.drivetrain.setSpeed(left, right);
  }
  }

  @Override
  protected boolean isFinished() {
      // return distError <= 0;
      SmartDashboard.putBoolean("Follow Vision is finished", area > END_AREA);
      if(endable) {
        return area > END_AREA;// || counts > endCounts;
      }
      return false;
  }

  @Override
  protected void end() {
    // System.out.println("=================== Follow vision Ended");
    Robot.drivetrain.resetEncPositions();
    Robot.drivetrain.stop();
  }

  // @Override
  // protected void interrupted() {
  //   OI.init();
  // }
}
