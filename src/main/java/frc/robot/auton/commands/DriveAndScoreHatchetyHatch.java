package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.hatchclaw.Release;
import frc.robot.climberelevator.powerpack.SmartSetElevatorPosition;
import frc.robot.drivetrain.commands.RunProfile;
import frc.robot.sensors.camera.FollowVision;

import java.util.ArrayList;

public class DriveAndScoreHatchetyHatch extends CommandGroup{
    
    public DriveAndScoreHatchetyHatch(ArrayList<String> fileName, ArrayList<Boolean> backwarsList, ArrayList<SmartSetElevatorPosition.Height> height, ArrayList<Boolean> grabHatch){
        for(int i = 0; i < fileName.size(); i++){
            addSequential(new RunProfile(fileName.get(i), backwarsList.get(i)));
            addSequential(new SmartSetElevatorPosition(height.get(i)));
            addSequential(new FollowVision(), 2000);
            if(!grabHatch.get(i)){
                addSequential(new Release());
            }else{
                addSequential(new Grab());
            }
        }
    }
}
