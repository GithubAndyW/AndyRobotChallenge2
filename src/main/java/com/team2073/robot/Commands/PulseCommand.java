package com.team2073.robot.Commands;

import com.team2073.common.command.AbstractLoggingCommand;
import com.team2073.robot.ApplicationContext;
import com.team2073.robot.Subsystems.SimpleSubsystem;
import com.team2073.common.util.*;

import static com.team2073.robot.Subsystems.SimpleSubsystem.timer;

public class PulseCommand extends AbstractLoggingCommand{
    ApplicationContext appCTX = ApplicationContext.getInstance();
    SimpleSubsystem simpleSubsystem = appCTX.getSimpleSubsystem();

    @Override
    protected void initializeDelegate() {
        timer.start();

            simpleSubsystem.setCurrentState(SimpleSubsystem.SimpleSubsystemState.PULSE);
        }

    @Override
    protected void endDelegate() {
        simpleSubsystem.setCurrentState(SimpleSubsystem.SimpleSubsystemState.AXIS);
    }

    @Override
    protected boolean isFinishedDelegate() {
        return false;
    }
}

