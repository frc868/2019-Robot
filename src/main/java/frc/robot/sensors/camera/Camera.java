package frc.robot.sensors.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Camera extends SubsystemManagerChild{
    
    private UsbCamera camera0, camera1;
    
    public Camera(){
        super("Camera");
    }
    
    @Override
    public void init(){
        camera0 = CameraServer.getInstance().startAutomaticCapture(0);
        camera0.setFPS(15);
        
        camera1 = CameraServer.getInstance().startAutomaticCapture(1);
        camera1.setFPS(15);
    }
}