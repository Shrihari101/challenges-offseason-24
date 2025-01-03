package frc.robot.subsystems.wrist;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {
  // For instructions on how to implement this class, refer to the README.md file
  WristIO m_io;
  PIDController m_controller;
  WristInputsAutoLogged m_inputs;
  Rotation2d m_desiredAngle;
  static final double TOLERANCE_DEGREES = 3.0;

  public Wrist(WristIO io, PIDController controller) {
    this.m_io = io;
    this.m_controller = controller;
    this.m_inputs = new WristInputsAutoLogged();
    this.m_desiredAngle = Rotation2d.fromDegrees(0);
  }

  @Override
  public void periodic() {
    m_io.updateInputs(m_inputs);
    double currentAngle = m_io.getAngle().getDegrees();
    double pidOutput = m_controller.calculate(currentAngle, m_desiredAngle.getDegrees());
    m_io.setVoltage(pidOutput);
  }

  public void setDesiredAngle(Rotation2d angle) {
    this.m_desiredAngle = angle;
  }

  public Command setDesiredAngleCommand(Rotation2d angle) {
    return new InstantCommand(() -> setDesiredAngle(angle));
  }

  public boolean withinTolerance() {
    return Math.abs(m_io.getAngle().getDegrees() - m_desiredAngle.getDegrees())
        <= TOLERANCE_DEGREES;
  }

  public WristInputsAutoLogged getInputs() {
    return m_inputs;
  }
}
