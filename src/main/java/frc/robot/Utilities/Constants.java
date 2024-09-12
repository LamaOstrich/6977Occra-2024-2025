package frc.robot.Utilities;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

public class Constants {
    
    public static final int kDriverControllerUsbSlot = 0;
    public static final int kOperatorControllerUsbSlot = 1;
    public static final int kFrontLeftDriveMotor = 2;
    public static final int kFrontRightDriveMotor = 3;
    public static final int kBackLeftDriveMotor = 4;
    public static final int kBackRightDriveMotor = 5;

    public static final int kPigeonId = 13;

    public static TalonSRXConfiguration defaultConfig = new TalonSRXConfiguration();
    
    public static final double kDeadband = 0.15;
    public static final double[] XY_Axis_inputBreakpoints =  {-1,  -0.9, -0.85, -0.7, -0.6, -0.5, -0.2,  -0.12, 0.12, 0.2,  0.5, 0.6, 0.7, 0.85, .9, 1};
    public static final double[] XY_Axis_outputTable = {-1.0, -.7, -0.6, -0.4, -0.3, -0.2, -0.05, 0, 0, 0.05,  0.2, 0.3, 0.4,  0.6, .7, 1.0};
    public static final double[] RotAxis_inputBreakpoints =  {-1, -.9, -0.85, -0.7, -0.6, -0.5, -0.2,  -0.12, 0.12, 0.2,  0.5, 0.6, 0.7, 0.85, .9, 1};
    public static final double[] RotAxis_outputTable = {-1.0, -.7, -0.6, -0.4, -0.3, -0.2, -0.05, 0, 0, 0.05,  0.2, 0.3, 0.4,  0.6, .7, 1.0};
}
