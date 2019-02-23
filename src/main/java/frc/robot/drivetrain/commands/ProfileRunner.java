package frc.robot.drivetrain.commands;

import java.io.FileNotFoundException;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.helpers.Helper;
import frc.robot.Robot;
import frc.robot.helpers.motionprofiling.TrajectoryPair;

public class ProfileRunner extends Command {
    private TrajectoryPair pair;
    private final double P = 0.03, I = 0.00, D = 0.5, V = 0.3, A = 0.0; // constants for position keeping pids at max vel of 1.0
    //p = 0.025, d = 0.45
    private final double Pa = .0048, Ia = 0.0, Da = 0.05; // constants for angle keeping pids


    private int i = 0;
    private long lastTime;
    private double initialAngle;

    PIDSource leftSource = new PIDSource(){
    
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {}
    
        @Override
        public double pidGet() {
            return pair.getLeft()[i].position - Robot.drivetrain.getScaledLeftDistance();
        }
    
        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }
    };

    PIDSource rightSource = new PIDSource(){
    
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {}
    
        @Override
        public double pidGet() {
            return pair.getRight()[i].position - Robot.drivetrain.getScaledRightDistance();
        }
    
        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }
    };

    PIDSource angleSource = new PIDSource(){
    
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {}
    
        @Override
        public double pidGet() {
            return Helper.angleDiff(pair.getAvgAngle(i), (Robot.drivetrain.getGyroAngleRadians()-initialAngle));
        }
    
        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }
    };

    PIDOutput leftOutput = new PIDOutput(){
        @Override
        public void pidWrite(double output) {
            SmartDashboard.putNumber("Left PID Out", output);
            Robot.drivetrain.setLeftSpeed((output + pair.getLeft()[i].velocity * V + pair.getLeft()[i].acceleration * A));
        }
    };

    PIDOutput rightOutput = new PIDOutput(){
    
        @Override
        public void pidWrite(double output) {
            SmartDashboard.putNumber("Right PID Out", output);
            Robot.drivetrain.setRightSpeed((output + pair.getRight()[i].velocity * V + pair.getRight()[i].acceleration * A));
        }
    };

    PIDOutput angleOutput = new PIDOutput(){
    
        @Override
        public void pidWrite(double output) {
            Robot.drivetrain.adjustSpeed(output, -output);
        }
    };

    PIDController 
        left = new PIDController(P, I, D, leftSource, leftOutput), 
        right = new PIDController(P, I, D, rightSource, rightOutput),
        angle = new PIDController(Pa, Ia, Da, angleSource, angleOutput);


    public ProfileRunner() {
        requires(Robot.drivetrain);
    }
    
    @Override
    protected void initialize() {
        i = 0;
        try {
            pair = new TrajectoryPair("rightTurn");
            initialAngle = Robot.drivetrain.getGyroAngleRadians();
            Robot.drivetrain.resetEncoders();
            left.enable();
            right.enable();
            angle.enable();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } 
        lastTime = System.currentTimeMillis();
        SmartDashboard.putBoolean("Done", false);
    }

    @Override
    protected void execute() {
        if(System.currentTimeMillis() - lastTime > 50) {
            i++;
            lastTime = System.currentTimeMillis();
            SmartDashboard.putNumber("i", i);
        }
        SmartDashboard.putNumber("Left PID", left.get());
        SmartDashboard.putNumber("Right PID", right.get());

        SmartDashboard.putNumber("Right Source", rightSource.pidGet());
        SmartDashboard.putNumber("Left Source", leftSource.pidGet());
        SmartDashboard.putNumber("Angle Source", angleSource.pidGet());
        SmartDashboard.putNumber("i", i);
    }

    @Override
    protected void end() {
        left.disable();
        right.disable();
        angle.disable();
        Robot.drivetrain.stop();
        SmartDashboard.putBoolean("Done", true);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return i >= pair.getLength() - 1;
    }

}