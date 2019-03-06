package frc.robot.helpers.sensors;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class CANEncoderPlus extends CANEncoder implements Sendable {
    private String name, subsystem;    

    public CANEncoderPlus(CANSparkMax motor) {
        super(motor);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Quadrature Encoder");
        builder.addDoubleProperty("Speed", this::getVelocity, null);
        builder.addDoubleProperty("Distance", this::getPosition, null);
        builder.addDoubleProperty("Distance per Tick", this::getPositionConversionFactor, null);
    }

    @Override
    public String getSubsystem() {
        return subsystem;
    }

    @Override
    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    
}