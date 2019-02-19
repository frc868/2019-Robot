/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.helpers.SubsystemManagerChild;
import frc.robot.sensors.vision.SwitchToCamera;
import frc.robot.sensors.vision.SwitchToVision;

public class Camera extends SubsystemManagerChild {
  private SerialPort port;
  private UsbCamera  jevois;
  private String     raw;
  private double     distance, position, heightRatio;
  
  private final int RES_WIDTH  = 320;
  private final int RES_HEIGHT = 240;
  private final int FPS_VISION = 10;
  private final int FPS_CAMERA = 15;

  public Camera() {
    super();
    // following assumes that cam is on port 1 *which is robot-specific*
    port = new SerialPort(115200, SerialPort.Port.kUSB1);
  }

  @Override
  public void init() {
    // change the param of startAutomaticCapture to whatever cam you're using
    jevois = CameraServer.getInstance().startAutomaticCapture(); // returns a UsbCamera
    jevois.setResolution(RES_WIDTH, RES_HEIGHT);
    this.switchToVision();
  }

  @Override
  public void update() {
    try {
      String newData = port.readString();
      if (newData != null && !newData.equals("")) {  
        raw = newData;      
        if (hasTarget()) {
          String[] dataArray = raw.split(",");

          distance = Double.parseDouble(dataArray[0]);
          position = Double.parseDouble(dataArray[1]);
          heightRatio = Double.parseDouble(dataArray[2]);
        } else {
          distance = -1; // "null", want to avoid NPEs
          position = -1;
          heightRatio = -1;
        }
      }
    } catch (Exception e) {}
  }

  @Override 
  public void initSD() {
    SmartDashboard.putData("Switch to Camera", new SwitchToCamera());
    SmartDashboard.putData("Switch to Vision", new SwitchToVision());
  }

  @Override
  public void updateSD() {
    SmartDashboard.putBoolean("Has Target", hasTarget());
    SmartDashboard.putString("Raw Data", getRawData());
    SmartDashboard.putNumber("Distance", getDistance());
    SmartDashboard.putNumber("Position", getPosition());
    SmartDashboard.putNumber("Height Ratio", getHeightRatio());
  }

  public boolean hasTarget() {
    return !getRawData().contains(",,");
  }

  public String getRawData() {
    return raw != null ? raw : ",,";
  }

  public double getDistance() {
    return distance;
  }

  public double getPosition() {
    return position - RES_WIDTH/2;
  }

  public double getHeightRatio() {
    return heightRatio;
  }

  public void sendData(String data) {
    port.writeString(data);
  }

  public void switchToVision() {
    jevois.setFPS(FPS_VISION);
  }

  public void switchToCamera() {
    jevois.setFPS(FPS_CAMERA);
  }
}