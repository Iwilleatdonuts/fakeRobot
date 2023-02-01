// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class FrontRightModule {
      public static final int TurnMotorID = 1;
      public static final int MoveMotorID = 2;
      public static final int CANCoderID = 9;
      public static final double DefaultAngle = 180;
  }
  public static class FrontLeftModule {
      public static final int TurnMotorID = 3;
      public static final int MoveMotorID = 4;
      public static final int CANCoderID = 10;
      public static final double DefaultAngle = 180;
  }
  public static class BackLeftModule {
      public static final int TurnMotorID = 5;
      public static final int MoveMotorID = 6;
      public static final int CANCoderID = 12;
      public static final double DefaultAngle = 180;
  }
  public static class BackRightModule {
      public static final int TurnMotorID = 7;
      public static final int MoveMotorID = 8;
      public static final int CANCoderID = 11;
      public static final double DefaultAngle = 180;
  } 
    
  public static final double angleMotorKP = 0.1;
  public static final double angleMotorKI = 0.01;
  public static final double angleMotorKD = 0.001;
    
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
