package com.mowitnow.tondeuse.batch.config;


import com.mowitnow.tondeuse.batch.items.TondeuseItemProcessor;
import com.mowitnow.tondeuse.batch.items.TondeuseItemReader;
import com.mowitnow.tondeuse.batch.items.TondeuseItemWriter;
import com.mowitnow.tondeuse.model.Tondeuse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class TondeuseJobConfig {

    @Autowired
    TondeuseItemProcessor tondeuseItemProcessor;

    @Bean
    public Job tondeuseJob(Step processTondeuseStep, JobBuilderFactory jobBuilderFactory) {
        return jobBuilderFactory.get("tondeuseJob")
                .incrementer(new RunIdIncrementer())
                .start(processTondeuseStep)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Tondeuse> tondeuseItemReader(@Value("#{jobParameters['inputFile']}") String inputFile) {
        return new TondeuseItemReader(inputFile);
    }

    @Bean
    public FlatFileItemWriter<Tondeuse> tondeuseItemWriter() {
        return new TondeuseItemWriter();
    }


    @Bean
    public Step processTondeusesStep(StepBuilderFactory stepBuilderFactory,
                                     ItemReader<Tondeuse> tondeuseItemReader,
                                     ItemWriter<Tondeuse> tondeuseItemWriter) {
        return stepBuilderFactory.get("processTondeusesStep")
                .<Tondeuse, Tondeuse>chunk(1)
                .reader(tondeuseItemReader)
                .processor(tondeuseItemProcessor)
                .writer(tondeuseItemWriter)
                .build();
    }

    public void setTondeuseItemProcessor(TondeuseItemProcessor tondeuseItemProcessor) {
        this.tondeuseItemProcessor = tondeuseItemProcessor;
    }
}
