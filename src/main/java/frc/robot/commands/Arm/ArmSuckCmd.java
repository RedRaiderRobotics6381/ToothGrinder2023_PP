package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSuckCmd extends CommandBase {

    private final ArmSubsystem armSubsystem;

    public ArmSuckCmd(ArmSubsystem armSubsystem) {
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        armSubsystem.suck.set(0.2);
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.suck.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}