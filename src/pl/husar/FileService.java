package pl.husar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

public class FileService {

    public Stream<String> readDataFile() {
        try {
            return Files.lines(Paths.get(Settings.dataFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveFile(BufferedImage image) {
        try {
            File outputFile = new File(Settings.outputFilePath);
            ImageIO.write(image, Settings.mapFileFormat, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
