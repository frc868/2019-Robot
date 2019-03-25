package frc.robot.helpers.oi;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.commands.StopCommand;

public class POVButtonPlus extends POVButton {

    public POVButtonPlus(XboxControllerPlus controller, int angle) {
        super(controller, angle);
    }

    /**
     * run a command while button is held, stop once released
     * @param command the command to run while held
     */
    public void pressToStartReleaseToStop(Command command) {
        pressToStartReleaseToStop(command, new StopCommand(command));
    }
    
    /**
     * like it sounds, it starts something when the trigger is activited and runs it's respective end command
     * @param startCommand the command to start
     * @param stopCommand the command to run when it is released
     */
    public void pressToStartReleaseToStop(Command startCommand, Command stopCommand) {
        whenPressed(startCommand);
        whenReleased(stopCommand);
    }

}