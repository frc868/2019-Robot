/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.tilt;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetTiltPositionFeedForward extends Command {
  private double setpoint;

  public SetTiltPositionFeedForward(double setpoint) {
		this.setpoint = setpoint;
    requires(Robot.tilt);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double error = -(setpoint - Robot.tilt.getPotPosition());  //positive error means need to go up
    // LOWER = 0.85, MIDDLE = 0.838, UPPER = .708



    if(setpoint == Tilt.LOWER)  { //go to lower setpoint

      if(Robot.ballIntake.isBallDetected()==true) { //if you have a ball
        
  			if (error > .001) { // need to move up
          Robot.tilt.setSpeed(.5);
  			} else if (error < -.001) { //need to go down
          Robot.tilt.setSpeed(-.05);
		  	} else {
          Robot.tilt.setSpeed(.15); //need to hold it there
		  	}

      } else if(Robot.hatchClaw.isGrabbed()==true)  { //if you have a hatch
        
  			if (error > .001) { // need to move up
          Robot.tilt.setSpeed(.5);
  			} else if (error < -.001) { //need to go down
          Robot.tilt.setSpeed(-.05);
		  	} else {
          Robot.tilt.setSpeed(.15); //need to hold it there
		  	}

      } else  {  //if you have no field objects
        
  			if (error > .001) { // need to move up
          Robot.tilt.setSpeed(.5);
  			} else if (error < -.001) { //need to go down
          Robot.tilt.setSpeed(-.05);
		  	} else {
          Robot.tilt.setSpeed(.15); //need to hold it there
		  	}

      }
    } else if(setpoint == Tilt.MIDDLE) {
      if(Robot.ballIntake.isBallDetected()==true) { //if you have a ball
        
  			if (error > .001) { // need to move up
          Robot.tilt.setSpeed(.5);
  			} else if (error < -.001) { //need to go down
          Robot.tilt.setSpeed(-.05);
		  	} else {
          Robot.tilt.setSpeed(.15); //need to hold it there
		  	}

      } else if(Robot.hatchClaw.isGrabbed()==true)  { //if you have a hatch
        
  			if (error > .001) { // need to move up
          Robot.tilt.setSpeed(.5);
  			} else if (error < -.001) { //need to go down
          Robot.tilt.setSpeed(-.05);
		  	} else {
          Robot.tilt.setSpeed(.15); //need to hold it there
		  	}

      } else  {  //if you have no field objects
        
  			if (error > .001) { // need to move up
          Robot.tilt.setSpeed(.5);
  			} else if (error < -.001) { //need to go down
          Robot.tilt.setSpeed(-.05);
		  	} else {
          Robot.tilt.setSpeed(.15); //need to hold it there
        }
      }


    } else if(setpoint == Tilt.UPPER) { //if we want to go to upper
      if(Robot.ballIntake.isBallDetected()==true) { //if you have a ball
        
        if(error > .1)  { //need to go up ALOT  (power needed earlier in the rotation)
          Robot.tilt.setSpeed(.5);
        }
  			if (error > .001) { // need to move up  (power needed for fine adjustments at the top)
          Robot.tilt.setSpeed(.2);
  			} else if (error < -.001) { //need to go down
          Robot.tilt.setSpeed(-.05);
		  	} else {
          Robot.tilt.setSpeed(.15); //need to hold it there
		  	}

      } else if(Robot.hatchClaw.isGrabbed()==true)  { //if you have a hatch
        
  			if (error > .001) { // need to move up
          Robot.tilt.setSpeed(.5);
  			} else if (error < -.001) { //need to go down
          Robot.tilt.setSpeed(-.05);
		  	} else {
          Robot.tilt.setSpeed(.15); //need to hold it there
		  	}

      } else  {  //if you have no field objects
        
  			if (error > .001) { // need to move up
          Robot.tilt.setSpeed(.5);
  			} else if (error < -.001) { //need to go down
          Robot.tilt.setSpeed(-.05);
		  	} else {
          Robot.tilt.setSpeed(.15); //need to hold it there
        }
        
      } 
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
