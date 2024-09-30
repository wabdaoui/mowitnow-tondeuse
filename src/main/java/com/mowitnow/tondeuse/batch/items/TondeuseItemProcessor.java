package com.mowitnow.tondeuse.batch.items;

import com.mowitnow.tondeuse.business.IDeplacementManager;
import com.mowitnow.tondeuse.model.Tondeuse;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TondeuseItemProcessor implements ItemProcessor<Tondeuse, Tondeuse> {

    @Autowired
    private IDeplacementManager deplacementManager;

    public Tondeuse process(Tondeuse tondeuse) {

        for (char instruction : tondeuse.getInstructions().toCharArray()) {
            if (instruction == 'A') {
                deplacementManager.moveForward(tondeuse);
            } else {
                deplacementManager.rotateTondeuse(tondeuse, instruction);
            }
        }
        System.out.println("tenseuse final postion is :" + tondeuse);
        return tondeuse;
    }

    public void setDeplacementManager(IDeplacementManager deplacementManager) {
        this.deplacementManager = deplacementManager;
    }

}
