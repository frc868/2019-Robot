// package frc.robot.sensors.camera;

// import edu.wpi.cscore.UsbCamera;
// import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.wpilibj.SerialPort;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.RobotMap;
// import frc.robot.helpers.subsystems.SubsystemManagerChild;

// public class Camera extends SubsystemManagerChild {
//   private SerialPort port;
//   private UsbCamera jevois;
//   private boolean vision_mode;

//   public Camera() {
//     super("Camera");
//     port = new SerialPort(115200, RobotMap.Sensors.Camera.PORT);
//   }

//   @Override
//   public void init() {
//     jevois = CameraServer.getInstance().startAutomaticCapture();
//     switchToCamera();

//     // SmartDashboard.putData("Toggle Camera", new ToggleCamera());
//   }

//   public VisionData getData() {
//     return new VisionData(port.readString());
//   }

//   private void sendData(String data) {
//     port.writeString(data);
//   }

//   public void switchToVision() {
//     SmartDashboard.putBoolean("Vision Mode", true);
//     sendData("setmapping2 MJPG 320 240 10 TechHOUNDS DeepSpace");
//     sendData("streamon");
//     jevois.setFPS(10);
//     vision_mode = true;
//   }

//   public void switchToCamera() {
//     SmartDashboard.putBoolean("Vision Mode", false);
//     sendData("setmapping2 MJPG 320 240 15 TechHOUNDS868 Trash2019");
//     sendData("streamon");
//     jevois.setFPS(15);
//     vision_mode = false;
//   }

//   public boolean isVisionMode() {
//     return vision_mode;
//   }
// }