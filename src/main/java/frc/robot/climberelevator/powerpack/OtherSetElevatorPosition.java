package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;

public class OtherSetElevatorPosition extends PIDCommand {
    public static final double P = 0.08 , I = 0.000, D = 0.00;
    private final double setpoint;

    public OtherSetElevatorPosition(double setpoint) {
        super(P, I, D);
        this.setpoint = setpoint;
        requires(Robot.powerPack);
        getPIDController().setAbsoluteTolerance(0.1);
        getPIDController().setOutputRange(-0.35, 1);
    }
    
    @Override
    protected void initialize() {
        (new SetTiltPosition(Tilt.MIDDLE)).start();
        Robot.powerPack.switchToElevator();
        Robot.powerPack.elevatorBrakeOff();
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Elevator Error", getError());
    }

    @Override
    protected double returnPIDInput() {
        return Robot.powerPack.getEncPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.powerPack.setSpeed(output);
    }

    @Override
    protected void end() {
        // if (setpoint == Robot.powerPack.INTAKE_BALL) {
        //     Robot.hatchClaw.grab();
        //     // (new Grab()).start();
        //     // (new SetTiltPosition(Tilt.LOWER)).start();
        // } 
        // else if (setpoint == Robot.powerPack.LOWER_BALL || setpoint == Robot.powerPack.MIDDLE_BALL || setpoint == Robot.powerPack.UPPER_BALL) {
        //     (new SetTiltPosition(Tilt.UPPER)).start();
        // }
        Robot.powerPack.stop();
        Robot.powerPack.elevatorBrakeOn();

        // TODO GET RID OF THIS
        // old way that we did it before match 11 queued 
        // if (getSetpoint() == Robot.powerPack.INTAKE_BALL) {
        //     (new SetTiltPosition(Tilt.LOWER)).start();
        //     (new Grab()).start();
        
        // } else if (getSetpoint() == Robot.powerPack.LOWER_BALL || getSetpoint() == Robot.powerPack.MIDDLE_BALL || getSetpoint() == Robot.powerPack.UPPER_BALL) {
        //     (new SetTiltPosition(Tilt.UPPER)).start();
        // }

    }
}