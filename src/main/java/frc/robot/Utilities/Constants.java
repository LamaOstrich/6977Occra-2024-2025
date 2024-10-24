package frc.robot.Utilities;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

public class Constants {
    
    public static final int kDriverControllerUsbSlot = 0;
    public static final int kOperatorControllerUsbSlot = 1;
    public static final int kLeft1DriveMotor = 2;
    public static final int kLeft2DriveMotor = 3;
    public static final int kRight1DriveMotor = 4;
    public static final int kRight2DriveMotor = 5;
    public static final int kIntakeMotor = 6;
    public static final int kSpikerTopMotor = 7;
    public static final int kSpikerLowMotor = 8;

    public static final int kPhotoEyeChannel = 1;

    public static TalonSRXConfiguration defaultConfig = new TalonSRXConfiguration();
    
    public static final double kDeadband = 0.15;
    public static final double[] XY_Axis_inputBreakpoints =  {-1,  -0.9, -0.85, -0.7, -0.6, -0.5, -0.2,  -0.12, 0.12, 0.2,  0.5, 0.6, 0.7, 0.85, .9, 1};
    public static final double[] XY_Axis_outputTable = {-1.0, -.7, -0.6, -0.4, -0.3, -0.2, -0.05, 0, 0, 0.05,  0.2, 0.3, 0.4,  0.6, .7, 1.0};
    public static final double[] RotAxis_inputBreakpoints =  {-1, -.9, -0.85, -0.7, -0.6, -0.5, -0.2,  -0.12, 0.12, 0.2,  0.5, 0.6, 0.7, 0.85, .9, 1};
    public static final double[] RotAxis_outputTable = {-.8, -.7, -0.6, -0.4, -0.3, -0.2, -0.05, 0, 0, 0.05,  0.2, 0.3, 0.4,  0.6, .7, .8};
}
