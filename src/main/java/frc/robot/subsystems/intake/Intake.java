package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private IntakeIO m_io;
  private IntakeInputsAutoLogged m_inputs;
  // For instructions on how to implement this class, refer to the README.md file

  public Intake(IntakeIO io) {
    // TODO: Implement the constructor
    m_io = io;
    m_inputs = new IntakeInputsAutoLogged();
  }

  @Override
  public void periodic() {
    // TODO: Implement this method
    m_io.updateInputs(m_inputs);
  }

  public void setRollerVoltage(double voltage) {
    // TODO: Implement this method
    m_io.setRollerVoltage(voltage);
  }

  public Command setVoltageCommand(double voltage) {
    // TODO: Implement this method
    return Commands.runOnce(() -> setRollerVoltage(voltage));
  }

  public IntakeInputsAutoLogged getInputs() {
    // TODO: Implement this method
    return m_inputs;
  }
}
