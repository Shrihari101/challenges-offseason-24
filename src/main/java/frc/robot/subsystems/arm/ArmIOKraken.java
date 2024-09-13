package frc.robot.subsystems.arm;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class ArmIOKraken implements ArmIO {
  // For instructions on how to implement this class, refer to the README.md file
  private TalonFX m_motor;

  public ArmIOKraken(int port) {
    // TODO: Implement this method
    m_motor = new TalonFX(port);
  }

  @Override
  public void setVoltage(double voltage) {
    m_motor.setVoltage(voltage);
  }

  @Override
  public double getVoltage() {
    // TODO: Implement this method
    return m_motor.getSupplyVoltage().getValueAsDouble();
  }

  @Override
  public double getVelocityRadiansPerSecond() {
    double rps = m_motor.getVelocity().getValueAsDouble();
    return Units.rotationsToRadians(rps);
    // TODO: Implement this method
  }

  @Override
  public Rotation2d getPosition() {
    // TODO: Implement this method
    double angle = m_motor.getRotorPosition().getValueAsDouble();
    return Rotation2d.fromRotations(angle);
  }

  @Override
  public Object getMotor() {
    // DO NOT MODIFY THIS METHOD
    return m_motor;
  }
}
