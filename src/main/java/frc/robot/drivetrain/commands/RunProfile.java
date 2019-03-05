package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.Helper;
import frc.robot.Robot;
import frc.robot.helpers.motionprofiling.TrajectoryPair;

public class RunProfile extends Command {
    private TrajectoryPair pair;
    private final double P = 0.03, I = 0.00, D = 0.5, V = 0.3, A = 0.0; // constants for position keeping pids at max vel of 1.0
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
            return Helper.angleDiff(pair.getAvgAngle(i), (Robot.drivetrain.getGyroRestrictedAngleRadians()-initialAngle));
        }
    
        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }
    };

    PIDOutput leftOutput = new PIDOutput(){
        @Override
        public void pidWrite(double output) {
            Robot.drivetrain.setLeftSpeed((output + pair.getLeft()[i].velocity * V + pair.getLeft()[i].acceleration * A));
        }
    };

    PIDOutput rightOutput = new PIDOutput(){
    
        @Override
        public void pidWrite(double output) {
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


    public RunProfile(String filename) {
        requires(Robot.drivetrain);
        pair = new TrajectoryPair(filename);
    }
    
    @Override
    protected void initialize() {
        initialAngle = Robot.drivetrain.getGyroRestrictedAngleRadians();
        Robot.drivetrain.resetEncPositions();
        
        left.enable();
        right.enable();
        angle.enable();
         
        lastTime = System.currentTimeMillis();
    }

    @Override
    protected void execute() {
        if(System.currentTimeMillis() - lastTime > 50) {
            i++;
            lastTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void end() {
        left.disable();
        right.disable();
        angle.disable();
        Robot.drivetrain.stop();
    }

    @Override
    protected boolean isFinished() {
        return i >= pair.getLength() - 1;
    }

}