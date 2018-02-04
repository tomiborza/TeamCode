/**
 * Created by Steve on 2/2/2018.
 */

package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;


@Autonomous
public class rangeSensor extends LinearOpMode
{
    private DcMotor motorRight, motorLeft;
    private ModernRoboticsI2cRangeSensor rangeSensor;

    @Override
    public void runOpMode()
    {
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensor");

        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.addData("cm", "%.2f cm", rangeSensor.getDistance(DistanceUnit.CM));
            telemetry.update();

            while(rangeSensor.getDistance(DistanceUnit.CM) > 50)
            {
                telemetry.addData("cm","%.2f cm", rangeSensor.getDistance(DistanceUnit.CM));
                telemetry.update();
                motorRight.setPower(1);
                motorLeft.setPower(1);
            }

            motorRight.setPower(0);
            motorLeft.setPower(0);

            while(rangeSensor.getDistance(DistanceUnit.CM) < 52)
            {
                telemetry.addData("cm","%.2f cm", rangeSensor.getDistance(DistanceUnit.CM));
                telemetry.update();
                motorRight.setPower(-1);
                motorLeft.setPower(-1);
            }

            motorRight.setPower(0);
            motorLeft.setPower(0);

        }
    }
}
