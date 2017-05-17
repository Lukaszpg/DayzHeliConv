package pl.husar.services;

import java.awt.*;
import java.util.stream.Stream;

import pl.husar.settings.Settings;

public class ProcessingService {

    private FileService fileService;
    private DrawingService drawingService;

    public ProcessingService() {
        initializeFileService();
        initializeDrawingService();
    }

    private void initializeFileService() {
        fileService = new FileService();
    }

    private void initializeDrawingService() { drawingService = new DrawingService(); }

    public void execute() {
        getVehicleDataAndParse();
        addParserInfoToMap();
        addBaseInfoToMap();
        saveProcessedMap();
    }

    private void getVehicleDataAndParse() {
        Stream<String> stream = fileService.readDataFile();

        if(stream != null) {
            stream.forEach(this::parseSingleLineAndDraw);
        }
    }

    private void parseSingleLineAndDraw(String line) {
        String[] array = line.split(",");
        Double x = Double.parseDouble(array[1]) / Settings.SINGLE_GRID_DIMENSIONS;
        Double y = Double.parseDouble(array[2]) / Settings.SINGLE_GRID_DIMENSIONS;
        y = Settings.MAX_GRIDS - y;

        drawingService.drawCircleOnImage(x, y, Color.magenta);
    }

    private void saveProcessedMap() {
        fileService.saveFile(drawingService.getImage());
    }

    private void addParserInfoToMap() {
        drawingService.drawTextOnImage(Settings.Texts.AUTHOR, Settings.Positions.AUTHOR_X, Settings.Positions.AUTHOR_Y, Settings.FontSizes.AUTHOR_SIZE, Color.white);
    }

    private void addBaseInfoToMap() {
        drawingService.drawCircleOnImage(Settings.Positions.BASE_X, Settings.Positions.BASE_Y, Color.red);
        drawingService.drawTextOnImage(Settings.Texts.BASE, Settings.Positions.BASE_X.floatValue(), Settings.Positions.BASE_Y.floatValue(), Settings.FontSizes.BASE_SIZE, Color.red);
    }
}
