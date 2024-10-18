package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Enums.IntakeState;
import frc.robot.Enums.SpikerState;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Spiker;

public class Autos {
    private Drivetrain _drivetrain;
    private Intake _intake;
    private Spiker _spiker;
    private static Autos _instance;
    public static Timer timer = new Timer();

   public Autos() {
    _drivetrain = Drivetrain.getInstance();
    _intake = Intake.getInstance();
    _spiker = Spiker.getInstance();
    _drivetrain.init();
    _intake.init();
    _spiker.init();
   }


   public static Autos getInstance() {
    if (_instance == null) {
        _instance = new Autos();
    }
    return _instance;
    }

   public void Test() {
    _spiker.setWantedState(SpikerState.SPIKE_CLOSE);
    Timer.delay(.2);
    _intake.setWantedState(IntakeState.FEED);
    Timer.delay(.1);
    _spiker.setWantedState(SpikerState.IDLE);
    _drivetrain.drive(1, 0);
    Timer.delay(4);
    _drivetrain.drive(0, 0);
    Timer.delay(.1);
    _drivetrain.drive(-1, 0);
    Timer.delay(4);
    _spiker.setWantedState(SpikerState.SPIKE_CLOSE);
    Timer.delay(.2);
    _intake.setWantedState(IntakeState.FEED);
    Timer.delay(.1);
    _spiker.setWantedState(SpikerState.IDLE);
    _drivetrain.drive(1, 0);
    Timer.delay(4);
    _drivetrain.drive(0,0);
   }
}
