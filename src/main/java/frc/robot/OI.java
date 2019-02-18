/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.helpers.XboxControllerPlus;

public class OI {
  
  public static XboxControllerPlus driver;
  public static XboxControllerPlus operator;

  public static void initDriver(){
    driver = new XboxControllerPlus(RobotMap.Controls.DRIVER);
  }

  public static void initOperator(){
    operator = new XboxControllerPlus(RobotMap.Controls.OPERATOR); 
  }

  public static void updateDriver() {
    
  }

  public static void updateOperator() {
    
  }

}
