package frc.robot.helpers.oi;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.commands.EmptyCommand;
import frc.robot.helpers.commands.StopCommand;
import frc.robot.helpers.oi.ButtonGroup;

public class POVButtonPlus extends POVButton {

    public POVButtonPlus(XboxControllerPlus controller, int angle) {
        super(controller, angle);
    }

    public void resetMappings() {
        whenActive(new EmptyCommand());
        whenInactive(new EmptyCommand());
        whenPressed(new EmptyCommand());
        whenReleased(new EmptyCommand());
        whileHeld(new EmptyCommand());
        cancelWhenActive(new EmptyCommand());
        cancelWhenPressed(new EmptyCommand());
        toggleWhenActive(new EmptyCommand());
        toggleWhenPressed(new EmptyCommand());
    }

    public void pressToStartReleaseToStop(Command command) {
        pressToStartReleaseToStop(command, new StopCommand(command));
    }

    public void pressToStartReleaseToStop(Command startCommand, Command stopCommand) {
        whenPressed(startCommand);
        whenReleased(stopCommand);
    }

    public ButtonGroup and(Button otherButton) {
        return new ButtonGroup(this, otherButton);
    }

}