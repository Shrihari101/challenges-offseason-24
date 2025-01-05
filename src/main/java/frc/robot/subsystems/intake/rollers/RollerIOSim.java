package frc.robot.subsystems.intake.rollers;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class RollerIOSim implements RollerIO {

  // For instructions on how to implement this class, refer to the README.md file
  private double m_voltage;
  private DCMotorSim m_sim;
  // define more members here as necessary

  public RollerIOSim() {
    DCMotor gearbox = DCMotor.getKrakenX60(1);
    double gearing = 1.0;
    double jkGMetersSquared = 0.00075;

    m_sim = new DCMotorSim(gearbox, gearing, jkGMetersSquared);

    m_voltage = 0.0;
    // TODO: Implement this constructor
  }

  @Override
  public void updateInputs(RollerInputs inputs) {
    m_sim.update(0.02);
    inputs.voltage = getVoltage();
    inputs.velocityRadPerSec = getVelocityRadPerSec();
  }

  @Override
  public void setVoltage(double voltage) {
    m_sim.setInputVoltage(voltage);
    m_voltage = voltage;
    // TODO: Implement this method
  }

  @Override
  public double getVoltage() {
    // TODO: Implement this method
    return m_voltage;
  }

  @Override
  public double getVelocityRadPerSec() {
    // TODO: Implement this method
    return m_sim.getAngularVelocityRadPerSec();
  }
}
