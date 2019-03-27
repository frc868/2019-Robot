package frc.robot.helpers.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoder extends Command{
    
    private CANSparkMax sparkMax;
    private WPI_TalonSRX talon;
    private Encoder encoder;
    
    public ResetEncoder(CANSparkMax sparkMax){
        this.sparkMax = sparkMax;
    }
    
    public ResetEncoder(WPI_TalonSRX talon){
        this.talon = talon;
    }
    
    public ResetEncoder(Encoder encoder){
        this.encoder = encoder;
    }
    
    @Override
    protected void initialize(){
        if(sparkMax != null){
            sparkMax.getEncoder().setPosition(0);
        }else if(talon != null){
            talon.setSelectedSensorPosition(0);
        }else{
            encoder.reset();
        }
    }
    
    @Override
    protected boolean isFinished(){
        return true;
    }
}