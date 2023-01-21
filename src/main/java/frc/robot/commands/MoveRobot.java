// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class MoveRobot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ExampleSubsystem m_subsystem;
  private final CommandXboxController m_CommandXboxController;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public MoveRobot(ExampleSubsystem subsystem, CommandXboxController commandXboxController) {
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
    double move = m_CommandXboxController.getRawAxis(XboxController.Axis.kLeftY.value);
    double turn = m_CommandXboxController.getRawAxis(XboxController.Axis.kLeftX.value);
   if (Math.abs(move)> Constants.deadzone&&Math.abs(turn)> Constants.deadzone||Math.abs(move)> Constants.deadzone&&Math.abs(turn)< Constants.deadzone||Math.abs(move)< Constants.deadzone&&Math.abs(turn)> Constants.deadzone){
    m_subsystem.setEMotorSpeed(move+ turn);
    m_subsystem.setWMotorSpeed(move- turn);
   } else {
    m_subsystem.setEMotorSpeed(0);
    m_subsystem.setWMotorSpeed(0);
   }
    Trigger setAngle = m_CommandXboxController.a();
    if(setAngle.getAsBoolean()&&(m_subsystem.getnETurnMotor()>190||m_subsystem.getnETurnMotor()<170)){
      m_subsystem.setnETurnMotor(0.1);
    } else {
      m_subsystem.setnETurnMotor(0);
    }
    if(setAngle.getAsBoolean()&&(m_subsystem.getnWTurnMotor()>190||m_subsystem.getnWTurnMotor()<170)){
      m_subsystem.setnWTurnMotor(0.1);
    } else {
      m_subsystem.setnWTurnMotor(0);
    }
    if(setAngle.getAsBoolean()&&(m_subsystem.getsETurnMotor()>190||m_subsystem.getsETurnMotor()<170)){
      m_subsystem.setsWTurnMotor(0.1);
    } else {
      m_subsystem.setsWTurnMotor(0);
    }
    if(setAngle.getAsBoolean()&&(m_subsystem.getsWTurnMotor()>190||m_subsystem.getsWTurnMotor()<170)){
      m_subsystem.setsETurnMotor(0.1);
    } else {
      m_subsystem.setsETurnMotor(0);
    }

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