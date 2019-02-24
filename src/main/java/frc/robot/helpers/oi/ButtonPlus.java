package frc.robot.helpers.oi;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.commands.EmptyCommand;
import frc.robot.helpers.commands.StopCommand;

public class ButtonPlus extends JoystickButton {

    public ButtonPlus(XboxControllerPlus controller, int id) {
        super(controller, id);
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

}