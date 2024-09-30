package com.mowitnow.tondeuse.batch.items;

import com.mowitnow.tondeuse.mappers.FileDataMapper;
import com.mowitnow.tondeuse.model.Tondeuse;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TondeuseItemReader extends FlatFileItemReader<Tondeuse> {

    public TondeuseItemReader(String inputFile) {

        // Log the input file path for debugging
        System.out.println("Input file path: " + inputFile);

        if (inputFile == null) {
            throw new IllegalArgumentException("Input file path must not be null");
        }
        String coordonneesCoin = readFirstLine(inputFile);
        // Use the FlatFileItemReaderBuilder to configure the reader
        this.setName("tondeuseItemReader");
        this.setResource(new FileSystemResource(inputFile));// Set the resource using inputFile path
        this.setLinesToSkip(1);// Skip the header line
        this.setLineMapper(lineMapper(coordonneesCoin));
    }

    private String readFirstLine(String inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            return reader.readLine(); // Read the first line
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private LineMapper<Tondeuse> lineMapper(String coordonneesCoin) {
        DefaultLineMapper<Tondeuse> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(" ");
        tokenizer.setNames("x", "y", "orientation", "instructions");
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(field -> FileDataMapper.mapLine(field, coordonneesCoin));

        return lineMapper;
    }

}
