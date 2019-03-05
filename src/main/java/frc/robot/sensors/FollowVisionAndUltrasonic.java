package frc.robot.sensors;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.sensors.camera.FollowVision;
import frc.robot.sensors.ultrasonic.FollowUltrasonic;

public class FollowVisionAndUltrasonic extends CommandGroup {

    public FollowVisionAndUltrasonic() {
        addSequential(new FollowVision());
        addSequential(new FollowUltrasonic());
    }
}