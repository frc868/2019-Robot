package frc.robot.helpers.oi;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.commands.StopCommand;

public class ButtonGroup extends Button {
    Button a, b;

    public ButtonGroup(Button a, Button b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean get() {
        return a.get() && b.get();
    }

    public void pressToStartReleaseToStop(Command command) {
        pressToStartReleaseToStop(command, new StopCommand(command));
    }

    public void pressToStartReleaseToStop(Command startCommand, Command stopCommand) {
        whenPressed(startCommand);
        whenReleased(stopCommand);
    }
}
