// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
public class ExampleSubsystem extends SubsystemBase {
  
//** Creates a new ExampleSubsystem. */
private final TalonFX m_nWTurnMotor;
private final TalonFX m_nETurnMotor;
private final TalonFX m_sWTurnMotor;
private final TalonFX m_sETurnMotor;
private final TalonFX m_nWMoveMotor;
private final TalonFX m_nEMoveMotor;
private final TalonFX m_sWMoveMotor;
private final TalonFX m_sEMoveMotor;
CANCoder nECANCoder = new CANCoder(Constants.nECANCoderID);

  private boolean m_isBrakeMode = false;

  public ExampleSubsystem() {
    m_nWTurnMotor = new TalonFX(Constants.nWTurnMotorID);
    m_nETurnMotor = new TalonFX(Constants.nETurnMotorID);
    m_sWTurnMotor = new TalonFX(Constants.sWTurnMotorID);
    m_sETurnMotor = new TalonFX(Constants.sETurnMotorID);
    m_nWMoveMotor = new TalonFX(Constants.nWMoveMotorID);
    m_nEMoveMotor = new TalonFX(Constants.nEMoveMotorID);
    m_sWMoveMotor = new TalonFX(Constants.sWMoveMotorID);
    m_sEMoveMotor = new TalonFX(Constants.sEMoveMotorID);
  }

  public void setWMotorSpeed(double wMotorSpeed){
      m_nWMoveMotor.set(ControlMode.PercentOutput, wMotorSpeed);
      m_sWMoveMotor.set(ControlMode.PercentOutput, wMotorSpeed);
  }
  public void setEMotorSpeed(double eMotorSpeed){
      m_nEMoveMotor.set(ControlMode.PercentOutput, eMotorSpeed);
      m_sEMoveMotor.set(ControlMode.PercentOutput, eMotorSpeed);
  }
  public void setBrakeMode(boolean isBrakeMode){
    if(isBrakeMode){
      m_isBrakeMode = true;
      m_nWTurnMotor.setNeutralMode(NeutralMode.Brake);
      m_nETurnMotor.setNeutralMode(NeutralMode.Brake);
      m_sWTurnMotor.setNeutralMode(NeutralMode.Brake);
      m_sETurnMotor.setNeutralMode(NeutralMode.Brake);
    } else {
      m_isBrakeMode = false;
      m_nWTurnMotor.setNeutralMode(NeutralMode.Coast);
      m_nETurnMotor.setNeutralMode(NeutralMode.Coast);
      m_sWTurnMotor.setNeutralMode(NeutralMode.Coast);
      m_sETurnMotor.setNeutralMode(NeutralMode.Coast);
    }
  }
  public double getNEAngle(){
      return nECANCoder.getAbsolutePosition();
  }
  public void setNEAngle(Trigger setAngle){
    if(setAngle.getAsBoolean()){
      if(getNEAngle()!=180){
        m_nEMoveMotor.set(ControlMode.PercentOutput, 0.5);
      }
    }
  }

  public boolean isBrakeMode(){
    return m_isBrakeMode;
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
