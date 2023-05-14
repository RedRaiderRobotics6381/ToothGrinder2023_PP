package frc.robot.subsystems;

// import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
    public CANSparkMax leftArmSlider;
    public CANSparkMax rightArmSlider;

    public CANSparkMax suck;

    public RelativeEncoder sliderEncoder;
    public RelativeEncoder grabberEncoder;

    public CANSparkMax grabberMotor;

    public static CANSparkMax armRotateMotor;

    public static SparkMaxAbsoluteEncoder armRotateEncoder;
    // public static RelativeEncoder armRotateEncoder;

    private final static I2C.Port i2cPort = I2C.Port.kOnboard;
    public final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);



    public ArmSubsystem() {
        leftArmSlider = new CANSparkMax(Constants.ArmConstants.kLeftSliderMotor, MotorType.kBrushless);
        rightArmSlider = new CANSparkMax(Constants.ArmConstants.kRightSliderMotor, MotorType.kBrushless);

        sliderEncoder = leftArmSlider.getEncoder();

        grabberMotor = new CANSparkMax(Constants.ArmConstants.kGrabberMotor,MotorType.kBrushless);
        armRotateMotor = new CANSparkMax(Constants.ArmConstants.kArmRotateMotor,MotorType.kBrushless);

        // armRotateEncoder = armRotateMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 8192);
        armRotateEncoder = armRotateMotor.	getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle);
        armRotateEncoder.setPositionConversionFactor(360);
        armRotateEncoder.setZeroOffset(300);

        suck = new CANSparkMax(Constants.ArmConstants.kVaccumMotor, MotorType.kBrushless);

        grabberEncoder = grabberMotor.getEncoder();

        armRotateEncoder.setPositionConversionFactor(0);
        sliderEncoder.setPositionConversionFactor(1.31741221882);
        grabberEncoder.setPositionConversionFactor(2.666);
    }

    public void suckOn(){
        suck.set(1);
    }

    public void suckOff(){
        suck.set(0);
    }
}