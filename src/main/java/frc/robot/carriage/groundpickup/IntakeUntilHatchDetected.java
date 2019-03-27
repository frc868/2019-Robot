// package frc.robot.carriage.groundpickup;

// import frc.robot.Robot;

// public class IntakeUntilHatchDetected extends SetGroundPickupIntakeSpeed {
//     private static final double DEFAULT_POWER = -1.0;
    
//     private int countsDetected = 0;
//     private final int COUNTS_NEEDED = 5; // the counts needed to determine that the hatch is indeed detected

//     public IntakeUntilHatchDetected(double power) {
//         super(power);
//     }

//     public IntakeUntilHatchDetected() {
//         super(DEFAULT_POWER);
//     }

//     @Override
//     protected void execute() {
//         if (Robot.groundPickup.isHatchDetected()) { // if the hatch is detected, add to counts detected
//             countsDetected++;
//         } else { // else reset counts detected
//             countsDetected = 0;
//         }
//     }

//     @Override
//     protected boolean isFinished() {
//         // return true if the hatch has been detected for the counts needed to determine the intake has the hatch
//         return countsDetected >= COUNTS_NEEDED;  
//     }
// }