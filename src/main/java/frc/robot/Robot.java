// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.server.PathPlannerServer;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Auto.Autos;
import frc.robot.subsystems.ArmSubsystem;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command m_autonomousCommand;

    public RobotContainer m_robotContainer;

    double targetPos = 180;

    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
        PathPlannerServer.startServer(5811);
        m_robotContainer = new RobotContainer();
        Autos.init();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and
     * test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled
        // commands, running already-scheduled commands, removing finished or
        // interrupted commands,
        // and running subsystem periodic() methods. This must be called from the
        // robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = Autos.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {

        double rotateoffset = 5;
        double pos1 = 95;
        double pos2 = 115;
        double pos3 = 180;
        double pos4 = 285;
        double restriction1 = 85;
        double restriction2 = 280;
        double rotateSpeed = 0.5;
        double P = ((Math.abs(ArmSubsystem.armRotateEncoder.getPosition() - targetPos)+50)/300);

        System.out.println(ArmSubsystem.armRotateEncoder.getPosition());

       if(RobotContainer.secondaryJoystick.getPOV() == 0){
        targetPos = pos1;
       }
       if(RobotContainer.secondaryJoystick.getPOV() == 90){
        targetPos = pos2;
       }
       if(RobotContainer.secondaryJoystick.getPOV() == 180){
        targetPos = pos3;
       }
       if(RobotContainer.secondaryJoystick.getPOV() == 270){
        targetPos = pos4;
       }
       ArmSubsystem.armRotateMotor.set(0);
       if(ArmSubsystem.armRotateEncoder.getPosition() > targetPos + rotateoffset){
        ArmSubsystem.armRotateMotor.set(-rotateSpeed * P);
       }
       if(ArmSubsystem.armRotateEncoder.getPosition() < targetPos - rotateoffset){
        ArmSubsystem.armRotateMotor.set(rotateSpeed * P);
       }

       if(RobotContainer.secondaryJoystick.getRawAxis(5) < -0.25 && ArmSubsystem.armRotateEncoder.getPosition() > restriction1){
        ArmSubsystem.armRotateMotor.set(RobotContainer.secondaryJoystick.getRawAxis(5)* 0.4);
        targetPos = ArmSubsystem.armRotateEncoder.getPosition();
       }

       if(RobotContainer.secondaryJoystick.getRawAxis(5) > 0.25 && ArmSubsystem.armRotateEncoder.getPosition() < restriction2){
        ArmSubsystem.armRotateMotor.set(RobotContainer.secondaryJoystick.getRawAxis(5)* 0.4);
        targetPos = ArmSubsystem.armRotateEncoder.getPosition();
       }

    // ArmSubsystem.armRotateMotor.set(RobotContainer.secondaryJoystick.getRawAxis(5)*0.2);
    // System.out.println(RobotContainer.secondaryJoystick.getRawAxis(5)*0.2);
    
    // Color detectedColor = ArmSubsystem.m_colorSensor.getColor();
    // int proximity = ArmSubsystem.m_colorSensor.getProximity();
    

    // SmartDashboard.putNumber("Red", detectedColor.red);
    // SmartDashboard.putNumber("Green", detectedColor.green);
    // SmartDashboard.putNumber("Blue", detectedColor.blue);
    // SmartDashboard.putNumber("Proximity", proximity);

    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }
}
