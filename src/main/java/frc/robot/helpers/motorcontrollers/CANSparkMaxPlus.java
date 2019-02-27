package frc.robot.helpers.motorcontrollers;

import com.revrobotics.CANSparkMax;

public class CANSparkMaxPlus extends CANSparkMax {

    public CANSparkMaxPlus(int port) {
        super(port, MotorType.kBrushless);
    }

}