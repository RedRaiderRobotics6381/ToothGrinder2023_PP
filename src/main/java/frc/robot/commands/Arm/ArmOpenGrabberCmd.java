package frc.robot.commands.Arm;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmOpenGrabberCmd extends CommandBase {

    private final ArmSubsystem armSubsystem;
    Supplier<Boolean> button;

    public ArmOpenGrabberCmd(ArmSubsystem armSubsystem, Supplier<Boolean> button) {
        this.armSubsystem = armSubsystem;
        this.button = button;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        System.out.println("Open: " + armSubsystem.grabberEncoder.getPosition());
        armSubsystem.grabberMotor.set(-0.5);
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.grabberMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        if(button.get()){
            return false;
        }
        else{
            return true;
        }
    }
}