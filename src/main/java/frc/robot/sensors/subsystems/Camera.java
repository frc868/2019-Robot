/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.helpers.SubsystemManagerChild;

public class Camera extends SubsystemManagerChild {
  private SerialPort port;
  private String raw;
  private double distance, position, heightDifference;
  private boolean isVisionCam = false;

  public Camera(boolean isVisionCam) {
    super();
    this.isVisionCam = isVisionCam;

    port = new SerialPort(115200, SerialPort.Port.kUSB1);
  }

  @Override
  public void init() {
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void update() {
    try {
      String newData = port.readString();
      if (newData != null && !newData.equals("")) {  
        raw = newData;      
        if (isVisionCam && hasTarget()) {
          String[] dataArray = raw.split(",");
          distance = Double.parseDouble(dataArray[0]);
          position = Double.parseDouble(dataArray[1]);
          heightDifference = Double.parseDouble(dataArray[2]);  
        }
      }
    } catch (Exception e) {}
  }

  @Override
  public void updateSD() {
    SmartDashboard.putString("Raw Data", getRawData());
    if (isVisionCam) {
      SmartDashboard.putBoolean("Has Target", hasTarget());
      SmartDashboard.putNumber("Distance", getDistance());
      SmartDashboard.putNumber("Position", getPosition());
      SmartDashboard.putNumber("Height Difference", getHeightDifference());
    }
  }

  public boolean hasTarget() {
    return isVisionCam && !getRawData().contains(",,");
  }

  public String getRawData() {
    return raw;
  }

  public double getDistance() {
    return distance;
  }

  public double getPosition() {
    return position;
  }

  public double getHeightDifference() {
    return heightDifference;
  }

  public void sendData(String data) {
    port.writeString(data);
  }

  public void switchToVisionAlgorithm() {
    sendData("setmapping MJPG 320 240 10 YUYV 640 480 30 TechHOUNDS DeepSpace");
    isVisionCam = true;
  }

  public void switchToCameraView() {
    sendData("setmapping MJPG 320 240 10 YUYV 320 240 10 TechHOUNDS868 Trash2019");
    isVisionCam = false;
  }
}
