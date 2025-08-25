import java.io.InputStreamReader;
import java.io.StringReader;
import javax.swing.JFrame;

/**
 * A class that holds the method that runs the application of the program.
 */
public class MasterImageProcessor {
  /**
   * Represents the main method which
   * runs the application of the image processor
   * in either GUI or text form.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    GUIImageFeatures.setDefaultLookAndFeelDecorated(false);
    GUIImageFeatures frame = new GUIImageFeatures();

    if (args.length == 0) {
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    } else if (args[0].equalsIgnoreCase("-file")) {
      IModel modelF = new ImageModel();
      IView viewF = new ImageView(System.out, modelF);
      IController controllerF = new ImageControl(modelF, viewF,
          new StringReader("file " + args[1]));
      controllerF.run();
    } else if (args[0].equalsIgnoreCase("-text")) {
      IModel model = new ImageModel();
      IView view = new ImageView(System.out, model);
      IController controller = new ImageControl(model, view, new InputStreamReader(System.in));
      controller.run();
    }

  }

}
