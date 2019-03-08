package frc.robot.sensors.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Camera extends SubsystemManagerChild {
  private SerialPort port;
  private UsbCamera jevois;
  private boolean vision_mode;

  public Camera() {
    super("Camera");
    port = new SerialPort(115200, RobotMap.Sensors.Camera.PORT);
  }

  @Override
  public void init() {
    jevois = CameraServer.getInstance().startAutomaticCapture();
    switchToVision();
  }

  public VisionData getData() {
    return new VisionData(port.readString());
  }

  private void sendData(String data) {
    port.writeString(data);
  }

  public void switchToVision() {
    vision_mode = true;
  }

  public void switchToCamera() {
    vision_mode = false;
  }

  public boolean isVisionMode() {
    return vision_mode;
  }
}