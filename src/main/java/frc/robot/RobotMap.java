package frc.robot;

public class RobotMap {

  public static class Drivetrain {
    public static final int LEFT_PRIMARY = 0;
    public static final int LEFT_SECONDARY = 1;
    public static final int LEFT_TERTIARY = 1;
    public static final int RIGHT_PRIMARY = 2;
    public static final int RIGHT_SECONDARY = 3;
    public static final int RIGHT_TERTIARY = 0;
 
    public static final boolean IS_LEFT_INVERTED = false;
    public static final boolean IS_RIGHT_INVERTED = true;
  }

  public static class Powerpack {
    public static final int POWERPACK_PRIMARY = 7;
    public static final int POWERPACK_SECONDARY = 8;
    public static final int POWERPACK_TERTIARY = 9;
    public static final int POWERPACK_QUATERNARY = 10;
    public static final int POWERPACK_SWITCHER = 0;

    public static final int DRIVE = 6;

    public static final int RAMP_LEFT = 1;
    public static final int RAMP_RIGHT = 1;

    public static final int FORKS_LEFT = 2;
    public static final int FORKS_RIGHT = 3;
  }
  
  public static class Carraige {
    public static final int TILT = 11;
    public static final int BALL_INTAKE = 12;
    public static final int HATCH_CLAW = 1;
    public static final int HATCH_PICKUP_TILT = 13;
    public static final int HATCH_PICKUP_INTAKE = 14;
  }

  /**contains mappings for an xbox controller's buttons to their int's
   * current values need to be varified
   */
  public class Controls{
    public static final int DRIVER = 0;
    public static final int OPERATOR = 1;
    
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

    public static final int LX = 0;
    public static final int LY = 1;
    public static final int RX = 2;
    public static final int RY = 3;
    public static final int LT = 4;
    public static final int RT = 5;
  }
}
