package com.mowitnow.tondeuse.business;

import com.mowitnow.tondeuse.model.Tondeuse;
import org.springframework.stereotype.Component;

@Component("deplacementManager")
public class DeplacementManager implements IDeplacementManager {

    @Override
    public void moveForward(Tondeuse tondeuse) {
        switch (tondeuse.getOrientation()) {

            case 'N':
                if (tondeuse.getY() < tondeuse.getPelouse().getTopRightY()) {
                    tondeuse.setY(tondeuse.getY() + 1);
                }
                break;
            case 'E':
                if (tondeuse.getX() < tondeuse.getPelouse().getTopRightX()) {
                    tondeuse.setX(tondeuse.getX() + 1);
                }
                break;
            case 'S':
                if (tondeuse.getY() > 0) {
                    tondeuse.setY(tondeuse.getY() - 1);
                }
                break;
            case 'W':
                if (tondeuse.getX() > 0) {
                    tondeuse.setX(tondeuse.getX() - 1);
                }
                break;
        }
    }

    @Override
    public void rotateTondeuse(Tondeuse tondeuse, char direction) {
        if (direction == 'D') {
            switch (tondeuse.getOrientation()) {
                case 'N':
                    tondeuse.setOrientation('E');
                    break;
                case 'E':
                    tondeuse.setOrientation('S');
                    break;
                case 'S':
                    tondeuse.setOrientation('W');
                    break;
                case 'W':
                    tondeuse.setOrientation('N');
                    break;
            }
        } else if (direction == 'G') {
            switch (tondeuse.getOrientation()) {
                case 'N':
                    tondeuse.setOrientation('W');
                    break;
                case 'E':
                    tondeuse.setOrientation('N');
                    break;
                case 'S':
                    tondeuse.setOrientation('E');
                    break;
                case 'W':
                    tondeuse.setOrientation('S');
                    break;
            }
        }
    }
}
