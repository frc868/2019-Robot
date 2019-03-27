package frc.robot.helpers.pid;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDTuner extends Command {
    private PIDCommandPlus command;
    private double P, I, D;
    private String name, PKey = "P", IKey = "I", DKey = "D";

    public PIDTuner(String name, PIDCommandPlus command) {
        this.name = name;

        PKey = name + " " + PKey;
        IKey = name + " " + IKey;
        DKey = name + " " + DKey;

        this.command = command;

        P = command.getP();
        I = command.getI();
        D = command.getD();

        SmartDashboard.putNumber(PKey, P);
        SmartDashboard.putNumber(IKey, I);
        SmartDashboard.putNumber(DKey, D);
    }

    public PIDTuner(PIDCommandPlus command) {
        this("", command); 
    }

    @Override
    protected void initialize() {
        P = SmartDashboard.getNumber(PKey, P);
        I = SmartDashboard.getNumber(IKey, I);
        D = SmartDashboard.getNumber(DKey, D);

        command.setP(P);
        command.setI(I);
        command.setD(D);

        command.start();
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber(name + " Error", command.getError());
    }

    @Override
    protected boolean isFinished() {
        return command.isCompleted();
    }
}