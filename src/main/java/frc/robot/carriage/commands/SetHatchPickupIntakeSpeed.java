// package frc.robot.carriage.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Robot;

// public class SetHatchPickupIntakeSpeed extends Command {
//     private double speed;

//     public SetHatchPickupIntakeSpeed(double speed) {
//         requires(Robot.hatchPickup);
//         this.speed = speed;
//     }

//     @Override
//     protected void initialize() {
//         Robot.hatchPickup.setIntakeSpeed(speed);
//     }

//     @Override
//     protected void end() {
//         Robot.hatchPickup.stopIntake();
//     }

//     @Override
//     protected void interrupted() {
//         end();
//     }
    
//     @Override
//     protected boolean isFinished() {
//         return false;
//     }
// }