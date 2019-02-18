package frc.robot;

public class RobotMap {

  public static class Drivetrain {
    public static final int LEFT_PRIMARY = 13;
    public static final int LEFT_SECONDARY = 14;
    public static final int LEFT_TERTIARY = 15;
    public static final int LEFT_ENCODER_A = 24;
    public static final int LEFT_ENCODER_B = 26;

    public static final int RIGHT_PRIMARY = 0;
    public static final int RIGHT_SECONDARY = 1;
    public static final int RIGHT_TERTIARY = 2;
    public static final int RIGHT_ENCODER_A = 24;
    public static final int RIGHT_ENCODER_B = 26;

    public static final int GYRO = 0;
  }

  public static class ClimberElevator {
    public static class Powerpack {
      public static final int PRIMARY = 13;//7;
      public static final int SECONDARY = 1;//8;
      public static final int SWITCHER = 0;
      public static final int BRAKE = 1;
      public static final int TOP_LIMIT = 0;
      public static final int BOTTOM_LIMIT = 0;
    }

    public static class FootDrive {
      public static final int MOTOR = 14;//6;
    }

    public static class Ramps {
      public static final int RAMPS = 7; 
    }

    public static class Forks {
      public static final int FORKS = 2; // idk
      //public static final int LEFT = 2;
      //public static final int RIGHT = 3;
    }
  }
  
  public static class Carriage {
    public static class Tilt {
      public static final int MOTOR = 11;
      public static final int ENCODER_A = 0;
      public static final int ENCODER_B = 1;
    }

    public static class BallIntake {
      public static final int MOTOR = 20;
      public static final int LIMIT = 0;
    }

    public static class HatchClaw {
      public static final int ACTUATOR = 1;
      public static final int LEFT_LIMIT = 0;
      public static final int RIGHT_LIMIT = 0;
    }
   
    public static class GroundPickup {
      public static final int WRIST = 13;
      public static final int INTAKE = 14;
      public static final int WRIST_ENCODER_A = 0;
      public static final int WRIST_ENCODER_B = 1;
      public static final int FAILSAFE = 0; // TODO: don't know what this is yet...
    }
  }

  public class Controls{
    public static final int DRIVER = 0;
    public static final int OPERATOR = 1;
  }
}
