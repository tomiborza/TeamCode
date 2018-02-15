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
public class AutoNomous extends LinearOpMode
{
    //private Gyroscope imu;
    private DcMotor motorRight, motorLeft, motorGrab;
    //private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    private Servo servoArm, servoGrabRight, servoGrabLeft;
    //private TouchSensor digitalTouch;
    private ColorSensor colorSensorArm, colorSensorFloor;
    private ModernRoboticsI2cRangeSensor rangeSensor;

    @Override
    public void runOpMode()
    {
        //referinta la motoare

        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorGrab = hardwareMap.get(DcMotor.class, "motorGrab");

        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //referinta la senzori culoare + variabile pentru citire

        colorSensorFloor = hardwareMap.get(ColorSensor.class, "colorSensorFloor");
        colorSensorArm = hardwareMap.get(ColorSensor.class, "colorSensorArm");
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final double SCALE_FACTOR = 255;

        //referinta senzor de distanta

        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensor");

        //imu = hardwareMap.get(Gyroscope.class, "imu");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");


        //referinta servouri

        servoArm = hardwareMap.get(Servo.class, "servoArm");
        servoArm = hardwareMap.servo.get("servoArm");
        servoArm.setPosition(0.5);

        servoGrabRight = hardwareMap.get(Servo.class, "servoGrabRight");
        servoGrabRight = hardwareMap.servo.get("servoGrabRight");
        servoGrabLeft = hardwareMap.get(Servo.class, "servoGrabRight");
        servoGrabLeft = hardwareMap.servo.get("servoGrabLeft");
        servoGrabLeft.setDirection(Servo.Direction.REVERSE);

        servoGrabRight.setPosition(0.4);
        servoGrabLeft.setPosition(0.4);

        //digitalTouch = hardwareMap.touchSensor.get("digitalTouch");

        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive())
        {
            //citire senzor podea in variable redFloor si blueFloor

            Color.RGBToHSV((int) (colorSensorFloor.red() * SCALE_FACTOR),
                    (int) (colorSensorFloor.green() * SCALE_FACTOR),
                    (int) (colorSensorFloor.blue() * SCALE_FACTOR),
                    hsvValues);

            int redFloor = colorSensorFloor.red();
            int blueFloor = colorSensorFloor.blue();

            //bratul lateral se pozitioneaza intre bile

            for(double i = 0.5; i > 0; i = i - 0.01)
            {
                servoArm.setPosition(i);
                sleep(25);
            }

            //citire senzor brat lateral in variable redArm si blueArm

            Color.RGBToHSV((int) (colorSensorArm.red() * SCALE_FACTOR),
                    (int) (colorSensorArm.green() * SCALE_FACTOR),
                    (int) (colorSensorArm.blue() * SCALE_FACTOR),
                    hsvValues);

            int redArm = colorSensorArm.red();
            int blueArm = colorSensorArm.blue();

            sleep(3000);

            //in funtie de culoare platformei si a bilei merge in fata sau spate
            //pentru a darma bila de culoare opusa

            if(redFloor > blueFloor)
            {
                if(redArm > blueArm)
                {
                    motorRight.setPower(-1);
                    motorLeft.setPower(-1);
                    sleep(50);
                    motorRight.setPower(0);
                    motorLeft.setPower(0);
                }
                else
                {
                    motorRight.setPower(1);
                    motorLeft.setPower(1);
                    sleep(50);
                    motorRight.setPower(0);
                    motorLeft.setPower(0);
                }
            }
            else
            {
                if(redArm > blueArm)
                {
                    motorRight.setPower(1);
                    motorLeft.setPower(1);
                    sleep(50);
                    motorRight.setPower(0);
                    motorLeft.setPower(0);
                }
                else
                {
                    motorRight.setPower(-1);
                    motorLeft.setPower(-1);
                    sleep(50);
                    motorRight.setPower(0);
                    motorLeft.setPower(0);
                }
            }

            sleep(2000);

            //ridica bratul de prindere

            motorGrab.setPower(-0.5);
            sleep(750);
            motorGrab.setPower(0);

            sleep(2000);

            //duce bratul lateral in interior

            for(double i = 0; i < 1; i = i + 0.01)
            {
                servoArm.setPosition(i);
                sleep(25);
            }

            //coboara bratul de prindere

            motorGrab.setPower(0.5);
            sleep(250);
            motorGrab.setPower(0);

            sleep(10000);

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
