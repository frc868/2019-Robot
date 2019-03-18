package frc.robot.carriage.ballintake;

import frc.robot.Robot;

public class IntakeUntilBallDetected extends SetBallIntakeSpeed {
    private static final double DEFAULT_POWER = -1.0;
    private int countsDetected = 0; // the ammount of counts that the ball is currently detected for 
    private final int COUNTS_NEEDED = 5; // the counts needed to determine that the ball is indeed detected

    /**
     * intakes the ball until the limit detects the ball for a set ammount of time
     * @param power power to set the intake to
     */
    public IntakeUntilBallDetected(double power) {
        super(power);
    }

    /**
     * intakes the ball until the limit detects the ball for a set ammount of time
     * power will be set to the default power
     */
    public IntakeUntilBallDetected() {
        super(DEFAULT_POWER);
    }

    @Override
    protected void execute() {
        if (Robot.ballIntake.isBallDetected()) { // if the ball is detected, add to counts detected
            countsDetected++;
        } else { // else reset counts detected
            countsDetected = 0;
        }
    }

    @Override
    protected boolean isFinished() {
        // return true if the ball has been detected for the counts needed to determine the intake has the ball
        return countsDetected >= COUNTS_NEEDED;  
    }
}