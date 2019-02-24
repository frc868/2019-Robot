package frc.robot.carriage.ballintake;

import frc.robot.Robot;

public class IntakeUntilBallDetected extends SetBallIntakeSpeed {
    private static final double DEFAULT_POWER = -1.0;
    
    private int countsDetected = 0;
    private final int COUNTS_NEEDED = 5; // the counts needed to determine that the ball is indeed detected

    public IntakeUntilBallDetected(double power) {
        super(power);
    }

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