package frc.robot.carriage.tilt;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.commands.StopMotor;
import frc.robot.helpers.sensors.PotentiometerLimit;
import frc.robot.helpers.subsystems.SubsystemManagerChild;


public class Tilt extends SubsystemManagerChild {
    private WPI_TalonSRX motor;
    private AnalogPotentiometer potentiometer;
    private PotentiometerLimit limit;
    public static final double LOWER = .265, MIDDLE = 0.248, UPPER = .134;
    public static final double TILT_LOWER_SPEED = -0.1;
    public boolean limitPower = false;

    public Tilt() {
        super("Tilt");
        motor = new WPI_TalonSRX(RobotMap.Carriage.Tilt.MOTOR);
        potentiometer = new AnalogPotentiometer(RobotMap.Carriage.Tilt.POTENTIOMETER);
        limit = new PotentiometerLimit(potentiometer, LOWER, UPPER);
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
        if (getTopLimit()) {
            speed = Helper.boundValue(speed, -1, 0);
        } else if (getBottomLimit()) {
            speed = Helper.boundValue(speed, 0, 1);
        }

        motor.set(Helper.boundValue(speed, TILT_LOWER_SPEED, 1));
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
     *
     * @return if forward limit is tripped
     */
    public boolean getTopLimit() {
        return limit.getReverseLimit();
    }

    /**
     * @return if reverse limit is tripped
     */
    public boolean getBottomLimit() {
        return limit.getForwardLimit();
    }

    @Override
    public void init() {
        limit.getTrigger().whenActive(new StopMotor(motor));
    }

    @Override
    public void initEnabled() {
        (new BrakeTilt()).start(); // brake the motor when we enable
    }

    @Override
    public void initSD() {
        addTab("Motor", motor);
        addTab("Potentiometer", potentiometer);
    }

    @Override
    public void updateSD() {
        SmartDashboard.putNumber("Tilt Speed", getSpeed());
        SmartDashboard.putNumber("Tilt Position", getPotPosition());
    }
}