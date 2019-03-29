package frc.robot.climberelevator.footdrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

/**
 * subsystem for the bottom drive on foot
 */
public class FootDrive extends SubsystemManagerChild{
    
    private WPI_TalonSRX motor;
    
    public FootDrive(){
        super("FootDrive");
        motor = new WPI_TalonSRX(RobotMap.ClimberElevator.FootDrive.MOTOR);
    }
    
    /**
     * turn off motor
     */
    public void stop(){
        motor.stopMotor();
    }
    
    @Override
    public void updateSD(){
        SmartDashboard.putNumber("Foot Speed", getSpeed());
    }
    
    /**
     * @return speed motor is set to
     */
    public double getSpeed(){
        return motor.get();
    }
    
    /**
     * sets motor's speed
     *
     * @param speed percentage power from 0 to 1 (Can't drive backwards)
     */
    public void setSpeed(double speed){
        motor.set(Helper.boundValue(speed, -1, 0));
    }
}
