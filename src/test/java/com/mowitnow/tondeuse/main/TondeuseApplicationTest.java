package com.mowitnow.tondeuse.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TondeuseApplication.class)
public class TondeuseApplicationTest {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job tondeuseJob;

    private TondeuseApplication tondeuseApplication;

    @BeforeEach
    void init() {
        tondeuseApplication = new TondeuseApplication(jobLauncher, tondeuseJob);
    }

    @Test
    public void testRunWithCorrectArguments() throws Exception {
        JobExecution jobExecution = mock(JobExecution.class);
        when(jobLauncher.run(eq(tondeuseJob), any(JobParameters.class))).thenReturn(jobExecution);

        tondeuseApplication = new TondeuseApplication(jobLauncher, tondeuseJob);
        tondeuseApplication.run("src/main/resources/input.txt");

        verify(jobLauncher, times(1)).run(eq(tondeuseJob), any(JobParameters.class));
    }

    @Test
    public void testRunWithNoArguments() throws Exception {
        JobExecution jobExecution = mock(JobExecution.class);
        when(jobLauncher.run(eq(tondeuseJob), any(JobParameters.class))).thenReturn(jobExecution);

        tondeuseApplication = new TondeuseApplication(jobLauncher, tondeuseJob);

        try {
            tondeuseApplication.run();
        } catch (IllegalArgumentException ex) {
            assert (true);
        }

        verify(jobLauncher, times(0)).run(any(), any());
    }
}