package com.mowitnow.tondeuse.batch.items;

import com.mowitnow.tondeuse.model.Tondeuse;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

public class TondeuseItemWriter extends FlatFileItemWriter<Tondeuse> {

    public TondeuseItemWriter() {
        // Set the output file path
        this.setResource(new FileSystemResource("data/out/output.txt"));

        // Create and configure the DelimitedLineAggregator
        DelimitedLineAggregator<Tondeuse> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(" ");

        // Set a custom field extractor for Tondeuse fields
        lineAggregator.setFieldExtractor(tondeuse -> new String[]{
                String.valueOf(tondeuse.getX()),
                String.valueOf(tondeuse.getY()),
                String.valueOf(tondeuse.getOrientation())
        });

        this.setLineAggregator(lineAggregator);

    }

}
