// package frc.robot.powerpack.subsystems;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// import edu.wpi.first.wpilibj.Solenoid;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.RobotMap;
// import frc.robot.helpers.Helper;
// import frc.robot.helpers.SubsystemManagerChild;


// public class PowerPack extends SubsystemManagerChild {
//   private WPI_TalonSRX primary, secondary, tertiary, quaternary;
//   private Solenoid switcher;
//   private final boolean ELEVATOR_MODE = true;

//   public PowerPack() {
//     primary = new WPI_TalonSRX(RobotMap.Powerpack.POWERPACK_PRIMARY);
//     secondary = new WPI_TalonSRX(RobotMap.Powerpack.POWERPACK_SECONDARY);
//     tertiary = new WPI_TalonSRX(RobotMap.Powerpack.POWERPACK_TERTIARY);
//     quaternary = new WPI_TalonSRX(RobotMap.Powerpack.POWERPACK_QUATERNARY);

//     secondary.follow(primary);
//     tertiary.follow(primary);
//     quaternary.follow(primary);

//     switcher = new Solenoid(RobotMap.Powerpack.POWERPACK_SWITCHER);
//   }

//   /**
//      * sets motor's speed
//      * @param speed percentage power from -1 to 1
//      */
//     public void setSpeed(double speed) {
//     primary.set(Helper.boundValue(speed));
//   }

//   /**
//    * turn off motor
//    */
//   public void stop() {
//     primary.stopMotor();
//   }

//   /**
//    * 
//    * @return speed motor is set to
//    */
//   public double getSpeed() {
//     return primary.get();
//   }

//   /**
//    * 
//    * @return position of motor according to encoder
//    */
//   public double getEncPosition() {
//     return primary.getSelectedSensorPosition();
//   }

//   /** 
//    * 
//    * @return state of forward limit switch
//    */
//   public boolean getForwardLimitSwitch() {
//     return true; //TODO fix this
//   }

//   /** 
//    * 
//    * @return state of reverse limit switch
//    */
//   public boolean getReverseLimitSwtich() {
//     return true; //TODO fix this
//   }

//   /**
//    * 
//    * @param mode mode to set powerpack to
//    */
//   public void setMode(boolean mode) {
//     switcher.set(mode);
//   }

//   /**
//    * switches powerpack to elevator mode
//    */
//   public void switchToElevator() {
//     setMode(ELEVATOR_MODE);
//   }

//   /**
//    * switches powerpack to climber mode
//    */
//   public void switchToClimber() {
//     setMode(!ELEVATOR_MODE);
//   }

//   /**
//    * 
//    * @return mode powerpack is set to
//    */
//   public boolean getMode() {
//     return switcher.get();
//   }

//   /**
//    * 
//    * @return true if powerpack is on elevator mode
//    */
//   public boolean isElevatorMode() {
//     return getMode() == ELEVATOR_MODE;
//   }

//   /**
//    * 
//    * @return true if powerpack is on climber mode
//    */
//   public boolean isClimberMode() {
//     return !isElevatorMode();
//   }


//   @Override
//   public void updateSD() {
//     SmartDashboard.putBoolean("Elevator Mode", isElevatorMode());
//     SmartDashboard.putNumber("Powerpack Speed", getSpeed());
//     SmartDashboard.putNumber("Powerpack Position", getEncPosition());
//   }
  
// }
