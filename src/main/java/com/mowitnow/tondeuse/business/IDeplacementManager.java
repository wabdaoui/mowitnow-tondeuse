package com.mowitnow.tondeuse.business;

import com.mowitnow.tondeuse.model.Tondeuse;

public interface IDeplacementManager {
    void moveForward(Tondeuse tondeuse);

    void rotateTondeuse(Tondeuse tondeuse, char direction);
}
