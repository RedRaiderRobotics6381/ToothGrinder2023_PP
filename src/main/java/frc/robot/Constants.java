package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

public final class Constants {

    public static final class ModuleConstants {
        
        //Setting the wheel diameter in meters (4"=.01016m)
        public static final double kWheelDiameterMeters = Units.inchesToMeters(4); // Almost sure, physical measurement is hard to measure
       
        //Setting the gear ratio from drive motor to wheel
        public static final double kDriveMotorGearRatio = 6.75; // Sure
       
        //Setting the gear ratio for turning motor to wheel
        public static final double kTurningMotorGearRatio = 150 / 7;// Sure:
       
        //Calculating rotations to radians (gear ratio of the turning motor * 2 * Pi)
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI; // Sure

         //Calculating rotations to meters (gear ratio of the turning motor * Pi * wheel diameter in meters)
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters; // Sure

        //Calculating rotations to meters/sec, basically taking the last calculation and dividing by 60
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60; // Sure

        //For every this many rotations of the motor, the wheel turns one full rotation
        public static final double kTurningEncoderRotPerRot = 1 / kTurningMotorGearRatio; // Sure

        //Calculating revolutions/minute to radians/sec ((2*Pi*(1/6.75))/60)
        public static final double kTurningEncoderRPM2RadPerSec = 2*Math.PI*kTurningEncoderRotPerRot/60; // Almost sure


        // Tune:
        public static final double kPTurning = 0.5;
    }

    public static final class DriveConstants {

        //Distance between right and left wheels
        public static final double kTrackWidth = -Units.inchesToMeters(21); // Sure

        // Distance between front and back wheels
        public static final double kWheelBase = Units.inchesToMeters(21); // Sure
    
    
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2));


        // Can ID's for the swerve drive motors
        public static final int kFrontLeftDriveMotorPort = 1; // Sure
        public static final int kFrontRightDriveMotorPort = 2; // Sure
        public static final int kBackLeftDriveMotorPort = 5; // Sure
        public static final int kBackRightDriveMotorPort = 4; // Sure
        // Can ID's for the swerve turning motors
        public static final int kFrontLeftTurningMotorPort = 6; // Sure
        public static final int kFrontRightTurningMotorPort = 7; // Sure
        public static final int kBackLeftTurningMotorPort = 3; // Sure
        public static final int kBackRightTurningMotorPort = 8; // Sure

        // Determines direction of the swerve turning motors
        public static final boolean kFrontLeftTurningEncoderReversed = true;
        public static final boolean kBackLeftTurningEncoderReversed = true;
        public static final boolean kFrontRightTurningEncoderReversed = true;
        public static final boolean kBackRightTurningEncoderReversed = true;

        // Determines direction of the swerve drive motors
        public static final boolean kFrontLeftDriveEncoderReversed = true;
        public static final boolean kBackLeftDriveEncoderReversed = true;
        public static final boolean kFrontRightDriveEncoderReversed = false;
        public static final boolean kBackRightDriveEncoderReversed = false;

        //Can ID's for the swerve encoders
        public static final int kFrontLeftDriveAbsoluteEncoderPort = 9; // Sure
        public static final int kFrontRightDriveAbsoluteEncoderPort = 10; // Sure
        public static final int kBackLeftDriveAbsoluteEncoderPort = 12; // Sure
        public static final int kBackRightDriveAbsoluteEncoderPort = 11; // Sure

        // Almost Sure: Determines the direction of sensing for the swerve encoders
        public static final boolean kFrontLeftDriveAbsoluteEncoderReversed = false;
        public static final boolean kBackLeftDriveAbsoluteEncoderReversed = false;
        public static final boolean kFrontRightDriveAbsoluteEncoderReversed = false;
        public static final boolean kBackRightDriveAbsoluteEncoderReversed = false;


        // Sure: Offset to basically make the drive wheels straight when the encoders read zero
        public static final double kFrontLeftDriveAbsoluteEncoderOffsetRad = 4.928680271476566;
        public static final double kFrontRightDriveAbsoluteEncoderOffsetRad = 4.766078307960687;
        public static final double kBackLeftDriveAbsoluteEncoderOffsetRad = 5.223204582750609;
        public static final double kBackRightDriveAbsoluteEncoderOffsetRad = 2.339320701525603;

        // Maybe check:
        // public static final double kPhysicalMaxSpeedMetersPerSecond = 5*(2.0/0.25); // max speed, roughly 0.6 full speed, was 5, changing this didn't change anything it seemed, maybe slowed down the turning motors.

        public static double kPhysicalMaxSpeedMetersPerSecond = 7;

        // Multiplying this by 2 divided the actual max speed the drive motors were going by 2.

        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 4;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = //
                kPhysicalMaxAngularSpeedRadiansPerSecond / 4;
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = DriveConstants.kPhysicalMaxSpeedMetersPerSecond / 2;
        public static final double kMaxAngularSpeedRadiansPerSecond = //
                DriveConstants.kPhysicalMaxAngularSpeedRadiansPerSecond / 2;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;
        public static final double kPXController = 0;
        public static final double kPYController = 0;
        public static final double kPThetaController = 0;

        public static final TrapezoidProfile.Constraints kThetaControllerConstraints = //
                new TrapezoidProfile.Constraints(
                        kMaxAngularSpeedRadiansPerSecond,
                        kMaxAngularAccelerationRadiansPerSecondSquared);
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kSecondaryDriverControllerPort = 1;

        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.05;
    }

    public static final class ArmConstants {
        public static final int kLeftSliderMotor = 13;
        public static final int kRightSliderMotor = 14;
        public static final int kArmRotateMotor = 16;
        public static final int kGrabberMotor = 17;

        public static final int kVaccumMotor = 15;

        // uncertain
        public static final int gArmSliderBottom = 1;
        public static final int gArmSliderTop = 39;
        public static final int gArmSliderHumanPlayer = 37;

        public static final double gArmOffset = 1;
        public static final double gRotateoffset = 0.25;
        public static final double gSliderSpeed = -0.4;
        public static final double gRotateSpeed = 0.3;
    }
}