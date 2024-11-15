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
    private double LVyCmd;
    private double RVyCmd;
    // private double WzCmd;
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

        LVyCmd = -OneDimensionalLookup.interpLinear(Constants.XY_Axis_inputBreakpoints, Constants.XY_Axis_outputTable, _driverController.getLeftY());

        RVyCmd = -OneDimensionalLookup.interpLinear(Constants.XY_Axis_inputBreakpoints, Constants.XY_Axis_outputTable, _driverController.getRightY());

        // WzCmd = OneDimensionalLookup.interpLinear(Constants.RotAxis_inputBreakpoints, Constants.RotAxis_outputTable, _driverController.getRightY());

        drive();

        odometry();
    }

    public void drive() {
        _left1.set(ControlMode.PercentOutput, LVyCmd);
        _right1.set(ControlMode.PercentOutput, -RVyCmd);
        // if (LVyCmd != 0) {
        //     if (WzCmd == 0) {
        //         _left1.set(ControlMode.PercentOutput, LVyCmd);
        //         _right1.set(ControlMode.PercentOutput, -LVyCmd);
        //     } else if (WzCmd < 0) {
        //         _left1.set(ControlMode.PercentOutput, LVyCmd);
        //         _right1.set(ControlMode.PercentOutput, -LVyCmd * (1 + WzCmd));
        //     } else if (WzCmd > 0) {
        //         _left1.set(ControlMode.PercentOutput, LVyCmd * (1 - WzCmd));
        //         _right1.set(ControlMode.PercentOutput, -LVyCmd);
        //     } 
        // } else {
        //     _left1.set(ControlMode.PercentOutput, -WzCmd);
        //     _right1.set(ControlMode.PercentOutput, -WzCmd);
        // }
    }

    public void drive(double y, double z) {
        if (y != 0) {
            _left1.set(ControlMode.PercentOutput, y);
            _right1.set(ControlMode.PercentOutput, -y);
        }  else if (z != 0) {
            _left1.set(ControlMode.PercentOutput, z);
            _right1.set(ControlMode.PercentOutput, -z);
        } else {
            _left1.set(ControlMode.PercentOutput, 0);
            _right1.set(ControlMode.PercentOutput, 0);
        } 
    }

    public void odometry() {
        SmartDashboard.putNumber("L percent", LVyCmd);
        SmartDashboard.putNumber("R percent", RVyCmd);
        SmartDashboard.putNumber("left f/t percent", _left1.getMotorOutputPercent());
        SmartDashboard.putNumber("right f/t percent", _right1.getMotorOutputPercent());
    }
}
