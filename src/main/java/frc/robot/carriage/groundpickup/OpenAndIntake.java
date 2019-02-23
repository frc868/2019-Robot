package frc.robot.carriage.groundpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenAndIntake extends CommandGroup {

    public OpenAndIntake () {
        addSequential(new SetHatchPickupWristPosition(SetHatchPickupWristPosition.INTAKE_HATCH));
        addSequential(new IntakeUntilHatchDetected());
    }
}