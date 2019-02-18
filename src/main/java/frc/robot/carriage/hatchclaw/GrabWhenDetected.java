/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GrabWhenDetected extends Command {
    private final int COUNTS_NEEDED = 5;
    private int counts = 0;

    public GrabWhenDetected() {
        requires(Robot.hatchClaw);
    }

    @Override
    protected void initialize() {
        Robot.hatchClaw.release();
    }

    @Override
    protected void execute() {
        if (Robot.hatchClaw.isHatchDetected()) {
            counts++;
        } else {
            counts = 0;
        }
    }

    @Override
    protected boolean isFinished() {
        return counts >= COUNTS_NEEDED; 
    }

    @Override
    protected void end() {
        Robot.hatchClaw.grab();
    }

    @Override
    protected void interrupted() {
        // don't do anything (if we don't overide it will end() when interupted)
    }
}
