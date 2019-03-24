package frc.robot.helpers.oi;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.commands.EmptyCommand;
import frc.robot.helpers.commands.StopCommand;
import frc.robot.helpers.triggers.TriggerPlus;

public class ButtonPlus extends TriggerPlus {
    private final GenericHID controller;
    private final int id;

    public ButtonPlus(XboxControllerPlus controller, int id) {
        this.controller = controller;
        this.id = id;
    }

    public void resetMappings() {
        whenPressed(new EmptyCommand());
        whileHeld(new EmptyCommand());
        whenReleased(new EmptyCommand());
        toggleWhenPressed(new EmptyCommand());
        cancelWhenPressed(new EmptyCommand());

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

    public void whenPressed(final Command command) {
        whenActive(command);
    }

    public void whileHeld(final Command command) {
        whileActive(command);
    }
    

    public void whenReleased(final Command command) {
        whenInactive(command);
    }

    public void toggleWhenPressed(final Command command) {
        toggleWhenActive(command);
    }

    public void cancelWhenPressed(final Command command) {
        cancelWhenActive(command);
    }
   
    @Override
    public boolean get() {
        return controller.getRawButton(id);
    }

}