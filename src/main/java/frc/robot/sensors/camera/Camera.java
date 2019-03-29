package frc.robot.sensors.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Camera extends SubsystemManagerChild {
  private SerialPort port;
  private UsbCamera camera0, camera1;
  private VisionData data;

  public Camera() {
    super("Camera");
  }

  @Override
  public void init() {
    camera0 = CameraServer.getInstance().startAutomaticCapture(0);
    camera0.setFPS(15);

    camera1 = CameraServer.getInstance().startAutomaticCapture(1);
    camera1.setFPS(15);

    // port = new SerialPort(115200, RobotMap.Sensors.Camera.PORT);
  }

  public VisionData getData() {
    data = new VisionData(port.readString());
    return data;
  }

  private void sendData(String data) {
    port.writeString(data);
  }

  @Override
  public void update() {
    // SmartDashboard.putString("Camera: Raw", data.getRawData());

    // SmartDashboard.putNumber("Camera: Distance", data.getDistance());
    // SmartDashboard.putNumber("Camera: Position", data.getPosition());
    // SmartDashboard.putNumber("Camera: Angle", data.getAngle());
    
    // SmartDashboard.putBoolean("Camera: Connected?", data.hasComs());
    // SmartDashboard.putBoolean("Camera: Target?", data.hasTarget());
  }
}