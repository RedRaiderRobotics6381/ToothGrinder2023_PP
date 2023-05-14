package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.Arm.ArmCloseGrabberCmd;
import frc.robot.commands.Arm.ArmOpenGrabberCmd;
import frc.robot.commands.Arm.ArmSliderBottomCmd;
import frc.robot.commands.Arm.ArmSliderHumanPlayerCmd;
import frc.robot.commands.Arm.ArmSliderTopCmd;
import frc.robot.commands.Arm.ArmSuckCmd;
import frc.robot.commands.Drive.SwerveJoystickCmd;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {

        public static final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
        public static final ArmSubsystem armSubsystem = new ArmSubsystem();

        public final XboxController driverJoytick = new XboxController(OIConstants.kDriverControllerPort);
        public final static XboxController secondaryJoystick = new XboxController(
                        OIConstants.kSecondaryDriverControllerPort);
        
        public RobotContainer() {
                swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                                swerveSubsystem,
                                () -> -driverJoytick.getRawAxis(OIConstants.kDriverYAxis),
                                () -> -driverJoytick.getRawAxis(OIConstants.kDriverXAxis),
                                () -> -driverJoytick.getRawAxis(OIConstants.kDriverRotAxis),
                                () -> driverJoytick.getRawAxis(2),
                                () -> driverJoytick.getRawButton(5),
                                () -> driverJoytick.getRawButton(6)));

                configureButtonBindings();
        }

        private void configureButtonBindings() {
                new JoystickButton(secondaryJoystick, 1).onTrue(
                        new ArmSliderBottomCmd(armSubsystem));
                new JoystickButton(secondaryJoystick, 2).onTrue(
                        new ArmSliderHumanPlayerCmd(armSubsystem));
                new JoystickButton(secondaryJoystick, 4).onTrue(
                        new ArmSliderTopCmd(armSubsystem));

                new JoystickButton(secondaryJoystick, 5).onTrue(
                                new ArmOpenGrabberCmd(armSubsystem, () -> secondaryJoystick.getRawButton(5)));
                new JoystickButton(secondaryJoystick, 6).onTrue(
                                new ArmCloseGrabberCmd(armSubsystem, () -> secondaryJoystick.getRawButton(6)));

                new JoystickButton(secondaryJoystick, 3).toggleOnTrue(
                        new ArmSuckCmd(armSubsystem));
        }
}