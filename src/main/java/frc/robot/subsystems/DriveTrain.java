// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenixpro.sim.CANcoderSimState;

public class DriveTrain extends SubsystemBase {
  
//** Creates a new ExampleSubsystem. */
private final TalonFX m_frontLeftTurnMotor;
private final TalonFX m_frontRightTurnMotor;
private final TalonFX m_backLeftTurnMotor;
private final TalonFX m_backRightTurnMotor;
private final TalonFX m_frontLeftMoveMotor;
private final TalonFX m_frontRightMoveMotor;
private final TalonFX m_backLeftMoveMotor;
private final TalonFX m_backRightMoveMotor;
private final CANCoder m_frontLeftCANCoder;
private final CANCoder m_frontRightCANCoder;
private final CANCoder m_backLeftCANCoder;
private final CANCoder m_backRightCANCoder;
PIDController angleController = new PIDController(Constants.angleMotorKP, Constants.angleMotorKI, Constants.angleMotorKD);

  private boolean m_isBrakeMode = false;

  public DriveTrain() {
    m_frontLeftTurnMotor = new TalonFX(Constants.FrontLeftModule.TurnMotorID);
    m_frontRightTurnMotor = new TalonFX(Constants.FrontRightModule.TurnMotorID);
    m_backLeftTurnMotor = new TalonFX(Constants.BackLeftModule.TurnMotorID);
    m_backRightTurnMotor = new TalonFX(Constants.BackRightModule.TurnMotorID);
    m_frontLeftMoveMotor = new TalonFX(Constants.FrontLeftModule.MoveMotorID);
    m_frontRightMoveMotor = new TalonFX(Constants.FrontRightModule.MoveMotorID);
    m_backLeftMoveMotor = new TalonFX(Constants.BackLeftModule.MoveMotorID);
    m_backRightMoveMotor = new TalonFX(Constants.BackRightModule.MoveMotorID);
    m_frontLeftCANCoder = new CANCoder(Constants.FrontLeftModule.CANCoderID);
    m_frontRightCANCoder = new CANCoder(Constants.FrontRightModule.CANCoderID);
    m_backLeftCANCoder = new CANCoder(Constants.BackLeftModule.CANCoderID);
    m_backRightCANCoder = new CANCoder(Constants.BackRightModule.CANCoderID);
  }

  public void setLeftTreadSpeed(double leftTreadSpeed){
      m_frontLeftMoveMotor.set(ControlMode.PercentOutput, leftTreadSpeed);
      m_backLeftMoveMotor.set(ControlMode.PercentOutput, leftTreadSpeed);
  }
  public void setRightTreadSpeed(double rightTreadSpeed){
      m_frontRightMoveMotor.set(ControlMode.PercentOutput, rightTreadSpeed);
      m_backRightMoveMotor.set(ControlMode.PercentOutput, rightTreadSpeed);
  }
  public void setBrakeMode(boolean mode){
    if(mode){
      m_isBrakeMode = true;
      m_frontLeftTurnMotor.setNeutralMode(NeutralMode.Brake);
      m_frontRightTurnMotor.setNeutralMode(NeutralMode.Brake);
      m_backLeftTurnMotor.setNeutralMode(NeutralMode.Brake);
      m_backRightTurnMotor.setNeutralMode(NeutralMode.Brake);
    } else {
      m_isBrakeMode = false;
      m_frontLeftTurnMotor.setNeutralMode(NeutralMode.Coast);
      m_frontRightTurnMotor.setNeutralMode(NeutralMode.Coast);
      m_backLeftTurnMotor.setNeutralMode(NeutralMode.Coast);
      m_backRightTurnMotor.setNeutralMode(NeutralMode.Coast);
    }
  }

  public void setTurnMotors(){
      m_frontLeftTurnMotor.set(ControlMode.PercentOutput, angleController.calculate(m_frontLeftCANCoder.getAbsolutePosition(), Constants.FrontLeftModule.DefaultAngle));
      m_frontRightTurnMotor.set(ControlMode.PercentOutput, angleController.calculate(m_frontRightCANCoder.getAbsolutePosition(), Constants.FrontRightModule.DefaultAngle));
      m_backLeftTurnMotor.set(ControlMode.PercentOutput, angleController.calculate(m_backLeftCANCoder.getAbsolutePosition(), Constants.BackLeftModule.DefaultAngle));
      m_backRightTurnMotor.set(ControlMode.PercentOutput, angleController.calculate(m_backRightCANCoder.getAbsolutePosition(), Constants.BackRightModule.DefaultAngle));
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
