package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;


/**
 * Created by tomi on 1/18/18.
 */
@TeleOp
public class FirstPrimulClass extends LinearOpMode {
    //private Gyroscope imu;
    private DcMotor motorRight, motorLeft;

    //private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    //private Servo servoTest;
    //private TouchSensor digitalTouch;
    @Override
    public void runOpMode() {
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");

        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        //imu = hardwareMap.get(Gyroscope.class, "imu");
        //motorTest = hardwareMap.get(DcMotor.class, "motorTest");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        //servoTest = hardwareMap.get(Servo.class, "servoTest");
        //servoTest= hardwareMap.servo.get("servoTest");
        //digitalTouch = hardwareMap.touchSensor.get("digitalTouch");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //if(digitalTouch.isPressed()){
            //  motorTest.setPower(1.0);
            //}else{
            //  motorTest.setPower(0);
            //}
            //servoTest.setPosition(0.5);
            motorLeft.setPower(1.0);
            motorRight.setPower(-1.0);
            sleep(2000);
            motorLeft.setPower(1.0);
            motorRight.setPower(1.0);
            sleep(2000);
            motorLeft.setPower(-1.0);
            motorRight.setPower(-1.0);
            sleep(2000);
            motorLeft.setPower(-1.0);
            motorRight.setPower(1.0);
            sleep(2000);
            telemetry.addData("Status", "Running");
            telemetry.update();
        }

    }
}