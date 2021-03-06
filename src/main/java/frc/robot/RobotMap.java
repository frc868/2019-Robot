package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI.Port;

public class RobotMap {

  public static final int PCM = 0; // TESTED

  public static class Drivetrain {
    public static final int LEFT_PRIMARY = 23; // SEMITESTED: the robot moves but we don't know if all these motors are properly mapped
    public static final int LEFT_SECONDARY = 24; // SEMITESTED: ^^^
    public static final int LEFT_TERTIARY = 25; // SEMITESTED: ^^^

    public static final int RIGHT_PRIMARY = 10; // SEMITESTED: ^^^
    public static final int RIGHT_SECONDARY = 11; // SEMITESTED: ^^^
    public static final int RIGHT_TERTIARY = 12; // SEMITESTED: ^^^
  }

  public static class ClimberElevator {
    public static class Powerpack {
      public static final int PRIMARY = 13; // TESTED
      public static final int SECONDARY = 22; // TESTED

      public static final int SWITCHER = 2;
      public static final int ELEVATOR_BRAKE = 3;
      public static final int CLIMBER_BRAKE = 4;
      
      public static final int ELEVATOR_TOP_LIMIT = 0;
      public static final int ELEVATOR_BOTTOM_LIMIT = 1;
      //public static final int CLIMBER_TOP_LIMIT = 5;
      //public static final int CLIMBER_BOTTOM_LIMIT = 6;

      public static final int CLIMBER_LIMIT_SWITCH = 4;
    }

    public static class FootDrive {
      public static final int MOTOR = 21;
    }

    public static class Ramps {
      public static final int ACTUATOR = 5; 
    }

    public static class Forks {
      public static final int RELEASE = 6; // idk
      //public static final int LEFT = 2;
      //public static final int RIGHT = 3;
    }
  }
  
  public static class Carriage {
    public static class Tilt {
      public static final int MOTOR = 15; // TESTED
      public static final int POTENTIOMETER = 0;
      public static final int BRAKE = 0;
    }

    public static class BallIntake {
      public static final int MOTOR = 14; // TESTED
      public static final int DETECTION_LIMIT = 2;
    }

    public static class HatchClaw {
      public static final int ACTUATOR = 1;
      public static final int DETECTION_LIMIT = 2;
    }
   
    public static class GroundPickup {
      public static final int WRIST = 13;
      public static final int INTAKE = 14;
      public static final int WRIST_ENCODER_A = 0;
      public static final int WRIST_ENCODER_B = 1;
      public static final int DETECTION_LIMIT = 0; 
    }
  }

  public static class Sensors {
    public static class Camera {
      public static final SerialPort.Port PORT = SerialPort.Port.kUSB1;
    }

    public static class Ultrasonic {
      //public static final int FRONT_TRIGGER = 0;
      //public static final int FRONT_ECHO = 1;
      public static final int LEFT_TRIGGER = 5;
      public static final int LEFT_ECHO = 6;
      public static final int RIGHT_TRIGGER = 7;
      public static final int RIGHT_ECHO = 8;


    }

    public static class Gyro {
      public static final Port GYRO = SPI.Port.kMXP;
      public static final int OLD_GYRO = 1;
    }
  }

  public static class Controls{
    public static final int DRIVER = 0;
    public static final int OPERATOR = 1;
  }
}
