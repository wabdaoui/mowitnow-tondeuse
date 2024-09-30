package com.mowitnow.tondeuse.batch.config;

import com.mowitnow.tondeuse.batch.items.TondeuseItemProcessor;
import com.mowitnow.tondeuse.batch.items.TondeuseItemReader;
import com.mowitnow.tondeuse.batch.items.TondeuseItemWriter;
import com.mowitnow.tondeuse.model.Tondeuse;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = TondeuseJobConfig.class)
public class TondeuseJobConfigTest {

    @MockBean
    private JobBuilderFactory jobBuilderFactory;

    @MockBean
    private StepBuilderFactory stepBuilderFactory;

    @MockBean
    private TondeuseItemProcessor tondeuseItemProcessor;

    @MockBean
    private TondeuseItemReader tondeuseItemReader;

    @MockBean
    private TondeuseItemWriter tondeuseItemWriter;

    @Test
    public void testTondeuseJob() {

        TondeuseJobConfig tondeuseJobConfig = new TondeuseJobConfig();
        tondeuseJobConfig.setTondeuseItemProcessor(tondeuseItemProcessor);

        JobBuilder jobBuilder = mock(JobBuilder.class);
        SimpleJobBuilder mockSimpleJobBuilder = mock(SimpleJobBuilder.class);

        when(jobBuilderFactory.get(anyString())).thenReturn(jobBuilder);
        //when(jobBuilder.incrementer(any(JobParametersIncrementer.class))).thenReturn(mockSimpleJobBuilder);
        when(mockSimpleJobBuilder.start(any(Step.class))).thenReturn(mockSimpleJobBuilder);

        tondeuseJobConfig.tondeuseJob(mock(Step.class), jobBuilderFactory);

        verify(jobBuilderFactory, times(1)).get(any());
        verify(jobBuilder, times(1)).incrementer(any());
        verify(mockSimpleJobBuilder, times(1)).start(any(Step.class));
    }

    @Test
    void testProcessTondeusesStep() {
        TondeuseJobConfig tondeuseJobConfig = new TondeuseJobConfig();
        tondeuseJobConfig.setTondeuseItemProcessor(tondeuseItemProcessor);

        StepBuilder stepBuilder = mock(StepBuilder.class);
        SimpleStepBuilder simpleStepBuilder = mock(SimpleStepBuilder.class);

        when(stepBuilderFactory.get(anyString())).thenReturn(stepBuilder);
        //when(stepBuilder.chunk(anyInt())).thenReturn(simpleStepBuilder);
        when(simpleStepBuilder.reader(any(ItemReader.class))).thenReturn(simpleStepBuilder);
        //when(simpleStepBuilder.processor(any())).thenReturn(simpleStepBuilder);
        when(simpleStepBuilder.writer(any(ItemWriter.class))).thenReturn(simpleStepBuilder);

        tondeuseJobConfig.processTondeusesStep(stepBuilderFactory, tondeuseItemReader, tondeuseItemWriter);

        verify(stepBuilderFactory, times(1)).get(any());
        verify(stepBuilder, times(1)).chunk(any());
        verify(simpleStepBuilder, times(1)).reader(any());
        //verify(simpleStepBuilder, times(1)).processor(any());
        verify(simpleStepBuilder, times(1)).writer(any());
    }
}