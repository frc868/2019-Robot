package frc.robot.helpers.oi;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.commands.StopCommand;
import frc.robot.helpers.triggers.TriggerPlus;

public class ButtonPlus extends TriggerPlus{
    
    private final GenericHID controller;
    private final int id;
    
    /**
     * allows controller mapping to multiple buttons, and uses an actually good whilepressed
     *
     * @param controller the xbox controller we're getting the button from
     * @param id         the button number used
     */
    public ButtonPlus(XboxControllerPlus controller, int id){
        this.controller = controller;
        this.id = id;
    }
    
    /**
     * runs command while a button is held, stops same command after
     *
     * @param command the command to be run
     */
    public void pressToStartReleaseToStop(Command command){
        pressToStartReleaseToStop(command, new StopCommand(command));
    }
    
    /**
     * runs 1st command while button is held, runs 2nd after released
     *
     * @param startCommand the command to run while held
     * @param stopCommand  the command to run once released
     */
    public void pressToStartReleaseToStop(Command startCommand, Command stopCommand){
        whenPressed(startCommand);
        whenReleased(stopCommand);
    }
    
    /**
     * runs command once button is pressed
     *
     * @param command the command to run once pressed
     */
    public void whenPressed(final Command command){
        whenActive(command);
    }
    
    /**
     * Initializes command when the button is released
     *
     * @param command command that is being called
     */
    public void whenReleased(final Command command){
        whenInactive(command);
    }
    
    /**
     * initializes command repeatedly while the button is held down
     * note: probably not going to be used much
     *
     * @param command the command to call repeatedly
     */
    public void whileHeld(final Command command){
        whileActive(command);
    }
    
    /**
     * toggles a command whne it is pressed
     *
     * @param command the command you want to toggle
     */
    public void toggleWhenPressed(final Command command){
        toggleWhenActive(command);
    }
    
    /**
     * cancel a running command when a button is pressed
     *
     * @param command the command to cancel
     */
    public void cancelWhenPressed(final Command command){
        cancelWhenActive(command);
    }
    
    /**
     * gets the state of the button
     */
    @Override
    public boolean get(){
        return controller.getRawButton(id);
    }
    
}