package frc.robot.commands.Arm;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ArmSubsystem;

public class ArmCloseGrabberCmd extends CommandBase {

    private final ArmSubsystem armSubsystem;
    Supplier<Boolean> button;

    public ArmCloseGrabberCmd(ArmSubsystem armSubsystem, Supplier<Boolean> button) {
        this.armSubsystem = armSubsystem;
        this.button = button;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
        RobotContainer.secondaryJoystick.setRumble(RumbleType.kRightRumble, 0.5);
    }

    @Override
    public void execute() {
        System.out.println("Close: " + armSubsystem.grabberEncoder.getPosition());
        armSubsystem.grabberMotor.set(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.grabberMotor.set(0);
        RobotContainer.secondaryJoystick.setRumble(RumbleType.kRightRumble, 0);
    }

    @Override
    public boolean isFinished() {
        if(button.get() == true){
            return false;
        }
        else{
            return true;
        }
    }
}