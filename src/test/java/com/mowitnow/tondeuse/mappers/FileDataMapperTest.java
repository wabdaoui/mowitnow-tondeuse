package com.mowitnow.tondeuse.mappers;

import com.mowitnow.tondeuse.model.Tondeuse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.batch.item.file.transform.FieldSet;

class FileDataMapperTest {

    @Test
    void testMapLine() {
        // test data
        String[] vals = {"1", "2", "N", "GAGA"};
        String[] names = {"x", "y", "orientation", "instructions"};
        String coordonneesCoin = "5 5";
        FieldSet fs = new DefaultFieldSet(vals, names);

        Tondeuse expected = new Tondeuse();
        expected.setX(1);
        expected.setY(2);
        expected.setOrientation('N');
        expected.setInstructions("GAGA");

        // perform test
        Tondeuse actual = FileDataMapper.mapLine(fs, coordonneesCoin);

        // assertions
        Assertions.assertEquals(expected.getX(), actual.getX());
        Assertions.assertEquals(expected.getY(), actual.getY());
        Assertions.assertEquals(expected.getOrientation(), actual.getOrientation());
        Assertions.assertEquals(expected.getInstructions(), actual.getInstructions());
    }
}