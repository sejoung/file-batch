package com.github.sejoung.configuration;

import com.github.sejoung.Incrementer.CurrentTimeIncrementer;
import com.github.sejoung.listener.JobCompletionNotificationListener;
import com.github.sejoung.listener.StepExecutionNotificationListener;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kim se joung
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepConfiguration {

	private final JobBuilderFactory jobBuilderFactory;

	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job audienceUserJob(JobCompletionNotificationListener listener, Step testStep1,
			Step testStep2, Step testStep3) {
		return jobBuilderFactory.get("testJob").incrementer(new CurrentTimeIncrementer())
				.listener(listener).flow(testStep1).next(testStep2).next(testStep3).end().build();
	}


	@Bean
	public Step testStep1(StepExecutionNotificationListener listener) {
		return stepBuilderFactory.get("testStep1").tasklet((var contribution, var chunkContext) -> {

			var cateMap = new HashMap<Integer, Integer>();

			chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
					.put("cateMap", cateMap);

			return RepeatStatus.FINISHED;
		}).listener(listener).build();
	}

	@Bean
	public Step testStep2(StepExecutionNotificationListener listener) {
		return stepBuilderFactory.get("testStep2").tasklet((var contribution, var chunkContext) -> {
			var cateMap = (Map<Integer, Integer>) chunkContext.getStepContext().getStepExecution()
					.getJobExecution().getExecutionContext().get("cateMap");
			return RepeatStatus.FINISHED;
		}).listener(listener).build();
	}

	@Bean
	public Step testStep3(StepExecutionNotificationListener listener) {
		return stepBuilderFactory.get("testStep3").tasklet((var contribution, var chunkContext) -> {
			var cateMap = (Map<Integer, Integer>) chunkContext.getStepContext().getStepExecution()
					.getJobExecution().getExecutionContext().get("cateMap");

			chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
					.put("cateMap", cateMap);

			return RepeatStatus.FINISHED;
		}).listener(listener).build();
	}


}
