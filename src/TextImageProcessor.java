import java.io.InputStreamReader;

/**
 * Represents the text image processor.
 */
public class TextImageProcessor {

  /**
   * Represents the main method which
   * runs the application of the image processor
   * in text form.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    IModel model = new ImageModel();
    IView view = new ImageView(System.out, model);
    IController controller = new ImageControl(model, view, new InputStreamReader(System.in));
    controller.run();
  }
}