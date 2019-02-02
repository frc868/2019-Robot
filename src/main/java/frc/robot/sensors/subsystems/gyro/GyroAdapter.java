package frc.robot.sensors.subsystems.gyro;
import edu.wpi.first.wpilibj.GyroBase;

/**
 * A helper class converting sensor's that provide gyro readings into standard
 * {@link GyroSubsystem} objects.
 */
public abstract class GyroAdapter extends GyroBase {
	// The value from the sensor when last reset/constructed
	private double zeroVal;
	// Whether or not the sensor readings wrap around
	private boolean trackTurns;
	// The minimum value reported by underlying (if wraps)
	private double minVal;
	// The maximum value the sensor will report (if wraps)
	private double maxVal;
	// The last value reported by the sensor (used to detect wrap around)
	private double lastVal;
	// The number of time the sensor has turned a full revolution
	private int turns;
	// Whether or not the rate value is provided
	private boolean hasRate;
	// Will be set to true if gyro is mounted such that the values it returns are inverted
	private boolean inverted;

	/**
	 * Construct a new instance for a sensor that does not wrap around.
	 * 
	 * @param initVal
	 *            Initial reading from sensor.
	 * @param hasRate
	 *            Whether the implementation will provide a {@link #getRate}
	 *            feature.
	 */
	GyroAdapter(double initVal, boolean hasRate) {
		this(initVal, 0, 360, hasRate);
		trackTurns = false;
	}

	/**
	 * Construct a new instance for a sensor that wraps.
	 * 
	 * @param initVal
	 *            Initial reading from the sensor.
	 * @param minVal
	 *            The minimum value the sensor can report.
	 * @param maxVal
	 *            The maximum value the sensor can report.
	 * @param hasRate
	 *            Whether the implementation will provide a {@link #getRate}
	 *            feature.
	 */
	GyroAdapter(double initVal, double minVal, double maxVal, boolean hasRate) {
		this.zeroVal = initVal;
		this.lastVal = initVal;
		this.minVal = minVal;
		this.maxVal = maxVal;
		this.hasRate = hasRate;
		turns = 0;
		trackTurns = true;
	}

	/**
	 * Creates a fake Gyro object that always reports 0 degrees and 0 degrees/second.
	 * 
	 * @return A Gyro object that is stuck at zero (when you need a non-null gyro).
	 */
	public static GyroBase createFakeGyro() {
		return new GyroAdapter(0, true) {
			
			@Override
			protected double getSensorValue() {
				return 0;
			}
			
			@Override
			public double getRate() {
				return 0.0;
			}
		};
	}

	/**
	 * Indicates whether the gyro was mounted upside down and the values returned by getAngle() are negated.
	 * 
	 * @return true if gyro is mounted upside down.
	 */
	public boolean isInverted() {
		return inverted;
	}

	/**
	 * Set whether the gyro was mounted upside down or not.
	 * 
	 * @param inverted Pass true if gyro is mounted upside down.
	 */
	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	/**
	 * Get the current position (in degrees) reported by the sensor.
	 * 
	 * <p>
	 * When you extend this class, you must implement this method and return the
	 * current position from the sensor in degrees. The value returned should be
	 * within the min/max range you specified in the constructor (if
	 * applicable).
	 * </p>
	 * 
	 * @return The current reading from the sensor in degrees.
	 */
	protected abstract double getSensorValue();

	/**
	 * Calibration requests are ignored by default.
	 * 
	 * <p>
	 * Our philosophy is that any calibration of the sensor should be done in
	 * the sensors implementation at time of construction (at the start of the
	 * match). It is best to avoid calibration during a match - especially in
	 * the case where you might have mutliple Gyro objects using the same
	 * underlying sensor).
	 * </p>
	 */
	@Override
	public final void calibrate() {
	}

	/**
	 * Reset (zero) out this gyro's current angle to zero (only affects this
	 * gyro instance).
	 */
	@Override
	public final void reset() {
		turns = 0;
		lastVal = zeroVal = getSensorValue();
	}

	/**
	 * Get the current angle of rotation from the gyro along the axis (in
	 * degrees).
	 * 
	 * @return Degrees rotated about axis in the range of (-INF, +INF).
	 */
	@Override
	public final double getAngle() {
		double curVal = getSensorValue();
		double angle = curVal - zeroVal;

		if (trackTurns) {
			double change = curVal - lastVal;
			double span = maxVal - minVal;
			double halfSpan = span / 2;

			if (change > halfSpan) {
				turns--;
			} else if (change < -halfSpan) {
				turns++;
			}

			angle += turns * span;
		}
		lastVal = curVal;

		return (inverted ? -angle : angle);
	}

	/**
	 * Indicates whether or not the gyro implementation provides the
	 * {@link #getRate} function.
	 * 
	 * @return false unless the sensor provides the {@link #getRate} function.
	 */
	public final boolean hasRate() {
		return hasRate;
	}

	/**
	 * By default the {@link #getRate} method is not supported and throws an
	 * exception.
	 * 
	 * <p>
	 * This method may be implemented by some sensors (such as the
	 * {@link ITG3200}) - check with the sensor's method used to construct a
	 * Gyro instance to see whether it was implemented prior to using.
	 * </p>
	 * 
	 * @return Rate of rotation in degrees/second, OR throws an unsupported
	 *         exception if you try to perform this operation on a sensor that
	 *         does not support it.
	 */
	@Override
	public double getRate() {
		throw new UnsupportedOperationException(
				"This Gryo implementation does not provide a rate measurement");
	}

}