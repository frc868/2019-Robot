package frc.robot.helpers.oi;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.commands.EmptyCommand;
import frc.robot.helpers.commands.StopCommand;
import frc.robot.helpers.oi.ButtonGroup;

public class ButtonPlus extends JoystickButton {

    public ButtonPlus(XboxControllerPlus controller, int id) {
        super(controller, id);
    }

    public void resetMappings() {
        whenPressed(new EmptyCommand());
        whileHeld(new EmptyCommand());
        whenReleased(new EmptyCommand());
        toggleWhenPressed(new EmptyCommand());
        cancelWhenPressed(new EmptyCommand());

        whenActive(new EmptyCommand());
        whileActive(new EmptyCommand());
        whenInactive(new EmptyCommand());
        toggleWhenActive(new EmptyCommand());
        cancelWhenActive(new EmptyCommand());

        pressToStartReleaseToStop(new EmptyCommand());
        pressToStartReleaseToStop(new EmptyCommand(), new EmptyCommand());
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