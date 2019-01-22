package frc.robot;

public class RobotMap {

  public class Drivetrain {
    public static final int LEFT_PRIMARY = 0;
    public static final int LEFT_SECONDARY = 1;
    public static final int RIGHT_PRIMARY = 2;
    public static final int RIGHT_SECONDARY = 3;
  }

  public class Climber {
    public static final int MOTOR = 4;
  }
  
  public class HatchClaw {
    public static final int SOLENOID = 0;
  }

  /**contains mappings for an xbox controller and its buttons
   * current values need to be varified
   */
  public class ControllerMapping{
    public static final int A = 0;
    public static final int B = 1;
    public static final int X = 2;
    public static final int Y = 3;
    public static final int RB = 6;
    public static final int LB = 7;
    public static final int RSTK = 4;
    public static final int LSTK = 5;
    public static final int START = 8;
    public static final int MENU = 9;
  }
}
