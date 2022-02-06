package com.team2073.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.Joystick;
import java.lang.Math;
import com.team2073.common.util.*;


public class SimpleSubsystem implements AsyncPeriodicRunnable {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    private final Joystick controller = appCTX.getController();
    private final CANSparkMax motor =  appCTX.getMotor();
    public static Timer timer = new Timer();

    private SimpleSubsystemState currentState = SimpleSubsystemState.AXIS;

    private double output = 0;
    double cruiseOutput = 0;
    double position = 0;

    public SimpleSubsystem()  { autoRegisterWithPeriodicRunner(); }

    @Override
    public void onPeriodicAsync() {

        double getPosition = 0;
        switch (currentState) {
            case AXIS:
                output = getAxisOutput();
                cruiseOutput = output;
                getPosition = motor.getEncoder().getPosition();
                System.out.println(getPosition);
                break;
            case HALF_POWER:
                output = 0.5;
                break;
            case PULSE:
                double time = timer.getElapsedTime();
                time = time / 1000;
                System.out.println(time);
                if (time % 2 > 1) {
                    output = 0.25;
                } else {
                    output = 0;
                }
                break;
            case CRUISE:
                System.out.println(cruiseOutput + "test");
                output = cruiseOutput;

                if (Math.abs(getAxisOutput()) > Math.abs(output)) {
                    output = getAxisOutput();
                }
                break;
            case MOVE:
                output = .5;
                getPosition = motor.getEncoder().getPosition();
                System.out.println(getPosition);
                if (getPosition > 1000) {
                    output = 0;
                }
                break;
            case RETURN:
                output = -0.5;
                getPosition = motor.getEncoder().getPosition();
                final double newPosition = getPosition;
                System.out.println(newPosition + " Testing");
                double positionMinus = newPosition - getPosition;
                if (positionMinus > newPosition) {
                    output = 0;
                }
                break;
            case STOP:
                output = 0;
                break;
        }

        //limits output
        if (output < 0.2 && output > -0.2) {
            output = 0;
        } else if (output > 0.8) {
            output = 0.8;
        } else if (output < -0.8) {
            output = -0.8;
        }

        System.out.println(output);
        motor.set(output);
    }

    private double getAxisOutput() {
        return -controller.getRawAxis(1);
    }

    public void setCurrentState(SimpleSubsystemState currentState) {
        this.currentState = currentState;
    }

    public enum SimpleSubsystemState {
        AXIS,
        PULSE,
        HALF_POWER,
        CRUISE,
        MOVE,
        RETURN,
        STOP
    }
}
