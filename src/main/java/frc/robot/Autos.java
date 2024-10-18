package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Spiker;

public class Autos {
    private Drivetrain _drivetrain;
    private Intake _intake;
    private Spiker _spiker;
    public static Timer timer = new Timer();

   public Autos() {
    _drivetrain = Drivetrain.getInstance();
    _intake = Intake.getInstance();
    _spiker = Spiker.getInstance();
    _drivetrain.init();
    _intake.init();
    _spiker.init();
   }

   public void Test() {

   }
}
