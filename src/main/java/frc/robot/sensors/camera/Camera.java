package frc.robot.sensors.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Camera extends SubsystemManagerChild {
  private SerialPort port;
  private UsbCamera camera0, camera1;
  private VisionData data;
  // private final int WIDTH = 240, HEIGHT = 180;
  private final int WIDTH = 1, HEIGHT = 1;

  public Camera() {
    super("Camera");
  }

  @Override
  public void init() {
    camera0 = CameraServer.getInstance().startAutomaticCapture(0);
    // camera0.setResolution(WIDTH, HEIGHT);
    // camera0.setExposureAuto();
    camera0.setFPS(15);

    camera1 = CameraServer.getInstance().startAutomaticCapture(1);
    // camera1.setResolution(WIDTH, HEIGHT);
    // camera1.setExposureAuto();
    camera1.setFPS(15);

    try {
      port = new SerialPort(115200, RobotMap.Sensors.Camera.PORT);
    } catch (Exception e) {}
  }

  public void updateData(){
    VisionData nextData = new VisionData(port.readString());

    if (nextData.hasComs()) {
      data = nextData;
    }
  }

  public VisionData getData() {
    return data;
  }

  private void sendData(String data) {
    port.writeString(data);
  }

  @Override
  public void update() {
    try {
      // SmartDashboard.putString("ttest", port.readString());
      updateData();

      SmartDashboard.putString("Camera: Raw", data.getRawData());

      SmartDashboard.putNumber("Camera: Distance", data.getDistance());
      SmartDashboard.putNumber("Camera: Position", data.getPosition());
      SmartDashboard.putNumber("Camera: Angle", data.getAngle());
      
      SmartDashboard.putBoolean("Camera: Connected?", data.hasComs());
      SmartDashboard.putBoolean("Camera: Target?", data.hasTarget());
    } catch (Exception e) {}
    
  }
}