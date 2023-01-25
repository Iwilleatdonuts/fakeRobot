// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class ArcadeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_arcadeDrive;
  private final CommandXboxController m_driverController;
  /**
   * Creates a new ExampleCommand.
   *
   * @param arcadeDrive The subsystem used by this command.
   */
  public ArcadeDrive(DriveTrain arcadeDrive, CommandXboxController commandXboxController) {
    m_arcadeDrive = arcadeDrive;
    m_driverController = commandXboxController;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arcadeDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double drive = m_driverController.getRawAxis(XboxController.Axis.kLeftY.value);
    double turn = m_driverController.getRawAxis(XboxController.Axis.kLeftX.value);
   if (Math.abs(drive)> Constants.deadzone&&Math.abs(turn)> Constants.deadzone||Math.abs(drive)> Constants.deadzone&&Math.abs(turn)< Constants.deadzone||Math.abs(drive)< Constants.deadzone&&Math.abs(turn)> Constants.deadzone){
    m_arcadeDrive.setLeftMotorSpeed(drive+ turn);
    m_arcadeDrive.setRightMotorSpeed(drive- turn);
   } else { 
    m_arcadeDrive.setLeftMotorSpeed(0);
    m_arcadeDrive.setRightMotorSpeed(0);
   }
    Trigger setAngle = m_driverController.a();
    if(setAngle.getAsBoolean()){
      m_arcadeDrive.setFrontLeftTurnMotor((180-m_arcadeDrive.getFrontLeftTurnMotor())*0.01);
      m_arcadeDrive.setFrontRightTurnMotor((180-m_arcadeDrive.getFrontRightTurnMotor())*0.01);
      m_arcadeDrive.setBackRightTurnMotor((180-m_arcadeDrive.getBackRightTurnMotor())*0.01);
      m_arcadeDrive.setBackLeftTurnMotor((180-m_arcadeDrive.getBackLeftTurnMotor())*0.01);
    }
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