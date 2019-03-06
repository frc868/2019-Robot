package frc.robot.helpers.motorcontrollers;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class CANSparkMaxPlus extends CANSparkMax implements Sendable {
    private String name;

    public CANSparkMaxPlus(int port) {
        super(port, MotorType.kBrushless);
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

    }

    @Override
    public String getSubsystem() {
        return null;
    }

    @Override
    public void setSubsystem(String subsystem) {
        
    }
}