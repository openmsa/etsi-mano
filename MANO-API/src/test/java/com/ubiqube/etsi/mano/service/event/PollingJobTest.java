package com.ubiqube.etsi.mano.service.event;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class PollingJobTest {
	@Autowired
	private Scheduler scheduler;

	void testName() throws Exception {
		final StdSchedulerFactory sf = new StdSchedulerFactory();
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("processId", new Long("9"));
		final JobDetail jobDetail = JobBuilder.newJob(PollingJob.class)
				.withIdentity("job1", "group1")
				.usingJobData(jobDataMap)
				.build();
		final Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
		Thread.sleep(100000);
	}
}
