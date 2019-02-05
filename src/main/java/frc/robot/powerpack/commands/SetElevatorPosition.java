// package frc.robot.powerpack.commands;

// import edu.wpi.first.wpilibj.command.PIDCommand;
// import frc.robot.Robot;

// public class SetElevatorPosition extends PIDCommand {
//     public static final double P = 1.0, I = 0.0, D = 0.0;
//     public final double LOWER = 0, MIDDLE = 1, UPPER = 2;

//     public SetElevatorPosition(double setpoint) {
//         super("SetElevatorPosition", P, I, D);
//         requires(Robot.powerPack);
//         setSetpoint(setpoint);
//     }
    
//     @Override
//     protected void initialize() {
//         Robot.powerPack.switchToElevator();
//     }

//     @Override
//     protected double returnPIDInput() {
//         return Robot.powerPack.getEncPosition();
//     }

//     @Override
//     protected void usePIDOutput(double output) {
//         Robot.powerPack.setSpeed(output);
//     }

//     @Override
//     protected boolean isFinished() {
//         return false; //TODO fix this
//     }
// }