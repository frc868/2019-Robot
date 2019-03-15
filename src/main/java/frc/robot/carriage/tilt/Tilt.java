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
    public static final double LOWER = .237, MIDDLE = 0.165, UPPER = .104;

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
        if (getTopLimit()) {
            speed = Helper.boundValue(speed, -1, 0);
        } else if (getBottomLimit()) {
            speed = Helper.boundValue(speed, 0, 1);
        }

        motor.set(Helper.boundValue(speed));
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
    public void initEnabled() {
        // SmartDashboard.putData("Tilt Up", new SetTiltPosition(Tilt.MIDDLE));
    }

    @Override
    public void init() {
        addTab("Motor", motor);
        addTab("Potentiometer", potentiometer);
        limit.getTrigger().whenActive(new StopMotor(motor));
    }

    @Override
    public void updateSD() {
        SmartDashboard.putNumber("Tilt Speed", getSpeed());
        SmartDashboard.putNumber("Tilt Position", getPotPosition());
    }
}