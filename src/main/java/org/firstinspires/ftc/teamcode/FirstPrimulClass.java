package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class FirstPrimulClass extends OpMode {

    private DcMotor motorRight, motorLeft, motorGrab = null;

    private Servo servoGrabRight, servoGrabLeft;

    int position = 0;

    boolean ok = false;


    @Override
    public void init() {

        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorGrab = hardwareMap.get(DcMotor.class, "motorGrab");

        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        motorGrab.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorGrab.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorGrab.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servoGrabRight = hardwareMap.get(Servo.class, "servoGrabRight");
        servoGrabRight = hardwareMap.servo.get("servoGrabRight");
        servoGrabLeft = hardwareMap.get(Servo.class, "servoGrabLeft");
        servoGrabLeft = hardwareMap.servo.get("servoGrabLeft");

        servoGrabLeft.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void loop() {
        motorLeft.setPower(gamepad1.left_stick_y);
        motorRight.setPower(gamepad1.right_stick_y);

        if(!ok) {
            position = motorGrab.getCurrentPosition();
        }
        else if (motorGrab.getCurrentPosition() > position + 500) {
            servoGrabRight.setPosition(0.9);
            servoGrabLeft.setPosition(1);
            ok = false;
        }
        if (gamepad2.dpad_up) {
            motorGrab.setTargetPosition(position + 50);
            motorGrab.setPower(0.25);
        }
        if (gamepad2.dpad_down) {
            motorGrab.setTargetPosition(position - 50);
            motorGrab.setPower(0.1);
        }
        if (gamepad2.a && gamepad2.left_stick_button) {
            ok = true;
            motorGrab.setTargetPosition(position + 560);
            motorGrab.setPower(0.75);
        }
        if (gamepad2.x) {
            servoGrabRight.setPosition(0.65);
            servoGrabLeft.setPosition(0.70);
        }
        if (gamepad2.b) {
            servoGrabRight.setPosition(0.9);
            servoGrabLeft.setPosition(1);
        }
        if (gamepad1.a) {
            motorLeft.setPower(1);
            motorRight.setPower(1);
        }
        if (gamepad1.y) {
            motorLeft.setPower(-1);
            motorRight.setPower(-1);
        }

       /* if (gamepad1.left_bumper) {
            motorLeft.setPower(-0.5);
        }
        if (gamepad1.right_bumper) {
            motorRight.setPower(-0.5);
        }
        if (gamepad1.dpad_up) {
            motorLeft.setPower(-0.5);
            motorRight.setPower(-0.5);
        }
        if (gamepad1.dpad_down) {
            motorRight.setPower(0.5);
            motorLeft.setPower(0.5);
        }*/

        if (gamepad1.left_bumper) {
            motorLeft.setPower(-0.5);
            motorRight.setPower(-0.5);
        }
        if (gamepad1.right_bumper) {
            motorRight.setPower(0.5);
            motorLeft.setPower(0.5);
        }
        if (gamepad1.dpad_left) {
            motorRight.setPower(-0.5);
        }
        if (gamepad1.dpad_right) {
            motorLeft.setPower(-0.5);
        }
    }
}