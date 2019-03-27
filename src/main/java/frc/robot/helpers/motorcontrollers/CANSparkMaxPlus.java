package frc.robot.helpers.motorcontrollers;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.helpers.sensors.CANEncoderPlus;

public class CANSparkMaxPlus extends CANSparkMax {
    private String name, subsystem;
    private CANEncoderPlus encoder;

    public CANSparkMaxPlus(int port) {
        super(port, MotorType.kBrushless);
        // encoder = new CANEncoderPlus(this);
    }

    // @Override
    // public String getName() {
    //     return name;
    // }

    // @Override
    // public void setName(String name) {
    //     this.name = name;
    // }

    // @Override
    // public void initSendable(SendableBuilder builder) {
    //     // builder.setSmartDashboardType("Speed Controller");
	// 	// builder.setSafeState(this::stopMotor);
	// 	// builder.addDoubleProperty("Value", this::get, this::set);
    // }

    // @Override
    // public String getSubsystem() {
    //     return subsystem;
    // }

    // @Override
    // public void setSubsystem(String subsystem) {
    //     this.subsystem = subsystem;
    // }

    // @Override
    // public CANEncoderPlus getEncoder() {
    //     return encoder;
    // }
}