package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;
import frc.robot.sensors.camera.FollowVision;
import frc.robot.sensors.camera.LimeData;

public class ManualFollowVision extends FollowVision {

    public ManualFollowVision() {
        super();
        super.k_dist = 0; // stop distance from having an effect as the driver will control this
    }

    @Override
    protected void execute() {
        LimeData data = Robot.camera.getData();

        // try {
            if (data.hasTarget()) {
                double area = data.getArea();
                double posError = data.getPosition();
                double posValue = (posError * k_pos) * Math.sqrt(Helper.boundValue(area * a_correction, 0, 1));

                double angleError = data.getAngle();
                double angleValue = 0.0;//angleError * k_angle;

                SmartDashboard.putNumber("Angle Value", angleValue);
                SmartDashboard.putString("hello", "i wok");

                double manual_y = Helper.deadzone(-OI.driver.getLY(), .03);

                double left = manual_y + ((posValue + angleValue)); /// 10.0);
                double right = manual_y - ((posValue + angleValue));// / 10.0);

                Robot.drivetrain.setSpeed(left, right);
            }
        // } catch(NullPointerException npe) {
        //     System.out.println("NULL POINTER EXCEPTION CAUGHT");
        // }
    }
}
