package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.helpers.pid.PIDCommandPlus;

public class OtherSetElevatorPosition extends PIDCommandPlus {
    public static final double P = 0.08 , I = 0.000, D = 0.00;
    private final Height height;

    public static enum Height {
        intake, lower, middle, upper;
    }

    public OtherSetElevatorPosition(Height height) {
        super(P, I, D);
        requires(Robot.powerPack);
        this.height = height;
        getPIDController().setAbsoluteTolerance(0.1);
        getPIDController().setOutputRange(-0.35, 1);
    }
    
    @Override
    protected void initialize() {
        if (height == Height.intake) {
            setSetpoint(PowerPack.INTAKE_BALL);
        } else if (height == Height.lower) {
            if (Robot.ballIntake.isBallDetected()) {
                setSetpoint(PowerPack.LOWER_BALL);
            } else {
                setSetpoint(PowerPack.LOWER_HATCH);
            }
        } else if (height == Height.middle) {
            if (Robot.ballIntake.isBallDetected()) {
                setSetpoint(PowerPack.MIDDLE_BALL);
            } else {
                setSetpoint(PowerPack.MIDDLE_HATCH);
            }
        } else if (height == Height.upper) {
            if (Robot.ballIntake.isBallDetected()) {
                setSetpoint(PowerPack.UPPER_BALL);
            } else {
                setSetpoint(PowerPack.UPPER_HATCH);
            }
        }


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
        if (height == Height.intake) {
            (new Grab()).start();
            (new SetTiltPosition(Tilt.LOWER)).start();;
        } else if (Robot.ballIntake.isBallDetected()) {
            (new SetTiltPosition(Tilt.UPPER)).start();;
        } 

        Robot.powerPack.stop();
        Robot.powerPack.elevatorBrakeOn();
    }
}