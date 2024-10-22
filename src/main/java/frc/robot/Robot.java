// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Enums.IntakeState;
import frc.robot.Enums.SpikerState;
import frc.robot.Subsystems.*;
import frc.robot.Utilities.Constants;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "nothing";
   private static final String kTestAuto = "test";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

   private XboxController driverController = new XboxController(Constants.kDriverControllerUsbSlot);
   private XboxController operatorController = new XboxController(Constants.kOperatorControllerUsbSlot);
   private Drivetrain _drivetrain;
   private Intake _intake;
   private Spiker _spiker;
   private Autos _auto;
   public static Timer timer= new Timer();

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("Test", kTestAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    Constants.defaultConfig.peakCurrentLimit = 35;
    Constants.defaultConfig.peakCurrentDuration = 1000;
    Constants.defaultConfig.continuousCurrentLimit = 30;
    _drivetrain = Drivetrain.getInstance();
    _intake = Intake.getInstance();
    _spiker = Spiker.getInstance();
    _auto = Autos.getInstance();
    _drivetrain.init();
    _intake.init();
    _spiker.init();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    m_autoSelected = m_chooser.getSelected();
    switch (m_autoSelected) {
      case kTestAuto:
        _auto.Test();
        break;
      case kDefaultAuto:
      default:
        _drivetrain.drive(.8, 0);
        Timer.delay(2);
        _drivetrain.drive(0, 0);
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    intakeTeleop();
    spikerTeleop();
    _intake.handleState();
    _spiker.handleState();
    _drivetrain.periodic();
  }

  public void intakeTeleop(){
    if (driverController.getRightTriggerAxis() > .2 && _intake.isHolding()) {
      _intake.setWantedState(IntakeState.FEED);
    } else if (driverController.getBButton()) {
      _intake.setWantedState(IntakeState.EJECT);
    }
  }

  public void spikerTeleop() {
    if (operatorController.getYButton()) {
      _spiker.setWantedState(SpikerState.SPIKE_FAR);
    } else if (operatorController.getAButton()) {
      _spiker.setWantedState(SpikerState.SPIKE_CLOSE);
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
