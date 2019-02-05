// package frc.robot.powerpack.commands;

// import edu.wpi.first.wpilibj.command.PIDCommand;
// import frc.robot.Robot;

// public class SetClimberPosition extends PIDCommand {
//     public static final double P = 1.0, I = 0.0, D = 0.0;
//     public final double FLOOR = 0, LEVEL_ONE = 1, LEVEL_TWO = 2;

//     public SetClimberPosition(double setpoint) {
//         super("SetClimberPosition", P, I, D);
//         requires(Robot.powerPack);
//         setSetpoint(setpoint);
//     }
    
//     @Override
//     protected void initialize() {
//         Robot.powerPack.switchToClimber();
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