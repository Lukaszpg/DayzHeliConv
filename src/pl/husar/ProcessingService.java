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
        addAppInfoToImage();
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
            image = ImageIO.read(new File(Settings.IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addAppInfoToImage() {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(g2d.getFont().deriveFont(230f));
        g2d.drawString("Processed with DayZ Heli Position Converter " + Settings.VERSION + " , Husar 2017.", 240.0f, 13168.0f);
    }

    private void parseSingleLineAndDraw(String line) {
        String[] array = line.split(",");
        Double x = Double.parseDouble(array[1]) / Settings.SINGLE_GRID_DIMENSIONS;
        Double y = Double.parseDouble(array[2]) / Settings.SINGLE_GRID_DIMENSIONS;
        y = Settings.MAX_GRIDS - y;

        drawOnImage(x, y);
    }

    private void drawOnImage(Double x, Double y) {
        Graphics2D g2d = image.createGraphics();
        x = x * Settings.GRID_DIMENSION_IN_PIXELS;
        y = y * Settings.GRID_DIMENSION_IN_PIXELS;

        g2d.setColor(Color.magenta);
        g2d.fill(new Ellipse2D.Float(x.floatValue(), y.floatValue(), Settings.DOT_DIMENSION, Settings.DOT_DIMENSION));
        g2d.dispose();
    }
}
