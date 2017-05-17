package pl.husar.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

import pl.husar.settings.Settings;

public class FileService {

    public Stream<String> readDataFile() {
        try {
            return Files.lines(Paths.get(Settings.DATA_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveFile(BufferedImage image) {
        try {
            File outputFile = new File(Settings.OUTPUT_FILE_PATH);
            ImageIO.write(image, Settings.MAP_FILE_FORMAT, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
