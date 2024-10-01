package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
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
    }

    public void periodic() {
        // VxCmd = -OneDimensionalLookup.interpLinear(Constants.XY_Axis_inputBreakpoints, Constants.XY_Axis_outputTable, _driverController.getLeftY());

        VyCmd = -OneDimensionalLookup.interpLinear(Constants.XY_Axis_inputBreakpoints, Constants.XY_Axis_outputTable, _driverController.getLeftX());

        WzCmd = -OneDimensionalLookup.interpLinear(Constants.RotAxis_inputBreakpoints, Constants.RotAxis_outputTable, _driverController.getRightX());

        if (VyCmd != 0) {
            _left1.set(ControlMode.PercentOutput, VyCmd);
            _left2.set(ControlMode.PercentOutput, VyCmd);
            _right1.set(ControlMode.PercentOutput, VyCmd);
            _right2.set(ControlMode.PercentOutput, VyCmd);
        } else if (WzCmd != 0) {
            _left1.set(ControlMode.PercentOutput, WzCmd);
            _left2.set(ControlMode.PercentOutput, WzCmd);
            _right1.set(ControlMode.PercentOutput, -WzCmd);
            _right2.set(ControlMode.PercentOutput, -WzCmd);
        }
    }
}