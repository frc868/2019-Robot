package frc.robot.climberelevator.powerpack;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.commands.ResetEncoder;
import frc.robot.helpers.commands.StopMotor;
import frc.robot.helpers.sensors.IRLimit;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class PowerPack extends SubsystemManagerChild{
    
    public static final double INTAKE_BALL = 2.54,
            LOWER_BALL = 5.02, LOWER_HATCH = 1.85,
            MIDDLE_BALL = 22.023, MIDDLE_HATCH = 19.66,
            UPPER_BALL = 39, UPPER_HATCH = 36.38;
    private final boolean ELEVATOR_MODE = false, BRAKE_MODE = false;
    private CANSparkMax primary, secondary;
    private Solenoid switcher, elevator_brake, climber_brake;
    private IRLimit elevator_top_limit, elevator_bottom_limit;
    
    public PowerPack(){
        super("PowerPack");
        primary = new CANSparkMax(RobotMap.ClimberElevator.Powerpack.PRIMARY, MotorType.kBrushless);
        secondary = new CANSparkMax(RobotMap.ClimberElevator.Powerpack.SECONDARY, MotorType.kBrushless);
        
        primary.setIdleMode(IdleMode.kBrake);
        secondary.setIdleMode(IdleMode.kBrake);
        
        secondary.follow(primary);
        
        // primary.setInverted(true);
        secondary.setInverted(true);
        
        switcher = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.SWITCHER);
        elevator_brake = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.ELEVATOR_BRAKE);
        climber_brake = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Powerpack.CLIMBER_BRAKE);
        
        elevator_top_limit = new IRLimit(RobotMap.ClimberElevator.Powerpack.ELEVATOR_TOP_LIMIT);
        elevator_bottom_limit = new IRLimit(RobotMap.ClimberElevator.Powerpack.ELEVATOR_BOTTOM_LIMIT);
    }
    
    /**
     * turn off motor
     */
    public void stop(){
        primary.stopMotor();
    }
    
    /**
     *
     */
    public void resetEncPosition(){
        primary.getEncoder().setPosition(0);
    }
    
    /**
     * switches powerpack to climber mode
     */
    public void switchToClimber(){
        switcher.set(!ELEVATOR_MODE);
    }
    
    /**
     * turns brake off
     */
    public void elevatorBrakeOff(){
        elevator_brake.set(!BRAKE_MODE);
    }
    
    /**
     * turns brake off
     */
    public void climberBrakeOff(){
        climber_brake.set(!BRAKE_MODE);
    }
    
    @Override
    public void init(){
        elevator_bottom_limit.getTrigger().whileActive(new ResetEncoder(primary));
        elevator_bottom_limit.getTrigger().whenActive(new StopMotor(primary, elevator_brake, BRAKE_MODE));
        elevator_top_limit.getTrigger().whenActive(new StopMotor(primary, elevator_brake, BRAKE_MODE));
    }
    
    @Override
    public void initDisabled(){
        climberBrakeOn();
        elevatorBrakeOn();
    }
    
    @Override
    public void initEnabled(){
        switchToElevator();
        climberBrakeOn();
        elevatorBrakeOn();
    }
    
    /**
     * switches powerpack to elevator mode
     */
    public void switchToElevator(){
        switcher.set(ELEVATOR_MODE);
    }
    
    @Override
    public void updateSD(){
        SmartDashboard.putNumber("PowerPack Speed", getSpeed());
        SmartDashboard.putNumber("PowerPack Position", getEncPosition());
        SmartDashboard.putBoolean("Elevator Mode?", isElevatorMode());
        SmartDashboard.putBoolean("Elevator Brake?", isElevatorBraked());
        SmartDashboard.putBoolean("Climber Brake?", isClimberBraked());
        SmartDashboard.putBoolean("Elevator Top Limit?", getElevatorTopLimitSwitch());
        SmartDashboard.putBoolean("Elevator Bottom Limit?", getElevatorBottomLimitSwitch());
    }
    
    /**
     * @return speed motor is set to
     */
    public double getSpeed(){
        return primary.get();
    }
    
    /**
     * sets motor's speed
     *
     * @param speed percentage power from -1 to 1, will not work if limits are tripped
     */
    public void setSpeed(double speed){
        speed = Helper.boundValue(speed);
        
        if(isElevatorMode()){
            if(getElevatorBottomLimitSwitch()){
                speed = Helper.boundValue(speed, 0, 1);
            }else if(getElevatorTopLimitSwitch()){
                speed = Helper.boundValue(speed, -1, 0);
            }
        }
        
        primary.set(speed);
    }
    
    /**
     * @return position of motor according to encoder
     */
    public double getEncPosition(){
        return primary.getEncoder().getPosition();
    }
    
    /**
     * @return true if powerpack is on elevator mode
     */
    public boolean isElevatorMode(){
        return getSwitcher() == ELEVATOR_MODE;
    }
    
    /**
     * @return true if elevator is braking
     */
    public boolean isElevatorBraked(){
        return getElevatorBrake() == BRAKE_MODE;
    }
    
    /**
     * @return true if climber is braking
     */
    public boolean isClimberBraked(){
        return getClimberBrake() == BRAKE_MODE;
    }
    
    /**
     * @return state of forward limit switch
     */
    public boolean getElevatorTopLimitSwitch(){
        return elevator_top_limit.get();
    }
    
    /**
     * @return state of reverse limit switch
     */
    public boolean getElevatorBottomLimitSwitch(){
        return elevator_bottom_limit.get();
    }
    
    /**
     *
     */
    public boolean getSwitcher(){
        return switcher.get();
    }
    
    /**
     * returns brake solenoid state
     */
    public boolean getElevatorBrake(){
        return elevator_brake.get();
    }
    
    /**
     * returns brake solenoid state
     */
    public boolean getClimberBrake(){
        return climber_brake.get();
    }
    
    /**
     * turns brake on
     */
    public void climberBrakeOn(){
        climber_brake.set(BRAKE_MODE);
    }
    
    /**
     * turns brake on
     */
    public void elevatorBrakeOn(){
        elevator_brake.set(BRAKE_MODE);
    }
    
    @Override
    public void periodic(){
        super.periodic();
        if(primary.get() == 0){
            elevatorBrakeOn();
        }
    }
}