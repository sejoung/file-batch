package com.github.sejoung.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kim se joung
 *
 */
@Slf4j
@Component
public class StepExecutionNotificationListener extends StepExecutionListenerSupport {
    
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("afterStep");
        return super.afterStep(stepExecution);
    }
    

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.debug("beforeStep ");
        super.beforeStep(stepExecution);
    }

}