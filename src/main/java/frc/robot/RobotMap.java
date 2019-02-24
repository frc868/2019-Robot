package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;

public class RobotMap {

  public static final int PCM = 16; // TESTED

  public static class Drivetrain {
    public static final int LEFT_PRIMARY = 13; // SEMITESTED: the robot moves but we don't know if all these motors are properly mapped
    public static final int LEFT_SECONDARY = 14; // SEMITESTED: ^^^
    public static final int LEFT_TERTIARY = 15; // SEMITESTED: ^^^

    public static final int RIGHT_PRIMARY = 0; // SEMITESTED: ^^^
    public static final int RIGHT_SECONDARY = 1; // SEMITESTED: ^^^
    public static final int RIGHT_TERTIARY = 2; // SEMITESTED: ^^^

    public static final int GYRO = 1; // TESTED
  }

  public static class ClimberElevator {
    public static class Powerpack {
      public static final int PRIMARY = 3; // TESTED
      public static final int SECONDARY = 12; // TESTED
      public static final int SWITCHER = 0;
      public static final int BRAKE = 1;
      public static final int TOP_LIMIT = 0;
      public static final int BOTTOM_LIMIT = 1;
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
      public static final int MOTOR = 5; // TESTED
    }

    public static class BallIntake {
      public static final int MOTOR = 4; // TESTED
      public static final int DETECTION_LIMIT = 0;
    }

    public static class HatchClaw {
      public static final int GRAB_SOLENOID = 4; // SEMITESTED: the two are 4 and 3 however, we don't know which is which
      public static final int RELEASE_SOLENOID = 3; // SEMITESTED: ^^^
      public static final int LEFT_LIMIT = 2;
      public static final int RIGHT_LIMIT = 3;
    }
   
    public static class GroundPickup {
      public static final int WRIST = 13;
      public static final int INTAKE = 14;
      public static final int WRIST_ENCODER_A = 0;
      public static final int WRIST_ENCODER_B = 1;
      // public static final int FORWARD_LIMIT = 0; 
      // public static final int REVERSE_LIMIT = 0; 
      // public static final int DETECTION_LIMIT = 0; 
    }
  }

  public static class Sensors {
    public static class Camera {
      public static final SerialPort.Port PORT = SerialPort.Port.kUSB1;
    }

    public static class Ultrasonic {
      public static final int FRONT_TRIGGER = 0;
      public static final int FRONT_ECHO = 1;
    }
  }

  public static class Controls{
    public static final int DRIVER = 0;
    public static final int OPERATOR = 1;
  }
}
