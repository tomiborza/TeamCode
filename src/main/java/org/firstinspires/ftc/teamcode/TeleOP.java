package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleOP extends OpMode{

    private DcMotor leftWheel, rightWheel, armWheel;

    private boolean dpad_up,dpad_down,X,B,A,Y;

    double leftWheelPower1, leftWheelPower2;
    double rightWheelPower1, rightWheelPower2;

    private Servo servoGrabRight, servoGrabLeft;

    @Override
    public void init() {
        leftWheel = hardwareMap.dcMotor.get("left_wheel");
        rightWheel = hardwareMap.dcMotor.get("right_wheel");
        armWheel = hardwareMap.dcMotor.get("arm_wheel");

        leftWheel.setDirection(DcMotor.Direction.REVERSE);

        servoGrabRight = hardwareMap.servo.get("servoGrabRight");
        servoGrabLeft = hardwareMap.servo.get("servoGrabLeft");

        servoGrabLeft.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void loop() {
        leftWheelPower1 = -gamepad1.right_stick_y;
        rightWheelPower1 = -gamepad1.left_stick_y;
        leftWheelPower2 = -gamepad2.right_stick_y;
        rightWheelPower2 = -gamepad2.left_stick_y;

        dpad_up = gamepad1.dpad_up;
        dpad_down = gamepad1.dpad_down;

        X = gamepad1.x;
        B = gamepad1.b;
        A = gamepad1.a;
        Y = gamepad1.y;

        leftWheel.setPower(leftWheelPower1);
        rightWheel.setPower(rightWheelPower1);
        leftWheel.setPower(leftWheelPower2/2);
        rightWheel.setPower(rightWheelPower2/2);

        if (dpad_up) armWheel.setPower(0.5);
        else armWheel.setPower(0);

        if (dpad_down) armWheel.setPower(-0.5);
        else armWheel.setPower(0);

        if(X)
        {
            servoGrabRight.setPosition(0.4);
            servoGrabLeft.setPosition(0.4);
        }
        if(B)
        {
            servoGrabRight.setPosition(0);
            servoGrabLeft.setPosition(0);
        }
        if(A)
        {
            leftWheel.setPower(1);
            rightWheel.setPower(1);
        }
        if(Y)
        {
            leftWheel.setPower(-1);
            rightWheel.setPower(-1);
        }
    }
}