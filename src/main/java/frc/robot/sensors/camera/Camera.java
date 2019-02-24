package frc.robot.sensors.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Camera extends SubsystemManagerChild {
  private SerialPort port;
  private UsbCamera jevois;
  private String raw;
  private double distance, position, heightRatio;
  
  private final int RES_WIDTH  = 320;
  private final int RES_HEIGHT = 240;
  private final int FPS_VISION = 10;
  private final int FPS_CAMERA = 15;
  private boolean vision_mode;

  public Camera() {
    super();
    port = new SerialPort(115200, RobotMap.Sensors.Camera.PORT);
  }

  @Override
  public void init() {
    jevois = CameraServer.getInstance().startAutomaticCapture();
    jevois.setResolution(RES_WIDTH, RES_HEIGHT);
    switchToVision();
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
    SmartDashboard.putData("Toggle Camera", new ToggleCamera());
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
    vision_mode = true;
  }

  public void switchToCamera() {
    jevois.setFPS(FPS_CAMERA);
    vision_mode = false;
  }

  public boolean isVisionMode() {
    return vision_mode;
  }
}