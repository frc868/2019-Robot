package frc.robot.carriage.commands;

import frc.robot.Robot;

public class IntakeUntilBallDetected extends SetBallIntakeSpeed {
    private int countsDetected = 0;
    private final int COUNTS_NEEDED = 5; // the counts needed to determine that the ball is indeed detected

    public IntakeUntilBallDetected() {
        super(1.0);
    }

    @Override
    protected void execute() {
        super.execute();

        if (Robot.ballIntake.isBallDetected()) { // if the ball is detected, add to counts detected
            countsDetected++;
        } else { // else reset counts detected
            countsDetected = 0;
        }
    }

    @Override
    protected boolean isFinished() {
        return countsDetected >= COUNTS_NEEDED; // return true if the ball has been detected for the counts needed to determine the intake has the ball 
    }
}