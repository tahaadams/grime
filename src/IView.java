/**
 * Represents the IView interface.
 * Will show all methods being used in the ImageView class.
 */
public interface IView {

  /**
   * Represents the menu options of the console.
   * This is going to be shown in the console
   * whenever the program is running.
   */
  void showMenu();

  /**
   * Represents the menu options of the welcome load console.
   */
  void showWelcome();

  /**
   * Represents the menu options of the goodbye load console.
   */
  void showGoodbye();

  /**
   * Represents the command being operated.
   */
  void showCommand(String command);

  /**
   * Represents the naming of the types of filters, blurs, sharpens, etc.
   */
  void showNaming();

  /**
   * Represents the option type of the files.
   * @param inputType type of file name
   * @param options type of option name
   */
  void showOptions(String inputType, String options);

  /**
   * Represents the invalid entries of the methods.
   * It returns an error code based off of the user input and type.
   */
  void showError(String type, String message);
}

