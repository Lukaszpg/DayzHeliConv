package pl.husar.settings;

public class Settings {

    public static final Double MAX_GRIDS = 153.0;
    public static final Double MAP_DIMENSION = 15360.0;
    public static final Double GRID_DIMENSION_IN_PIXELS = 90.0;
    public static final Double SINGLE_GRID_DIMENSIONS = MAP_DIMENSION / MAX_GRIDS;
    public static final String DATA_FILE_PATH = "C://Dayz//data.txt";
    public static final String IMAGE_PATH = "C://Dayz//map.jpg";
    public static final String OUTPUT_FILE_PATH = "C://Dayz//output.jpg";
    public static final String MAP_FILE_FORMAT = "jpg";
    public static final Float DOT_DIMENSION = 75.0f;
    public static final String VERSION = "0.5 beta";

    public class Texts {
        public static final String AUTHOR = "Processed with DayZ Heli Position Converter " + Settings.VERSION + ", Husar 2017.";
        public static final String BASE = "Baza";
    }

    public static class Positions {
        public static final Float AUTHOR_X = 2.0f;
        public static final Float AUTHOR_Y = 146.0f;
        public static final Double BASE_X = 76.0;
        public static final Double BASE_Y = 12.0;
    }

    public static class FontSizes {
        public static final Float AUTHOR_SIZE = 230f;
        public static final Float BASE_SIZE = 100f;
    }
}
