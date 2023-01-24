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
  private final DriveTrain m_subsystem;
  private final CommandXboxController m_CommandXboxController;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArcadeDrive(DriveTrain subsystem, CommandXboxController commandXboxController) {
    m_subsystem = subsystem;
    m_CommandXboxController = commandXboxController;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double drive = m_CommandXboxController.getRawAxis(XboxController.Axis.kLeftY.value);
    double turn = m_CommandXboxController.getRawAxis(XboxController.Axis.kLeftX.value);
   if (Math.abs(drive)> Constants.deadzone&&Math.abs(turn)> Constants.deadzone||Math.abs(drive)> Constants.deadzone&&Math.abs(turn)< Constants.deadzone||Math.abs(drive)< Constants.deadzone&&Math.abs(turn)> Constants.deadzone){
    m_subsystem.setLeftMotorSpeed(drive+ turn);
    m_subsystem.setRightMotorSpeed(drive- turn);
   } else { 
    m_subsystem.setLeftMotorSpeed(0);
    m_subsystem.setRightMotorSpeed(0);
   }
    Trigger setAngle = m_CommandXboxController.a();
    if(setAngle.getAsBoolean()){
      m_subsystem.setFrontLeftTurnMotor((180-m_subsystem.getFrontLeftTurnMotor())*0.01);
    }
    if(setAngle.getAsBoolean()){
      m_subsystem.setFrontRightTurnMotor((180-m_subsystem.getFrontRightTurnMotor())*0.01);
    }
    if(setAngle.getAsBoolean()){
      m_subsystem.setBackRightTurnMotor((180-m_subsystem.getBackRightTurnMotor())*0.01);
    }
    if(setAngle.getAsBoolean()){
      m_subsystem.setBackLeftTurnMotor((180-m_subsystem.getBackLeftTurnMotor())*0.01);
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