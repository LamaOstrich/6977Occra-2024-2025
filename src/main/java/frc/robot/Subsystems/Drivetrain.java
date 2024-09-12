package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Utilities.*;;

public class Drivetrain {
    
    private TalonSRX _frontLeft = new TalonSRX(frc.robot.Utilities.Constants.kFrontLeftDriveMotor);
    private TalonSRX _frontRight = new TalonSRX(frc.robot.Utilities.Constants.kFrontRightDriveMotor);
    private TalonSRX _backLeft = new TalonSRX(frc.robot.Utilities.Constants.kBackLeftDriveMotor);
    private TalonSRX _backRight = new TalonSRX(frc.robot.Utilities.Constants.kBackRightDriveMotor);
    private double VxCmd;
    private double VyCmd;
    private double WzCmd;
    private XboxController _driverController = new XboxController(Constants.kDriverControllerUsbSlot);
    

    public Drivetrain() {}

    public void init() {
        _frontLeft.configAllSettings(Constants.defaultConfig);
        _frontRight.configAllSettings(Constants.defaultConfig);
        _backLeft.configAllSettings(Constants.defaultConfig);
        _backRight.configAllSettings(Constants.defaultConfig);
    }

    public void Drive(){
        if (VyCmd != 0) {
            _frontLeft.set(ControlMode.PercentOutput, VyCmd);
            _frontRight.set(ControlMode.PercentOutput, VyCmd);
            _backLeft.set(ControlMode.PercentOutput, VyCmd);
            _backRight.set(ControlMode.PercentOutput, VyCmd);
        } else if (WzCmd != 0) {
            _frontLeft.set(ControlMode.PercentOutput, WzCmd);
            _frontRight.set(ControlMode.PercentOutput, -WzCmd);
            _backLeft.set(ControlMode.PercentOutput, WzCmd);
            _backRight.set(ControlMode.PercentOutput, -WzCmd);
        } else {
            _frontLeft.set(ControlMode.PercentOutput, 0.0);
            _frontRight.set(ControlMode.PercentOutput, 0.0);
            _backLeft.set(ControlMode.PercentOutput, 0.0);
            _backRight.set(ControlMode.PercentOutput, 0.0);
        }
    }

    public void periodic() {
        // VxCmd = -OneDimensionalLookup.interpLinear(Constants.XY_Axis_inputBreakpoints, Constants.XY_Axis_outputTable, _driverController.getLeftY());

        VyCmd = -OneDimensionalLookup.interpLinear(Constants.XY_Axis_inputBreakpoints, Constants.XY_Axis_outputTable, _driverController.getLeftX());

        WzCmd = -OneDimensionalLookup.interpLinear(Constants.RotAxis_inputBreakpoints, Constants.RotAxis_outputTable, _driverController.getRightX());
    }
}
