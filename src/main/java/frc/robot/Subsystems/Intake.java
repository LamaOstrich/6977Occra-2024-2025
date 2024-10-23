package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Enums.IntakeState;
import frc.robot.Utilities.Constants;

public class Intake {
    private final double kPhotoEyeDebounceTime = 0.04;
    private VictorSPX _motor;
    private DigitalInput _photoEye = new DigitalInput(Constants.kPhotoEyeChannel);
    private Debouncer debouncer = new Debouncer(kPhotoEyeDebounceTime, DebounceType.kRising);
    // private IntakeState previousState = IntakeState.IDLE;
    private IntakeState currentState = IntakeState.IDLE;
    private static Intake _instance;

    public Intake() {
    _motor = new VictorSPX(6);
    // _motor.configAllSettings(Constants.defaultConfig);
    _motor.configAllSettings(Constants.defaultSPXConfig);
    }

    public void init() {
        _motor.configAllSettings(Constants.defaultSPXConfig);
    }

    public static Intake getInstance() {
        if (_instance == null) {
            _instance = new Intake();
        }
        return _instance;
    }

    private void idle() {
        if (isHolding()) {
            _motor.set(ControlMode.PercentOutput, 0.0);
        } else {
            setWantedState(IntakeState.INTAKE);
        }
    }

    private void intake() {
        if(!isHolding()) {
            _motor.set(ControlMode.PercentOutput, .5);
        } else {
            setWantedState(IntakeState.IDLE);
        }
    }

    private void eject() {
        _motor.set(ControlMode.PercentOutput, -.5);
    }

    private void feed() {
        _motor.set(ControlMode.PercentOutput, 0.5);
    }

    public void setWantedState(IntakeState state) {
        if (currentState != state) {
            currentState = state;
        }
    }

    public void handleState() {
        switch(currentState) {
            case IDLE: 
                idle();
                break;
            case INTAKE:
                intake();
                break;
            case EJECT:
                eject();
                break;
            case FEED:
                feed();
                break;
            default:
                idle();
                break;
        }
    }

    public Boolean isHolding() {
        return debouncer.calculate(_photoEye.get());
    }
}
