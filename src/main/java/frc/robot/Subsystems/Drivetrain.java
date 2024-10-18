package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Utilities.*;

public class Drivetrain {
    
    private TalonSRX _left1 = new TalonSRX(frc.robot.Utilities.Constants.kLeft1DriveMotor);
    private TalonSRX _left2 = new TalonSRX(frc.robot.Utilities.Constants.kLeft2DriveMotor);
    private TalonSRX _right1 = new TalonSRX(frc.robot.Utilities.Constants.kRight1DriveMotor);
    private TalonSRX _right2 = new TalonSRX(frc.robot.Utilities.Constants.kRight2DriveMotor);
    // private double VxCmd;
    private double VyCmd;
    private double WzCmd;
    private XboxController _driverController = new XboxController(Constants.kDriverControllerUsbSlot);
    private static Drivetrain _instance;
    

    public Drivetrain() {}

    public static Drivetrain getInstance() {
        if (_instance == null)
        {
            _instance = new Drivetrain();
        }

        return _instance;
    }

    public void init() {
        _left1.configAllSettings(Constants.defaultConfig);
        _left2.configAllSettings(Constants.defaultConfig);
        _right1.configAllSettings(Constants.defaultConfig);
        _right2.configAllSettings(Constants.defaultConfig);
        _left2.follow(_left1);
        _right2.follow(_right1);
    }

    public void periodic() {
        // VxCmd = -OneDimensionalLookup.interpLinear(Constants.XY_Axis_inputBreakpoints, Constants.XY_Axis_outputTable, _driverController.getLeftY());

        VyCmd = -OneDimensionalLookup.interpLinear(Constants.XY_Axis_inputBreakpoints, Constants.XY_Axis_outputTable, _driverController.getLeftX());

        WzCmd = -OneDimensionalLookup.interpLinear(Constants.RotAxis_inputBreakpoints, Constants.RotAxis_outputTable, _driverController.getRightX());

        drive();

        odometry();
    }

    public void drive() {
        if (WzCmd != 0 && VyCmd != 0) {
            if (WzCmd < 0) {
                _left1.set(ControlMode.PercentOutput, VyCmd * 1 + WzCmd);
                _right1.set(ControlMode.PercentOutput, -VyCmd);
            } else {
                _left1.set(ControlMode.PercentOutput, VyCmd);
                _right1.set(ControlMode.PercentOutput, -VyCmd * 1 - WzCmd);
            }
        } else if (VyCmd != 0) {
            _left1.set(ControlMode.PercentOutput, VyCmd);
            _right1.set(ControlMode.PercentOutput, -VyCmd);
        }  else if (WzCmd != 0) {
            _left1.set(ControlMode.PercentOutput, WzCmd);
            _right1.set(ControlMode.PercentOutput, -WzCmd);
        }
    }

    public void drive(double y, double z) {
        if (z != 0 && y != 0) {
            if (z < 0) {
                _left1.set(ControlMode.PercentOutput, y * 1 + z);
                _right1.set(ControlMode.PercentOutput, -y);
            } else {
                _left1.set(ControlMode.PercentOutput, y);
                _right1.set(ControlMode.PercentOutput, -y * 1 - z);
            }
        } else if (VyCmd != 0) {
            _left1.set(ControlMode.PercentOutput, y);
            _right1.set(ControlMode.PercentOutput, -y);
        }  else if (WzCmd != 0) {
            _left1.set(ControlMode.PercentOutput, z);
            _right1.set(ControlMode.PercentOutput, -z);
        }
    }

    public void odometry() {
        SmartDashboard.putNumber("foward percent", VyCmd);
        SmartDashboard.putNumber("turn percent", WzCmd);
        SmartDashboard.putNumber("left f/t percent", _left1.getMotorOutputPercent());
        SmartDashboard.putNumber("right f/t percent", _right1.getMotorOutputPercent());
    }
}
