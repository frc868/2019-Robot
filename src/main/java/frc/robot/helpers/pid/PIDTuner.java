package frc.robot.helpers.pid;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDTuner extends Command {
    public PIDCommandPlus command;
    public double P = 0, I = 0, D = 0;

    public PIDTuner(PIDCommandPlus command) {
        SmartDashboard.putNumber("P", P);
        SmartDashboard.putNumber("I", I);
        SmartDashboard.putNumber("D", D);
        this.command = command;
    }

    @Override
    protected void initialize() {
        P = SmartDashboard.getNumber("P", P);
        I = SmartDashboard.getNumber("I", I);
        D = SmartDashboard.getNumber("D", D);

        command.setP(P);
        command.setI(I);
        command.setD(D);

        command.start();
    }

    @Override
    protected boolean isFinished() {
        return command.isCompleted();
    }
}