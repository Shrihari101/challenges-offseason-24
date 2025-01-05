package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class PivotIOSim implements PivotIO {
  // For instructions on how to implement this class, refer to the README.md file
  private double m_voltage;
  private SingleJointedArmSim m_sim;
  // define more members here as necessary

  public PivotIOSim() {
    // TODO: Implement this constructor
    DCMotor gearbox = DCMotor.getNEO(1);
    double gearing = 1.0;
    double armLength = 0.21;
    double jKgMetersSquared = SingleJointedArmSim.estimateMOI(armLength, 4.0);
    double minAngle = Units.degreesToRadians(5);
    double maxAngle = Units.degreesToRadians(125);
    double startingAngle = Units.degreesToRadians(120);

    m_sim =
        new SingleJointedArmSim(
            gearbox,
            gearing,
            jKgMetersSquared,
            armLength,
            minAngle,
            maxAngle,
            false,
            startingAngle);

    m_voltage = 0.0;
  }

  @Override
  public void updateInputs(PivotInputs inputs) {
    m_sim.update(0.02);

    inputs.voltage = getVoltage();
    inputs.velocityRadPerSec = getVelocityRadPerSec();
    inputs.angleRad = getAngle().getRadians();
  }

  @Override
  public void setVoltage(double voltage) {
    // TODO: Implement this method
    m_sim.setInputVoltage(voltage);
    m_voltage = voltage;
  }

  @Override
  public double getVoltage() {
    // TODO: Implement this method
    return m_voltage;
  }

  @Override
  public double getVelocityRadPerSec() {
    // TODO: Implement this method
    return m_sim.getVelocityRadPerSec();
  }

  @Override
  public Rotation2d getAngle() {
    // TODO: Implement this method
    return Rotation2d.fromRadians(m_sim.getAngleRads());
  }
}
