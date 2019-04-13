package frc.robot.climberelevator.powerpack;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.commands.ResetEncoder;
import frc.robot.helpers.commands.StopMotor;
import frc.robot.helpers.motorcontrollers.CANSparkMaxPlus;
import frc.robot.helpers.sensors.IRLimit;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class PowerPack extends SubsystemManagerChild {
    private CANSparkMaxPlus primary, secondary;
    private Solenoid switcher, elevator_brake, climber_brake;
    private IRLimit elevator_top_limit, elevator_bottom_limit, climber_top_limit, climber_bottom_limit;
    private DigitalInput climber_limit_switch;
    private final boolean ELEVATOR_MODE = false, BRAKE_MODE = false;

    public static final double INTAKE_BALL = 2.54, 
        LOWER_BALL = 5.02, LOWER_HATCH = 0.5,//LOWER_HATCH = 0, 
        MIDDLE_BALL = 22.023, MIDDLE_HATCH = 19.66,//MIDDLE_HATCH = 19.66, 
        UPPER_BALL = 39, UPPER_HATCH = 35.85;

    public PowerPack() {
        super("PowerPack");
        primary = new CANSparkMaxPlus(RobotMap.ClimberElevator.Powerpack.PRIMARY);
        secondary = new CANSparkMaxPlus(RobotMap.ClimberElevator.Powerpack.SECONDARY);

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
        climber_top_limit = new IRLimit(RobotMap.ClimberElevator.Powerpack.CLIMBER_TOP_LIMIT);
        climber_bottom_limit = new IRLimit(RobotMap.ClimberElevator.Powerpack.CLIMBER_BOTTOM_LIMIT);

        climber_limit_switch = new DigitalInput(RobotMap.ClimberElevator.Powerpack.CLIMBER_LIMIT_SWITCH);
    }

    /**
     * sets motor's speed
     * @param speed percentage power from -1 to 1, will not work if limits are tripped
     */
    public void setSpeed(double speed) {
        speed = Helper.boundValue(speed);

        if (isElevatorMode()) {
            if (getElevatorBottomLimitSwitch()) {
                speed = Helper.boundValue(speed, 0, 1);
            } 
            else if (getElevatorTopLimitSwitch()) {
                speed = Helper.boundValue(speed, -1, 0);
            }
        } 
        else{
            if(getClimberLimitSwitch()){
                speed = Helper.boundValue(speed, 0, 1);
            }
        }
        // else {
        //     if (getClimberBottomLimitSwitch()) {
        //         speed = Helper.boundValue(speed, 0, 1);
        //     } 
        //     // else if (getClimberTopLimitSwitch()) {
        //     //     speed = Helper.boundValue(speed, -1, 0);
        //     // }
        // }

        primary.set(speed);
    }

    /**
     * turn off motor
     */
    public void stop() {
        primary.stopMotor();
    }

    /**
     *
     * @return speed motor is set to
     */
    public double getSpeed() {
        return primary.get();
    }

    /**
     *
     * @return position of motor according to encoder
     */
    public double getEncPosition() {
        return primary.getEncoder().getPosition();
    }

    /**
     *
     */
    public void resetEncPosition() {
        primary.getEncoder().setPosition(0);
    }

    /**
     *
     * @return state of forward limit switch
     */
    public boolean getElevatorTopLimitSwitch() {
        return elevator_top_limit.get();
    }

    /**
     *
     * @return state of reverse limit switch
     */
    public boolean getElevatorBottomLimitSwitch() {
        return elevator_bottom_limit.get();
    }

    /**
     *
     * @return state of forward limit switch
     */
    public boolean getClimberTopLimitSwitch() {
        return climber_top_limit.get();
    }

    /**
     *
     * @return state of reverse limit switch
     */
    public boolean getClimberBottomLimitSwitch() {
        return climber_bottom_limit.get();
    }

    public boolean getClimberLimitSwitch(){
        return !climber_limit_switch.get();
    }

    /**
     * switches powerpack to elevator mode
     */
    public void switchToElevator() {
        switcher.set(ELEVATOR_MODE);
    }

    /**
     * switches powerpack to climber mode
     */
    public void switchToClimber() {
        switcher.set(!ELEVATOR_MODE);
    }

    /**
     *
     */
    public boolean getSwitcher() {
        return switcher.get();
    }

    /**
     *
     * @return true if powerpack is on elevator mode
     */
    public boolean isElevatorMode() {
        return getSwitcher() == ELEVATOR_MODE;
    }

    /**
     * returns brake solenoid state
     */
    public boolean getElevatorBrake() {
        return elevator_brake.get();
    }

    /**
     * @return true if elevator is braking
     */
    public boolean isElevatorBraked() {
        return getElevatorBrake() == BRAKE_MODE;
    }

    /**
     * turns brake on
     */
    public void elevatorBrakeOn() {
        elevator_brake.set(BRAKE_MODE);
    }

    /**
     * turns brake off
     */
    public void elevatorBrakeOff() {
        elevator_brake.set(!BRAKE_MODE);
    }

    /**
     * returns brake solenoid state
     */
    public boolean getClimberBrake() {
        return climber_brake.get();
    }

    /**
     * @return true if climber is braking
     */
    public boolean isClimberBraked() {
        return getClimberBrake() == BRAKE_MODE;
    }

    /**
     * turns brake on
     */
    public void climberBrakeOn() {
        climber_brake.set(BRAKE_MODE);
    }

    /**
     * turns brake off
     */
    public void climberBrakeOff() {
        climber_brake.set(!BRAKE_MODE);
    }

    @Override
    public void init() {
        elevator_bottom_limit.getTrigger().whileActive(new ResetEncoder(primary));
        elevator_bottom_limit.getTrigger().whenActive(new StopMotor(primary, elevator_brake, BRAKE_MODE));
        elevator_top_limit.getTrigger().whenActive(new StopMotor(primary, elevator_brake, BRAKE_MODE));
        // climber_bottom_limit.getTrigger().whenActive(new StopMotor(primary));
        // climber_top_limit.getTrigger().whenActive(new StopMotor(primary));
    }

    @Override
    public void periodic() {
        super.periodic();
        if(primary.get() == 0){
            elevatorBrakeOn();
        }
    }

    @Override
    public void initEnabled() {
        switchToElevator();
        climberBrakeOn();
        elevatorBrakeOn();
    }

    @Override
    public void initDisabled() {
        climberBrakeOn();
        elevatorBrakeOn();
    }

    @Override
    public void initSD() {
        addTab("Switcher", switcher);
        addTab("Elevator Brake", elevator_brake);
        addTab("Climber Brake", climber_brake);

        addTab("Elevator Bottom Limit", elevator_bottom_limit);
        addTab("Elevator Top Limit", elevator_top_limit);

        addTab("Climber Bottom Limit", climber_bottom_limit);
        addTab("Climber Top Limit", climber_top_limit);

        // addTab("Motors", primary);
        // addTab("Encoder", primary.getEncoder());
    }

    @Override
    public void updateSD() {
        SmartDashboard.putNumber("PowerPack Speed", getSpeed());
        // SmartDashboard.putNumber("PowerPack Secondary Speed", secondary.get());
        SmartDashboard.putNumber("PowerPack Position", getEncPosition());
        SmartDashboard.putBoolean("Elevator Mode?", isElevatorMode());
        SmartDashboard.putBoolean("Elevator Brake?", isElevatorBraked());
        SmartDashboard.putBoolean("Climber Brake?", isClimberBraked());
        SmartDashboard.putBoolean("Elevator Top Limit?", getElevatorTopLimitSwitch());
        SmartDashboard.putBoolean("Elevator Bottom Limit?", getElevatorBottomLimitSwitch());
        SmartDashboard.putBoolean("Climber Limit?", getClimberLimitSwitch());
        // SmartDashboard.putBoolean("Climber Bottom Limit?", getClimberBottomLimitSwitch());

        SmartDashboard.putNumber("PowerPack Current", primary.getOutputCurrent());
        // SmartDashboard.putNumber("PowerPack Secondary Current", secondary.getOutputCurrent());
    }
}
