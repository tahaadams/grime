import javax.swing.JFrame;

/**
 * Represents the GUI image processor.
 */
public class GUIImageProcessor {

  /**
   * Represents the main method which
   * opens a window of the image processor
   * in GUI form.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    GUIImageFeatures.setDefaultLookAndFeelDecorated(false);
    GUIImageFeatures frame = new GUIImageFeatures();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

}
