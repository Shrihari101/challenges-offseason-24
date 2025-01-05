// Copyright 2021-2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.oi.DriverControls;
import frc.robot.oi.DriverControlsXbox;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.FlywheelIO;
import frc.robot.subsystems.FlywheelIONeo;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
@SuppressWarnings("unused")
public class RobotContainer {
  // For instructions on how to implement this class, refer to the README.md file
  private Flywheel m_flywheel;
  private DriverControls m_driverControls;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureSubsystems();
    configureControllers();
    configureButtonBindings();
  }

  private void configureSubsystems() {
    FlywheelIO flywheelIO = new FlywheelIONeo(2);
    PIDController pidController =
        new PIDController(FlywheelConstants.kP, FlywheelConstants.kI, FlywheelConstants.kD);
    m_flywheel = new Flywheel(flywheelIO, pidController);
    // TODO: Implement this method
  }

  private void configureControllers() {
    m_driverControls = new DriverControlsXbox(1);
  }

  private void configureButtonBindings() {
    Trigger runFlywheelTrigger = m_driverControls.runFlywheel();
    runFlywheelTrigger.onTrue(
        m_flywheel.setDesiredVelocityCommand(FlywheelConstants.kVelocitySetpoint));
    runFlywheelTrigger.onFalse(m_flywheel.setDesiredVelocityCommand(0.0));
    // TODO: Implement this method
  }
}
