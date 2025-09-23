// 代码生成时间: 2025-09-24 01:17:51
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.NameMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskExecutor {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskExecutor.class);
    private Scheduler scheduler;
    private ExecutorService executorService;
    private SqlSessionFactory sqlSessionFactory;

    public ScheduledTaskExecutor(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.executorService = Executors.newCachedThreadPool();
    }

    public void start() throws SchedulerException, IOException {
        // Initialize scheduler
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        logger.info("Scheduler started");
    }

    public void scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        scheduler.scheduleJob(jobDetail, trigger);
        logger.info("Job scheduled");
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            logger.error("ExecutorService shutdown interrupted", e);
        }
        scheduler.shutdown(true);
        logger.info("Scheduler and ExecutorService shutdown");
    }

    public static class SampleJob implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // Your business logic here
                logger.info("SampleJob executed");
            } catch (Exception e) {
                throw new JobExecutionException(e);
            }
        }
    }

    public static void main(String[] args) {
        try {
            SqlSessionFactory sqlSessionFactory = ...; // Initialize your SqlSessionFactory
            ScheduledTaskExecutor executor = new ScheduledTaskExecutor(sqlSessionFactory);
            executor.start();

            JobDetail job = JobBuilder.newJob(SampleJob.class)
                    .withIdentity("sampleJob", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("sampleTrigger", "group1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(10, 0))
                    .build();

            executor.scheduleJob(job, trigger);

            // Keep the application running to allow the scheduler to execute jobs
            executorService.submit(() -> {
                while (true) {
                    // Keep alive
                }
            }).get();
        } catch (Exception e) {
            logger.error("Error occurred", e);
        }
    }
}
