package frc.robot.oi;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class Rumble extends TimedCommand {
    protected static final double DEFAULT_TIME = 0.5;

    public Rumble() {
        this(DEFAULT_TIME);
    }

    public Rumble(double time) {
        super(time);
    }

    @Override
    protected void initialize() {
        OI.driver.setRumble(true);
        OI.operator.setRumble(true);
    }

    @Override
    protected void end() {
        OI.driver.setRumble(false);
        OI.operator.setRumble(false);
    }
}