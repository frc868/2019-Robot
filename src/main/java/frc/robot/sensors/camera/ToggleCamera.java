package frc.robot.sensors.camera;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleCamera extends Command {
    public ToggleCamera() {
        // requires(Robot.camera);
    }

    @Override
    protected void initialize() {
        // if (Robot.camera.isVisionMode()) {
        //     Robot.camera.switchToCamera();
        // } else {
        //     Robot.camera.switchToVision();
        // }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}