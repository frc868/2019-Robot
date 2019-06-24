// package frc.robot.drivetrain.commands;

// import edu.wpi.first.wpilibj.PIDController;
// import edu.wpi.first.wpilibj.PIDOutput;
// import edu.wpi.first.wpilibj.PIDSource;
// import edu.wpi.first.wpilibj.PIDSourceType;
// import frc.robot.Robot;

// public class DriveStraightRamp extends DriveStraight {
//     public double startPower, endPower;
//     public PIDController distController;
//     private double P = 0.0001, I = 0, D = 0;
//     private PIDOutput output;
//     private PIDSource source;

//     public DriveStraightRamp(double targetDistance, double startPower, double endPower) {
//         super(targetDistance, startPower);
//         this.startPower = startPower;
//         this.endPower = endPower;
//         output = new PIDOutput(){
        
//             @Override
//             public void pidWrite(double output) {
//                 Robot.drivetrain.setSpeed(output, output);
//             }
//         };
//         source = new PIDSource(){
        
//             @Override
//             public void setPIDSourceType(PIDSourceType pidSource) {
//             }
        
//             @Override
//             public double pidGet() {
//                 return Robot.drivetrain.getAvgScaledDistance();
//             }
        
//             @Override
//             public PIDSourceType getPIDSourceType() {
//                 return PIDSourceType.kDisplacement;
//             }
//         };
//         distController = new PIDController(P,I,D,source,output);
//     }

//     public DriveStraightRamp(double targetDistance, double startPower, double endPower, double targetAngleChange) {
//         this(targetDistance, startPower, endPower);
//         super.targetAngleChange = targetAngleChange;
//     }

//     @Override
//     protected void execute() {
//         // super.targetPower = startPower + ((endPower - startPower) / distanceToTarget());
//     }

//     protected double distanceToTarget() {
//         return Math.abs(targetDistance) - Math.abs(Robot.drivetrain.getAvgScaledDistance() - initialDistance);
//     }

// }