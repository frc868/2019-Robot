package frc.robot.sensors.camera;

import java.awt.Color;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Camera extends SubsystemManagerChild {
//   private SerialPort port;
  private UsbCamera camera;
//   private boolean vision_mode;

  public Camera() {
    super("Camera");
    // port = new SerialPort(115200, RobotMap.Sensors.Camera.PORT);
  }

  @Override
  public void init() {
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setFPS(15);

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