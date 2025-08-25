import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


/**
 * Represents the class for the
 * histogram that is being seen on the GUI.
 */
public class ImageHistogram extends Canvas {

  private int[] histogram;
  private int[] histogramR;
  private int[] histogramG;
  private int[] histogramB;
  private double maxValue;

  /**
   * Constructor for the ImageHistogram takes in ImageData being used
   * draws histogram.
   *
   * @param data ImageData
   */
  public ImageHistogram(ImageData data) {
    setSize(new Dimension(256, 256));

    int intensity;
    int[] histogram = new int[256];
    int[] histogramR = new int[256];
    int[] histogramG = new int[256];
    int[] histogramB = new int[256];
    int maxValue = 1;
    RGB[][] cD = data.getColorData();
    for (int i = 0; i < data.getHeight(); i++) {
      for (int j = 0; j < data.getWidth(); j++) {
        histogramR[cD[i][j].getRed()]++;
        histogramG[cD[i][j].getGreen()]++;
        histogramB[cD[i][j].getBlue()]++;
        intensity = cD[i][j].intensityRBG().getRed();
        histogram[intensity]++;
      }
    }

    for (int i = 0; i < 256; i++) {
      maxValue = Math.max(maxValue, histogramR[i]);
      maxValue = Math.max(maxValue, histogramG[i]);
      maxValue = Math.max(maxValue, histogramB[i]);
      maxValue = Math.max(maxValue, histogram[i]);
    }

    this.histogram = histogram;
    this.histogramR = histogramR;
    this.histogramG = histogramG;
    this.histogramB = histogramB;
    this.maxValue = maxValue;

  }

  /**
   * Represents the paint of the graphics.
   *
   * @param g Graphics.
   */
  public void paint(Graphics g) {
    setBackground(Color.WHITE);
    for (int i = 0; i < 256; i++) {
      g.setColor(Color.RED);
      g.drawLine(i, 256, i, (int) Math.round(256 - ((histogramR[i] / maxValue)) * 256));
      g.setColor(Color.GREEN);
      g.drawLine(i, 256, i, (int) Math.round(256 - ((histogramG[i] / maxValue)) * 256));
      g.setColor(Color.BLUE);
      g.drawLine(i, 256, i, (int) Math.round(256 - ((histogramB[i] / maxValue)) * 256));
      g.setColor(Color.BLACK);
      g.drawLine(i, 256, i, (int) Math.round(256 - ((histogram[i] / maxValue)) * 256));
    }

  }

}
