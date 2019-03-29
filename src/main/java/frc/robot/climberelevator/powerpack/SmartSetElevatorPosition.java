package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class SmartSetElevatorPosition extends CommandGroup{
    
    private Height height;
    
    public SmartSetElevatorPosition(Height height){
        this.height = height;
    }
    
    @Override
    protected void initialize(){
        CommandGroup command = new CommandGroup();
        if(height == Height.ballIntake){
            command.addSequential(new SetElevatorPosition(PowerPack.INTAKE_BALL));
        }else if(height == Height.lower){
            if(Robot.ballIntake.isBallDetected()){
                command.addSequential(new SetElevatorPosition(PowerPack.LOWER_BALL));
            }else{
                command.addSequential(new SetElevatorPosition(PowerPack.LOWER_HATCH));
            }
        }else if(height == Height.middle){
            if(Robot.ballIntake.isBallDetected()){
                command.addSequential(new SetElevatorPosition(PowerPack.MIDDLE_BALL));
            }else{
                command.addSequential(new SetElevatorPosition(PowerPack.MIDDLE_HATCH));
            }
        }else if(height == Height.upper){
            if(Robot.ballIntake.isBallDetected()){
                command.addSequential(new SetElevatorPosition(PowerPack.UPPER_BALL));
            }else{
                command.addSequential(new SetElevatorPosition(PowerPack.UPPER_HATCH));
            }
        }
        
        command.start();
    }
    
    public enum Height{
        ballIntake, lower, middle, upper
    }
}