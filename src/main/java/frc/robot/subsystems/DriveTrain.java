// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
public class DriveTrain extends SubsystemBase {
  
//** Creates a new ExampleSubsystem. */
private final TalonFX m_frontRightTurnMotor;
private final TalonFX m_frontLeftTurnMotor;
private final TalonFX m_backLeftTurnMotor;
private final TalonFX m_backRightTurnMotor;
private final TalonFX m_frontRightDriveMotor;
private final TalonFX m_frontLeftDriveMotor;
private final TalonFX m_backLeftDriveMotor;
private final TalonFX m_backRightDriveMotor;
CANCoder frontRightCANCoder = new CANCoder(Constants.FrontRight.CANCoderID);
CANCoder frontLeftCANCoer = new CANCoder(Constants.FrontLeft.CANCoderID);
CANCoder backRightCANCoder = new CANCoder(Constants.BackRight.CANCoderID);
CANCoder backLeftCANCoder = new CANCoder(Constants.BackLeft.CANCoderID);

  private boolean m_isBrakeMode = false;

  public DriveTrain() {
    m_frontRightTurnMotor = new TalonFX(Constants.FrontRight.TurnMotorID);
    m_frontLeftTurnMotor = new TalonFX(Constants.FrontLeft.TurnMotorID);
    m_backLeftTurnMotor = new TalonFX(Constants.BackLeft.TurnMotorID);
    m_backRightTurnMotor = new TalonFX(Constants.BackRight.TurnMotorID);
    m_frontRightDriveMotor = new TalonFX(Constants.FrontRight.DriveMotorID);
    m_frontLeftDriveMotor = new TalonFX(Constants.FrontLeft.DriveMotorID);
    m_backLeftDriveMotor = new TalonFX(Constants.BackLeft.DriveMotorID);
    m_backRightDriveMotor = new TalonFX(Constants.BackRight.DriveMotorID);
  }

  public void setRightMotorSpeed(double rightMotorSpeed){
      m_frontRightDriveMotor.set(ControlMode.PercentOutput, rightMotorSpeed);
      m_backRightDriveMotor.set(ControlMode.PercentOutput, rightMotorSpeed);
  }
  public void setLeftMotorSpeed(double leftMotorSpeed){
      m_frontLeftDriveMotor.set(ControlMode.PercentOutput, leftMotorSpeed);
      m_backLeftDriveMotor.set(ControlMode.PercentOutput, leftMotorSpeed);
  }
  public void setBrakeMode(boolean isBrakeMode){
    if(isBrakeMode){
      m_isBrakeMode = true;
      m_frontRightTurnMotor.setNeutralMode(NeutralMode.Brake);
      m_frontLeftTurnMotor.setNeutralMode(NeutralMode.Brake);
      m_backLeftTurnMotor.setNeutralMode(NeutralMode.Brake);
      m_backRightTurnMotor.setNeutralMode(NeutralMode.Brake);
    } else {
      m_isBrakeMode = false;
      m_frontRightTurnMotor.setNeutralMode(NeutralMode.Coast);
      m_frontLeftTurnMotor.setNeutralMode(NeutralMode.Coast);
      m_backLeftTurnMotor.setNeutralMode(NeutralMode.Coast);
      m_backRightTurnMotor.setNeutralMode(NeutralMode.Coast);
    }
    
  }
  public double getFrontRightTurnMotor(){
      return frontRightCANCoder.getAbsolutePosition();
    }
  public double getFrontLeftTurnMotor(){
      return frontLeftCANCoer.getAbsolutePosition();
    }
  public double getBackRightTurnMotor(){
      return backRightCANCoder.getAbsolutePosition();
    }
  public double getBackLeftTurnMotor(){
      return backLeftCANCoder.getAbsolutePosition();
    }
  public void setFrontLeftTurnMotor(double frontLeftTurnMotorSpeed){
    m_frontLeftTurnMotor.set(ControlMode.PercentOutput, frontLeftTurnMotorSpeed);
  }
  public void setFrontRightTurnMotor(double frontRightTurnMotorSpeed){
    m_frontRightTurnMotor.set(ControlMode.PercentOutput, frontRightTurnMotorSpeed);
  }
  public void setBackRightTurnMotor(double backRightTurnMotorSpeed){
    m_backRightTurnMotor.set(ControlMode.PercentOutput, backRightTurnMotorSpeed);
  }
  public void setBackLeftTurnMotor(double backLeftTurnMotorSpeed){
    m_backLeftTurnMotor.set(ControlMode.PercentOutput, backLeftTurnMotorSpeed);
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
     //This method will be called once per scheduler run
    SmartDashboard.putNumber("Front Right Turn Motor", getFrontRightTurnMotor());
    SmartDashboard.putNumber("Front Left Turn Motor", getFrontLeftTurnMotor());
    SmartDashboard.putNumber("Back Right Turn Motor", getBackRightTurnMotor());
    SmartDashboard.putNumber("Back Left Turn Motor", getBackLeftTurnMotor());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
