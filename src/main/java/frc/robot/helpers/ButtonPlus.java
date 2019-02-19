package frc.robot.helpers;

import edu.wpi.first.wpilibj.buttons.JoystickButton;;

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



}