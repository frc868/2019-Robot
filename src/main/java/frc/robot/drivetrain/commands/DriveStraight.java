package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class DriveStraight extends PIDCommand {
    public double initialDist,targetDist;//initialAngle,targetAngle;
    // private double Pa = 0.001, Ia = 0, Da = 0;
    private static final double Pd = -.05, Id = 0, Dd = 0;

    // private double angleOut;
    // private double distOut;

    // private PIDSource distSource,angleSource;
    // private PIDController distController,angleController;
    // private PIDOutput distOutput,angleOutput;

    // public DriveStraight(double targetDist) {
    //     this(targetDist,0);
    // }

    public DriveStraight(double targetDist) {
        super(Pd,Id,Dd);
        requires(Robot.drivetrain);
        this.targetDist = targetDist;
        // this.targetAngle = targetAngle;

        // distSource = new PIDSource(){
        
        //     @Override
        //     public void setPIDSourceType(PIDSourceType pidSource) {}
        
        //     @Override
        //     public double pidGet() {
        //         return Robot.drivetrain.getAvgScaledDistance();
        //     }
        
        //     @Override
        //     public PIDSourceType getPIDSourceType() {
        //         return PIDSourceType.kDisplacement;
        //     }
        // };
        // distOutput = new PIDOutput(){
        
        //     @Override
        //     public void pidWrite(double output) {
        //         System.out.println("Output!:" + output);
        //         Robot.drivetrain.setSpeed(Helper.boundValue(output), Helper.boundValue(output));
        //     }
        }

        // angleSource = new PIDSource(){
        
        //     @Override
        //     public void setPIDSourceType(PIDSourceType pidSource) {}
        
        //     @Override
        //     public double pidGet() {
        //         return Robot.gyro.getAngle();
        //     }
        
        //     @Override
        //     public PIDSourceType getPIDSourceType() {
        //         return PIDSourceType.kDisplacement;
        //     }
        // };
        // angleOutput = new PIDOutput(){
        
        //     @Override
        //     public void pidWrite(double output) {
        //         angleOut = output;
        //     }
        // };

        // distController = new PIDController(Pd, Id, Dd, distSource, distOutput);
    // }

    @Override
    protected double returnPIDInput() {
        return targetDist - Robot.drivetrain.getAvgScaledDistance();
    }

    @Override
    protected void usePIDOutput(double output) {
        System.out.println(output);
        double speed = Helper.boundValue(output);
        Robot.drivetrain.setSpeed(speed, speed);
    }

    @Override
    public void initialize() {
        initialDist = Robot.drivetrain.getAvgScaledDistance();
        System.out.println("Started!!!!!!!!!!!!!!!!!!!!  " + initialDist);
    }

    // public void combineOutputs(double angle, double distance) {
    //     Robot.drivetrain.setSpeed(distance-angle, distance+angle);
    // }

    @Override
    public void execute() {
        // combineOutputs(angleOut, distOut);
        SmartDashboard.putNumber("Drive Straight Error", targetDist - Robot.drivetrain.getAvgScaledDistance());
    }

    @Override
    public boolean isFinished() {
        return (Robot.drivetrain.getAvgScaledDistance() - initialDist) > targetDist;
        // return false;
    }

    @Override
    protected void end() {
        System.out.println("STOPPED===============");
    }

}