/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.camera;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LimeData extends VisionData{

    NetworkTable table;
    NetworkTableEntry tv;
    NetworkTableEntry ta;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ts;
    
    public LimeData(){
    }

    public void init(){
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tv = table.getEntry("tv");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        ts = table.getEntry("ts");
    }

    @Override
    public boolean hasTarget() {
        return tv.getBoolean(false);
    }

    @Override
    public double getDistance() {
        return 0;
    }

    @Override
    public double getPosition() {
        return tx.getDouble(0.0);
    }

    @Override
    public double getAngle() {
        return ts.getDouble(0.0);
    }

    public void updateLimeData(){
        SmartDashboard.putNumber("lime pos", tx.getDouble(0.0));
        SmartDashboard.putNumber("lime angle", ts.getDouble(0.0));
    }
}
