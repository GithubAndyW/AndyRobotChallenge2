package com.team2073.robot.Commands;

import com.team2073.common.command.AbstractLoggingCommand;
import com.team2073.robot.ApplicationContext;
import com.team2073.robot.Subsystems.SimpleSubsystem;

public class ReturnCommand extends AbstractLoggingCommand {
    ApplicationContext appCTX = ApplicationContext.getInstance();
    SimpleSubsystem simpleSubsystem = appCTX.getSimpleSubsystem();

    @Override
    protected void initializeDelegate() {
        simpleSubsystem.setCurrentState(SimpleSubsystem.SimpleSubsystemState.RETURN);
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
