package frc.robot.helpers.pid;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDTuner extends Command {
    private PIDCommandPlus command;
    private double P, I, D;
    private String PKey = "P", IKey = "I", DKey = "D";

    public PIDTuner(String name, PIDCommandPlus command) {
        PKey = name + " " + PKey;
        IKey = name + " " + IKey;
        DKey = name + " " + DKey;
        this.command = command;
        setup();
    }

    public PIDTuner(PIDCommandPlus command) {
        this.command = command;
        setup();   
    }

    private void setup() {
        P = command.getP();
        I = command.getI();
        D = command.getD();

        SmartDashboard.putNumber(PKey, P);
        SmartDashboard.putNumber(IKey, I);
        SmartDashboard.putNumber(DKey, D);
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
    protected boolean isFinished() {
        return command.isCompleted();
    }
}