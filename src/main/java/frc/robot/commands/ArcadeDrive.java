// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/** An example command that uses an example subsystem. */
public class ArcadeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_drive;
  private final CommandXboxController m_driverController;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArcadeDrive(DriveTrain subsystem, CommandXboxController commandXboxController) {
    m_drive = subsystem;
    m_driverController = commandXboxController;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double move = m_driverController.getRawAxis(XboxController.Axis.kLeftY.value);
    double turn = m_driverController.getRawAxis(XboxController.Axis.kLeftX.value);
    m_drive.setRightTreadSpeed(move+ turn);
    m_drive.setLeftTreadSpeed(move- turn);
    //Trigger mode = m_CommandXboxController.x();
    //m_subsystem.setBrakeMode(mode);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}