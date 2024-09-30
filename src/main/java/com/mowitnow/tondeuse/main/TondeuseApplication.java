package com.mowitnow.tondeuse.main;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan(basePackages = "com.mowitnow.tondeuse")
public class TondeuseApplication implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job tondeuseJob;

    @Autowired
    public TondeuseApplication(JobLauncher jobLauncher, Job tondeuseJob) {
        this.jobLauncher = jobLauncher;
        this.tondeuseJob = tondeuseJob;
    }

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(TondeuseApplication.class, args)));
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 1) {
            throw new IllegalArgumentException("Merci de fournir le chemain pur le fichier d'entrée");
        }else {

            String inputFile = args[0];
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("inputFile", inputFile)
                    .addString("outputFile", "output")
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(tondeuseJob, jobParameters);
        }
    }
}
