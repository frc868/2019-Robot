package frc.robot.sensors.subsystems.gyro;

import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.helpers.SubsystemManagerChild;

public class Gyroscope extends SubsystemManagerChild {
	private BNO055 gyro;
	private GyroBase gyroX;
	public static final boolean DEBUG = false;
	
	public Gyroscope() {
		gyro = BNO055.getInstance(I2C.Port.kOnboard);
		gyroX = gyro.createGyroX();
		gyroX.reset();
		
		// TODO: livewindow stuff
	}
	
	/**
	 * Gets current rotation of the robot.
	 * @return rotation of the robot (degrees)
	 */
	public double getRotation() {
		return gyroX.getAngle();
	}
	
	public void reset() {
		gyroX.reset();
	}
	
	public void initSD() {
		if (DEBUG) {
			SmartDashboard.putData(this);
			
		}
	}
	
	/**
	 * Updates the information on the SmartDashboard.
	 */
	@Override
	public void updateSD() {
		SmartDashboard.putNumber("Rotation", getRotation());
		if (DEBUG) {
//			gyro.updateDashboard(10);
		}
	}
}
