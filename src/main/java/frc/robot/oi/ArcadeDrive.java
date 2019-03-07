package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import frc.robot.helpers.Helper;
import frc.robot.helpers.oi.XboxControllerPlus;

public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        double y = Math.pow(Helper.deadzone(-OI.driver.getLY(),0.1),3);
        double x = Math.pow(Helper.deadzone(0.75*(-OI.driver.getRX()),0.1),3);
        Robot.drivetrain.setSpeed(y - x, y + x);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}