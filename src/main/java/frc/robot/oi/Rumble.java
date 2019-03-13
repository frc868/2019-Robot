package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;

public class Rumble extends Command {
    private boolean state;

    public Rumble(boolean state) {
        this.state = state;
    }

    @Override
    protected void initialize() {
        OI.driver.setRumble(state);
        OI.operator.setRumble(state);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}