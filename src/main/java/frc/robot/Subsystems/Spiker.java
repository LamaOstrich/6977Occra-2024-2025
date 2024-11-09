package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Enums.SpikerState;
import frc.robot.Utilities.Constants;

public class Spiker {

    private VictorSPX _topMotor;
    private VictorSPX _lowMotor;
    private SpikerState currentState = SpikerState.IDLE;
    private static Spiker _instance = getInstance();
    private Intake _intake = Intake.getInstance();

    public Spiker() {
        _topMotor = new VictorSPX(Constants.kSpikerTopMotor);
        _lowMotor = new VictorSPX(Constants.kSpikerLowMotor);
        _topMotor.configAllSettings(Constants.defaultSPXConfig);
        _lowMotor.configAllSettings(Constants.defaultSPXConfig);
    }

    public static Spiker getInstance() {
        if (_instance == null) {
            _instance = new Spiker();
        }
        return _instance;
    }

    public void init() {
        _topMotor.configAllSettings(Constants.defaultSPXConfig);
        _lowMotor.configAllSettings(Constants.defaultSPXConfig);
        _lowMotor.setInverted(false);
        setWantedState(SpikerState.IDLE);
    }

    private void idle() {
        if (!_intake.isHolding()) {
            _topMotor.set(ControlMode.PercentOutput, 0);
            _lowMotor.set(ControlMode.PercentOutput, 0);
        } else {
            setWantedState(SpikerState.STANDBY);
        }
    }

    private void standby() {
        if (_intake.isHolding()) {
            _topMotor.set(ControlMode.PercentOutput, 0.3);
            _lowMotor.set(ControlMode.PercentOutput, 0.3);
        } else {
            setWantedState(SpikerState.IDLE);
        }
    }

    private void farSpike() {
        _topMotor.set(ControlMode.PercentOutput, .8);
        _lowMotor.set(ControlMode.PercentOutput, 1);
    }

    private void closeSpike() {
        _topMotor.set(ControlMode.PercentOutput, 0.7);
        _lowMotor.set(ControlMode.PercentOutput, .9);
    }

    private void intakebad() {
        _topMotor.set(ControlMode.PercentOutput, -.4);
        _lowMotor.set(ControlMode.PercentOutput, -.4);
    }

    private void feed() {
        _topMotor.set(ControlMode.PercentOutput, .6);
        _lowMotor.set(ControlMode.PercentOutput, -.6);
    }

    public void setWantedState(SpikerState state) {
        if (currentState != state) {
            currentState = state;
        } 
    }

    public void handleState() {
        SmartDashboard.putString("spiker state", currentState.name());
        switch(currentState) {
            case IDLE:
                idle();
                break;
            case STANDBY:
                standby();
                break;
            case SPIKE_FAR:
                farSpike();
                break;
            case SPIKE_CLOSE:
                closeSpike();
                break;
            case INTAKE_BAD:
                intakebad();
                break;
            case FEED:
                feed();
            default:
                idle();
                break;
        }
    }
}
