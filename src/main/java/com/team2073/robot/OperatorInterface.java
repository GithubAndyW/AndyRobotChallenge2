package com.team2073.robot;

import com.team2073.robot.Commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperatorInterface {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    public final Joystick controller  = appCTX.getController();


    private final JoystickButton a = new JoystickButton(controller, 1);
    private final JoystickButton lb = new JoystickButton(controller, 5);
    private final JoystickButton y = new JoystickButton(controller, 4);
    private final JoystickButton b = new JoystickButton(controller, 2);
    private final JoystickButton x = new JoystickButton(controller, 3);

    public void init() {
        a.whileHeld(new HalfPowerCommand());
        lb.whileHeld(new PulseCommand());
        y.toggleWhenPressed(new CruiseCommand());
        b.whenPressed(new MoveCommand());
        x.whenPressed(new ReturnCommand());
    }
}
