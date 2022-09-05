package com.luucasor.goldenraspberryawards.readers;

import com.luucasor.goldenraspberryawards.GoldenRaspberryAwardsApplication;
import com.luucasor.goldenraspberryawards.dtos.MovieDTO;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class CSVReader {

    public List<?> getValues(String fileName, Character separator) throws FileNotFoundException, URISyntaxException {

        URL res = GoldenRaspberryAwardsApplication.class.getClassLoader().getResource(fileName);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();

        return new CsvToBeanBuilder(new FileReader(absolutePath))
                .withType(MovieDTO.class).withSeparator(separator).build().parse();
    }
}
