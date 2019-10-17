package com.github.sejoung.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kim se joung
 *
 */
@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private final StopWatch sw = new StopWatch();

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            log.info("!!! JOB STARTED!");
            sw.start();
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED!");
            sw.stop();
        }
        log.info("시간 " + (sw.getTotalTimeMillis()) + "Millis");
    }
}
