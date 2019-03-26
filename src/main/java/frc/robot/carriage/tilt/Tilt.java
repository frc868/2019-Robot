package frc.robot.carriage.tilt;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.commands.StopMotor;
import frc.robot.helpers.sensors.PotentiometerLimit;
import frc.robot.helpers.subsystems.SubsystemManagerChild;


public class Tilt extends SubsystemManagerChild {
    private WPI_TalonSRX motor;
    private AnalogPotentiometer potentiometer;
    private Solenoid brake;
    public static final double LOWER = 0.85, MIDDLE = 0.838, UPPER = .708;
    private final boolean BRAKE_MODE = false;
    public boolean limitPower = false;

    public Tilt() {
        super("Tilt");
        motor = new WPI_TalonSRX(RobotMap.Carriage.Tilt.MOTOR);
        brake = new Solenoid(RobotMap.PCM, RobotMap.Carriage.Tilt.BRAKE);
        potentiometer = new AnalogPotentiometer(RobotMap.Carriage.Tilt.POTENTIOMETER);
        motor.setInverted(true);
    }

    /**
     * sets motor's speed
     * @param speed percentage power from -1 to 1, will not work if limits are tripped
     */
    public void setSpeed(double speed) {
        if(limitPower){
            speed = Helper.boundValue(speed, -0.25, 0.25);
        }

        motor.set(Helper.boundValue(speed));
        SmartDashboard.putNumber("Tilt Current", motor.getOutputCurrent());
    }

    /**
     * turn off motor
     */
    public void stop() {
        motor.stopMotor();
    }

    /**
     *
     * @return speed motor is set to
     */
    public double getSpeed() {
        return motor.get();
    }

    /**
     *
     * @return position of motor according to encoder
     */
    public double getPotPosition() {
        return potentiometer.get();
    }

    /**
     * returns brake solenoid state
     */
    public boolean getBrake() {
        return brake.get();
    }

    /**
     * @return true if elevator is braking
     */
    public boolean isBraked() {
        return getBrake() == BRAKE_MODE;
    }

     /**
     * turns brake on
     */
    public void brakeOn() {
        brake.set(BRAKE_MODE);
    }

    /**
     * turns brake off
     */
    public void brakeOff() {
        brake.set(!BRAKE_MODE);
    }

    @Override
    public void updateSD() {
        SmartDashboard.putNumber("Tilt Speed", getSpeed());
        SmartDashboard.putNumber("Tilt Position", getPotPosition());
        SmartDashboard.putBoolean("Tilt Braked?", isBraked());
    }
}