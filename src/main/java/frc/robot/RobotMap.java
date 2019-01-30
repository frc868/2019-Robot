package frc.robot;

import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;

public class RobotMap {

  public static class Drivetrain {
    public static final int LEFT_PRIMARY = 0;
    public static final int LEFT_SECONDARY = 1;
    public static final int RIGHT_PRIMARY = 2;
    public static final int RIGHT_SECONDARY = 3;

    public static final boolean IS_LEFT_INVERTED = false;
    public static final boolean IS_RIGHT_INVERTED = true;
  }

  public static class Climber {
    public static final int FOOT_PRIMARY = 4;
    public static final int FOOT_SECONDARY = 5;
    public static final int DRIVE = 6;
    public static final int RAMP = 0;

    public static final boolean IS_FOOT_INVERTED = false;
    public static final boolean IS_DRIVE_INVERTED = false;

    public static final LimitSwitchPolarity FORWARD_LIMIT_SWITCH_POLARITY = LimitSwitchPolarity.kNormallyClosed;
    public static final LimitSwitchPolarity REVERSE_LIMIT_SWITCH_POLARITY = LimitSwitchPolarity.kNormallyClosed;
  }
  
  public static class HatchClaw {
    public static final int SOLENOID = 1;
  }

  public static class Forks {
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
  }
}
