package com.mowitnow.tondeuse.mappers;

import com.mowitnow.tondeuse.model.Pelouse;
import com.mowitnow.tondeuse.model.Tondeuse;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public class FileDataMapper {
    public static Tondeuse mapLine(FieldSet fieldSet, String coordonneesCoin) {
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setX(fieldSet.readInt("x"));
        tondeuse.setY(fieldSet.readInt("y"));
        tondeuse.setOrientation(fieldSet.readString("orientation").charAt(0));
        tondeuse.setInstructions(fieldSet.readString("instructions"));
        int[] coordonneesXY = Arrays.stream(Objects.requireNonNull(StringUtils.split(coordonneesCoin, " "))).mapToInt(Integer::parseInt).toArray();
        Pelouse pelouse = new Pelouse(coordonneesXY[0], coordonneesXY[1]);
        tondeuse.setPelouse(pelouse);
        return tondeuse;
    }
}
