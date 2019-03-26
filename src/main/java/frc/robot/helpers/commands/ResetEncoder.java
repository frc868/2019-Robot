package frc.robot.helpers.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.helpers.motorcontrollers.CANSparkMaxPlus;

public class ResetEncoder extends InstantCommand {
  private CANSparkMaxPlus sparkMax;
  private WPI_TalonSRX talon;
  private Encoder encoder;

  public ResetEncoder(CANSparkMaxPlus sparkMax) {
    this.sparkMax = sparkMax;
  }

  public ResetEncoder(WPI_TalonSRX talon) {
    this.talon = talon;
  }

  public ResetEncoder(Encoder encoder) {
    this.encoder = encoder;
  }

  @Override
  protected void initialize() {
    if (sparkMax != null) {
      sparkMax.getEncoder().setPosition(0);
    } else if (talon != null) {
      talon.setSelectedSensorPosition(0);
    } else {
      encoder.reset();
    }
  }
}