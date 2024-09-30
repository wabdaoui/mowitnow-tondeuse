package com.mowitnow.tondeuse.batch.items;

import com.mowitnow.tondeuse.business.DeplacementManager;
import com.mowitnow.tondeuse.business.IDeplacementManager;
import com.mowitnow.tondeuse.model.Tondeuse;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = TondeuseItemProcessor.class)
public class TondeuseItemProcessorTest {

    @MockBean
    private DeplacementManager deplacementManager;

    @InjectMocks
    private TondeuseItemProcessor tondeuseItemProcessor;

    @BeforeEach
    void init() {
        tondeuseItemProcessor.setDeplacementManager(deplacementManager);
    }

    @Test
    public void process_moveForwardInstruction_shouldCallMoveForwardMethod() throws Exception {
        Tondeuse tondeuse = new Tondeuse(1, 1, 'N');
        tondeuse.setInstructions("A");
        tondeuseItemProcessor.process(tondeuse);
        verify(deplacementManager, times(1)).moveForward(tondeuse);
    }

    @Test
    public void process_rotateTondeuseInstruction_shouldCallRotateTondeuseMethod() throws Exception {
        Tondeuse tondeuse = new Tondeuse(1, 1, 'N');
        tondeuse.setInstructions("D");
        tondeuseItemProcessor.process(tondeuse);
        verify(deplacementManager, times(1)).rotateTondeuse(tondeuse, 'D');
    }

    @Test
    public void process_combinedInstructions_shouldCallRespectiveMethods() throws Exception {
        Tondeuse tondeuse = new Tondeuse(1, 1, 'N');
        tondeuse.setInstructions("AD");
        tondeuseItemProcessor.process(tondeuse);
        verify(deplacementManager, times(1)).moveForward(tondeuse);
        verify(deplacementManager, times(1)).rotateTondeuse(tondeuse, 'D');
    }
}