/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  /**CREATING BUTTONS
   * One type of button is a joystick button which is any button on a
   * joystick.
   * You create one by telling it which joystick it's on and which button
   * number it is.
   * Joystick stick = new Joystick(port);
   * Button button = new JoystickButton(stick, buttonNumber);
   *
   * There are a few additional built in buttons you can use. Additionally,
   * by subclassing Button you can create custom triggers and bind those to
   * commands the same as any other Button.
   * TRIGGERING COMMANDS WITH BUTTONS
   * Once you have a button, it's trivial to bind it to a button in one of
   * three ways:
   *
   * Start the command when the button is pressed and let it run the command
   * until it is finished as determined by it's isFinished method.
   * button.whenPressed(new ExampleCommand());
   *
   * Run the command while the button is being held down and interrupt it once
   * the button is released.
   * button.whileHeld(new ExampleCommand());
   *
   * Start the command when the button is released and let it run the command
   * until it is finished as determined by it's isFinished method.
   * button.whenReleased(new ExampleCommand());
   */
  
  public static XboxController driver;
  public static XboxController operator;

  public static void initDriver(){
    driver = new XboxController(RobotMap.Controls.DRIVER);

    Button a = new JoystickButton(driver, RobotMap.Controls.A);
    Button b = new JoystickButton(driver, RobotMap.Controls.B);
    Button x = new JoystickButton(driver, RobotMap.Controls.X);
    Button y = new JoystickButton(driver, RobotMap.Controls.Y);
    Button rb = new JoystickButton(driver, RobotMap.Controls.RB);
    Button lb = new JoystickButton(driver, RobotMap.Controls.LB);
    Button rstk = new JoystickButton(driver, RobotMap.Controls.RSTK);
    Button lstk = new JoystickButton(driver, RobotMap.Controls.LSTK);

  }

  public static void initOperator(){
    operator = new XboxController(RobotMap.Controls.OPERATOR);

    Button a = new JoystickButton(driver, RobotMap.Controls.A);
    Button b = new JoystickButton(driver, RobotMap.Controls.B);
    Button x = new JoystickButton(driver, RobotMap.Controls.X);
    Button y = new JoystickButton(driver, RobotMap.Controls.Y);
    Button rb = new JoystickButton(driver, RobotMap.Controls.RB);
    Button lb = new JoystickButton(driver, RobotMap.Controls.LB);
    Button rstk = new JoystickButton(driver, RobotMap.Controls.RSTK);
    Button lstk = new JoystickButton(driver, RobotMap.Controls.LSTK);
  }

  public static void updateDriver() {
    double lx = driver.getRawAxis(RobotMap.Controls.LX);
    double ly = driver.getRawAxis(RobotMap.Controls.LY);
    double rx = driver.getRawAxis(RobotMap.Controls.RX);
    double ry = driver.getRawAxis(RobotMap.Controls.RY);
    double lt = driver.getRawAxis(RobotMap.Controls.LT);
    double rt = driver.getRawAxis(RobotMap.Controls.RT);

    Robot.drivetrain.setSpeed(ly + lx, ly - lx);
  }

  public static void updateOperator() {
    double lx = operator.getRawAxis(RobotMap.Controls.LX);
    double ly = operator.getRawAxis(RobotMap.Controls.LY);
    double rx = operator.getRawAxis(RobotMap.Controls.RX);
    double ry = operator.getRawAxis(RobotMap.Controls.RY);
    double lt = operator.getRawAxis(RobotMap.Controls.LT);
    double rt = operator.getRawAxis(RobotMap.Controls.RT);
  }

}
