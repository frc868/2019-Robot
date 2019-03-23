package frc.robot.sensors.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Camera extends SubsystemManagerChild {
//   private SerialPort port;
  private UsbCamera camera0, camera1;
//   private boolean vision_mode;

  public Camera() {
    super("Camera");
    // port = new SerialPort(115200, RobotMap.Sensors.Camera.PORT);
  }

  @Override
  public void init() {
    camera0 = CameraServer.getInstance().startAutomaticCapture(0);
    camera0.setFPS(15);

    camera1 = CameraServer.getInstance().startAutomaticCapture(1);
    camera1.setFPS(15);


  //   new Thread(() -> {
  //     camera = CameraServer.getInstance().startAutomaticCapture();
  //     camera.setResolution(640, 480);
      
  //     CvSink cvSink = CameraServer.getInstance().getVideo();
  //     CvSource outputStream = CameraServer.getInstance().putVideo("Target", 640, 480);
      
  //     Mat source = new Mat();
      
  //     if(!Thread.interrupted()) {
  //         cvSink.grabFrame(source);
  //         Imgproc.line(source, new Point(640/2, 0), new Point(640/2, 480), new Scalar(0, 255, 0));
  //         outputStream.putFrame(source);
  //     }
  //   }).start();
  }

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
}