package frc.robot.helpers.commands;

import edu.wpi.first.wpilibj.command.Command;

public class StopCommand extends Command{
    
    private Command command;
    
    public StopCommand(Command command){
        this.command = command;
    }
    
    @Override
    protected void initialize(){
        command.cancel();
    }
    
    @Override
    protected boolean isFinished(){
        return true;
    }
}