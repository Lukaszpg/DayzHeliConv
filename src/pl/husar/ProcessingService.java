package pl.husar;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

public class ProcessingService {

    private BufferedImage image;
    private FileService fileService;

    public ProcessingService() {
        initializeFileService();
        initializeImage();
    }

    private void initializeFileService() {
        fileService = new FileService();
    }

    public void execute() {
        getDataAndParse();
        saveProcessedMap();
    }

    private void getDataAndParse() {
        Stream<String> stream = fileService.readDataFile();

        if(stream != null) {
            stream.forEach(this::parseSingleLineAndDraw);
        }
    }

    private void saveProcessedMap() {
        fileService.saveFile(image);
    }

    private void initializeImage() {
        try {
            image = ImageIO.read(new File(Settings.imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseSingleLineAndDraw(String line) {
        String[] array = line.split(",");
        Double x = Double.parseDouble(array[1]) / Settings.singleGridDimensions;
        Double y = Double.parseDouble(array[2]) / Settings.singleGridDimensions;
        y = Settings.maxGrids - y;

        drawOnImage(x, y);
    }

    private void drawOnImage(Double x, Double y) {
        Graphics2D g2d = image.createGraphics();
        x = x * Settings.gridDimensionInPixels;
        y = y * Settings.gridDimensionInPixels;

        g2d.setColor(Color.magenta);
        g2d.fill(new Ellipse2D.Float(x.floatValue(), y.floatValue(), Settings.dotDimension, Settings.dotDimension));
        g2d.dispose();
    }
}
