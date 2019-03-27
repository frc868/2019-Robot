package frc.robot.sensors.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

import frc.robot.sensors.camera.FollowVision;

public class Camera extends SubsystemManagerChild {
  private SerialPort port;
  private UsbCamera camera0;//, camera1;

  public Camera() {
    super("Camera");
    port = new SerialPort(115200, RobotMap.Sensors.Camera.PORT);
  }

  @Override
  public void init() {
    //camera0 = CameraServer.getInstance().startAutomaticCapture(0);
    //camera0.setFPS(15);

    //camera1 = CameraServer.getInstance().startAutomaticCapture(1);
    //camera1.setFPS(15);

    camera0 = CameraServer.getInstance().startAutomaticCapture();
    camera0.setFPS(15);
  }

  public VisionData getData() {
    return new VisionData(port.readString());
  }

  private void sendData(String data) {
    port.writeString(data);
  }

  @Override
  public void update() {
    // VisionData data = getData();

    // SmartDashboard.putString("Camera: Raw", data.getRawData());

    // SmartDashboard.putNumber("Camera: Distance", data.getDistance());
    // SmartDashboard.putNumber("Camera: Position", data.getPosition());
    // SmartDashboard.putNumber("Camera: Angle", data.getAngle());
    
    // SmartDashboard.putBoolean("Camera: Connected?", data.hasComs());
    // SmartDashboard.putBoolean("Camera: Target?", data.hasTarget());
  }
}