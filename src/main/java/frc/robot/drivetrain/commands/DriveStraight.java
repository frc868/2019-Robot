// package frc.robot.drivetrain.commands;

// import edu.wpi.first.wpilibj.command.PIDCommand;
// import frc.robot.Robot;

// public class DriveStraight extends PIDCommand {
//     private static final double P = 1.0, I = 0.0, D = 0.0;
//     public double initialDistance;
//     public double targetDistance, targetPower, targetAngleChange;

//     public DriveStraight(double targetDistance, double targetPower) {
//         super("DriveStraight", P, I, D);
//         this.targetDistance = targetDistance;
//         this.targetPower = targetPower;
//     }
    
//     public DriveStraight(double targetDistance, double targetPower, double targetAngleChange) {
//         this(targetDistance, targetPower);
//         this.targetAngleChange = targetAngleChange;
//     }


//     @Override
//     protected void initialize() {
//         setSetpoint(Robot.gyro.getRotation() + targetAngleChange);
//         initialDistance = Robot.drivetrain.getAvgScaledDistance();
//     }

//     @Override
//     protected double returnPIDInput() {
//         return Robot.gyro.getRotation();
//     }

//     @Override
//     protected void usePIDOutput(double output) {
//         double left = targetPower + output;
//         double right = targetPower - output;
//         Robot.drivetrain.setSpeed(left, right);
//     }


//     @Override
//     protected boolean isFinished() {
//     	return distanceToTarget() < 0;
//     }

//     @Override
//     protected void end() {
//         Robot.drivetrain.stop();
//     }

//     @Override
//     protected void interrupted() {
//         end();
//     }

//     protected double distanceToTarget() {
//         return Math.abs(targetDistance) - Math.abs(Robot.drivetrain.getAvgScaledDistance() - initialDistance);
//     }
// }