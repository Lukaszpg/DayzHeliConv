package pl.husar.services;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import pl.husar.settings.Settings;

public class DrawingService {
    private BufferedImage image;

    public DrawingService() {
        initializeImage();
    }

    private void initializeImage() {
        try {
            image = ImageIO.read(new File(Settings.IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTextOnImage(String textToAdd, Float x, Float y, Float fontSize, Color color) {
        x = x * Settings.GRID_DIMENSION_IN_PIXELS.floatValue();
        y = y * Settings.GRID_DIMENSION_IN_PIXELS.floatValue();

        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(g2d.getFont().deriveFont(fontSize));
        g2d.setColor(color);
        g2d.drawString(textToAdd, x, y);
    }

    public void drawCircleOnImage(Double x, Double y, Color color) {
        Graphics2D g2d = image.createGraphics();
        x = x * Settings.GRID_DIMENSION_IN_PIXELS;
        y = y * Settings.GRID_DIMENSION_IN_PIXELS;

        g2d.setColor(color);
        g2d.fill(new Ellipse2D.Float(x.floatValue(), y.floatValue(), Settings.DOT_DIMENSION, Settings.DOT_DIMENSION));
        g2d.dispose();
    }

    public BufferedImage getImage() {
        return this.image;
    }
}
