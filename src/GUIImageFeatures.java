import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Represents the GUIImageFeatures class which extends the JFrame an implements Action and Item
 * Listener. This class creates the image features in the constructor. This opens the main image
 * processor in a window.
 */
public class GUIImageFeatures extends JFrame implements ActionListener, ItemListener {

  private final IModel model;

  private final JPanel histogramPanel;

  private final JLabel imageLabel;

  private final JComboBox<String> imagePackage;
  private final JPanel cardPanel;
  private final JTextField sText;

  private int curValue = 0;

  /**
   * Represents the constructor which setups up the GUI.
   */
  public GUIImageFeatures() {
    model = new ImageModel();

    setTitle("GRIME");
    Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) Math.round(sc.getWidth() / 2);
    int height = (int) Math.round(sc.getHeight() / 2);
    setSize(width, height);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    add(mainPanel);

    JPanel mainPanel1 = new JPanel();
    mainPanel1.setLayout(new BorderLayout());
    mainPanel.add(mainPanel1, BorderLayout.NORTH);

    JPanel mainPanel2 = new JPanel();
    mainPanel2.setLayout(new BorderLayout());
    mainPanel.add(mainPanel2, BorderLayout.CENTER);

    histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    mainPanel1.add(histogramPanel, BorderLayout.EAST);

    JPanel imagePanel = new JPanel(new CardLayout());
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon());
    JScrollPane imageScrollPanel = new JScrollPane(imageLabel);
    mainPanel2.add(imageScrollPanel, BorderLayout.CENTER);

    JPanel menuPanel = new JPanel();
    menuPanel.setLayout(new FlowLayout());
    menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
    mainPanel1.add(menuPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Options"));

    JButton loadButton = new JButton("LOAD");
    loadButton.setActionCommand("LOAD");
    loadButton.addActionListener(this);
    buttonPanel.add(loadButton);
    JButton saveButton = new JButton("SAVE");
    saveButton.setActionCommand("SAVE");
    saveButton.addActionListener(this);
    buttonPanel.add(saveButton);
    menuPanel.add(buttonPanel);

    JPanel imagePackagePanel = new JPanel();
    imagePackagePanel.setLayout(new FlowLayout());
    imagePackagePanel.setBorder(BorderFactory.createTitledBorder("Image Package"));
    imagePackage = new JComboBox<>();
    imagePackage.setActionCommand("Image");
    imagePackage.addActionListener(this);
    imagePackagePanel.add(imagePackage);
    menuPanel.add(imagePackagePanel);

    JPanel optionsPanel = new JPanel();
    optionsPanel.setLayout(new FlowLayout());
    optionsPanel.setBorder(BorderFactory.createTitledBorder("Option"));

    JPanel cardOptionsPanel = new JPanel();
    String[] comboBoxItems = {"Filter", "Adjust", "Shift", "Flip"};
    JComboBox<String> comboBox = new JComboBox<>(comboBoxItems);
    comboBox.setActionCommand("cardOptions");
    comboBox.addActionListener(this);
    comboBox.setEditable(false);
    comboBox.addItemListener(this);
    cardOptionsPanel.add(comboBox);

    cardPanel = new JPanel(new CardLayout());
    JPanel filterCard = new JPanel();
    JButton fRed = new JButton("Red");
    filterCard.add(fRed);
    fRed.setActionCommand("fRed");
    fRed.addActionListener(this);
    JButton fBlue = new JButton("Blue");
    filterCard.add(fBlue);
    fBlue.setActionCommand("fBlue");
    fBlue.addActionListener(this);
    JButton fGreen = new JButton("Green");
    filterCard.add(fGreen);
    fGreen.setActionCommand("fGreen");
    fGreen.addActionListener(this);
    JButton fValue = new JButton("Value");
    filterCard.add(fValue);
    fValue.setActionCommand("fValue");
    fValue.addActionListener(this);
    JButton fLuma = new JButton("Luma");
    filterCard.add(fLuma);
    fLuma.setActionCommand("fLuma");
    fLuma.addActionListener(this);
    JButton fIntensity = new JButton("Intensity");
    filterCard.add(fIntensity);
    fIntensity.setActionCommand("fIntensity");
    fIntensity.addActionListener(this);
    JButton fSepia = new JButton("Sepia");
    filterCard.add(fSepia);
    fSepia.setActionCommand("fSepia");
    fSepia.addActionListener(this);

    JPanel adjustCard = new JPanel();
    JButton aBlur = new JButton("Blur");
    adjustCard.add(aBlur);
    aBlur.setActionCommand("aBlur");
    aBlur.addActionListener(this);
    JButton aSharpen = new JButton("Sharpen");
    adjustCard.add(aSharpen);
    aSharpen.setActionCommand("aSharpen");
    aSharpen.addActionListener(this);

    JPanel shiftCard = new JPanel();
    JButton sBrighten = new JButton("Brighten");
    shiftCard.add(sBrighten);
    sBrighten.setActionCommand("sBrighten");
    sBrighten.addActionListener(this);
    JButton sDarken = new JButton("Darken");
    shiftCard.add(sDarken);
    sDarken.setActionCommand("sDarken");
    sDarken.addActionListener(this);
    shiftCard.add(new JLabel(" Integer Amount: "));
    sText = new JTextField("", 10);
    shiftCard.add(sText);
    JButton sEnter = new JButton("Enter");
    sEnter.setBorder(BorderFactory.createTitledBorder(""));
    sEnter.addActionListener(this);
    sEnter.setActionCommand("sEnter");
    shiftCard.add(sEnter);

    JPanel flipCard = new JPanel();
    JButton fHorizontal = new JButton("Horizontal");
    flipCard.add(fHorizontal);
    fHorizontal.setActionCommand("fHorizontal");
    fHorizontal.addActionListener(this);
    JButton fVertical = new JButton("Vertical");
    flipCard.add(fVertical);
    fVertical.setActionCommand("fVertical");
    fVertical.addActionListener(this);

    cardPanel.add(filterCard, "Filter");
    cardPanel.add(adjustCard, "Adjust");
    cardPanel.add(shiftCard, "Shift");
    cardPanel.add(flipCard, "Flip");

    optionsPanel.add(cardOptionsPanel);
    optionsPanel.add(cardPanel);
    menuPanel.add(optionsPanel);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "LOAD": {
        try {
          final JFileChooser fchooser = new JFileChooser(".");
          FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "Supported Files", "jpg", "png", "bmp", "ppm");
          fchooser.setFileFilter(filter);
          int retvalue = fchooser.showOpenDialog(GUIImageFeatures.this);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            String s = JOptionPane.showInputDialog(GUIImageFeatures.this,
                "Apply a name to the Image Data.");
            model.loadImage(f.getAbsolutePath(), s);
            imagePackage.addItem(s);
            imageLabel.setIcon(new ImageIcon(
                model.returnBuffer(model.getImage((String) imagePackage.getSelectedItem()))));
          }
        } catch (NullPointerException npe) {
          JOptionPane.showMessageDialog(GUIImageFeatures.this,
              "File selected is not supported.",
              "Error", JOptionPane.WARNING_MESSAGE);
        }
        break;
      }
      case "SAVE": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(GUIImageFeatures.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          model.saveImage(f.getAbsolutePath(),
              model.getImage((String) imagePackage.getSelectedItem()));
        }
        break;
      }
      case "Image": {
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(
                model.getImage((String) imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
        histogramPanel.add(
            new ImageHistogram(model.getImage((String) imagePackage.getSelectedItem())));
        break;
      }
      case "fRed": {
        model.addImage((String) imagePackage.getSelectedItem(), model.getImage((String)
            imagePackage.getSelectedItem()).filter("red"));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(
            model.getImage((String) imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
        histogramPanel.add(
            new ImageHistogram(model.getImage((String) imagePackage.getSelectedItem())));
        break;
      }
      case "fBlue": {
        model.addImage((String) imagePackage.getSelectedItem(),
            model.getImage((String) imagePackage.getSelectedItem()).filter("blue"));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(
            model.getImage((String) imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
        histogramPanel.add(
            new ImageHistogram(model.getImage((String) imagePackage.getSelectedItem())));
        break;
      }
      case "fGreen": {
        model.addImage((String) imagePackage.getSelectedItem(),
            model.getImage((String) imagePackage.getSelectedItem()).filter("green"));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(new ImageHistogram(model.getImage((String)
            imagePackage.getSelectedItem())));
        break;
      }
      case "fValue": {
        model.addImage((String) imagePackage.getSelectedItem(),
            model.getImage((String) imagePackage.getSelectedItem()).filter("value"));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(new ImageHistogram(model.getImage((String)
            imagePackage.getSelectedItem())));
        break;
      }
      case "fLuma": {
        model.addImage((String) imagePackage.getSelectedItem(),
            model.getImage((String) imagePackage.getSelectedItem()).filter("luma"));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(
            new ImageHistogram(model.getImage((String) imagePackage.getSelectedItem())));
        break;
      }
      case "fIntensity": {
        model.addImage((String) imagePackage.getSelectedItem(), model.getImage((String)
            imagePackage.getSelectedItem()).filter("intensity"));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(
            new ImageHistogram(model.getImage((String) imagePackage.getSelectedItem())));
        break;
      }
      case "fSepia": {
        model.addImage((String) imagePackage.getSelectedItem(),
            model.getImage((String) imagePackage.getSelectedItem()).filter("sepia"));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(new ImageHistogram(model.getImage((String)
            imagePackage.getSelectedItem())));
        break;
      }
      case "aBlur": {
        model.addImage((String) imagePackage.getSelectedItem(),
            model.getImage((String) imagePackage.getSelectedItem()).adjust("blur"));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(new ImageHistogram(model.getImage((String)
            imagePackage.getSelectedItem())));
        break;
      }
      case "aSharpen": {
        model.addImage((String) imagePackage.getSelectedItem(), model.getImage((String)
            imagePackage.getSelectedItem()).adjust("sharpen"));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(new ImageHistogram(model.getImage((String)
            imagePackage.getSelectedItem())));
        break;
      }
      case "sBrighten": {
        model.addImage((String) imagePackage.getSelectedItem(),
            model.getImage((String) imagePackage.getSelectedItem()).shiftPixel(curValue));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(new ImageHistogram(model.getImage((String)
            imagePackage.getSelectedItem())));
        break;
      }
      case "sDarken": {
        model.addImage((String) imagePackage.getSelectedItem(), model.getImage((String)
            imagePackage.getSelectedItem()).shiftPixel(-1 * curValue));
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(new
            ImageHistogram(model.getImage((String) imagePackage.getSelectedItem())));
        break;
      }
      case "sEnter": {
        try {
          curValue = Integer.parseInt(sText.getText());
          imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
              imagePackage.getSelectedItem()))));
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(GUIImageFeatures.this,
              "Value inputed is not an Integer.", "Error",
              JOptionPane.WARNING_MESSAGE);
        }
        break;
      }
      case "fHorizontal": {
        model.addImage((String) imagePackage.getSelectedItem(),
            model.getImage((String) imagePackage.getSelectedItem()).flipHorizontally());
        imageLabel.setIcon(new ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(new
            ImageHistogram(model.getImage((String) imagePackage.getSelectedItem())));
        break;
      }
      case "fVertical": {
        model.addImage((String) imagePackage.getSelectedItem(),
            model.getImage((String) imagePackage.getSelectedItem()).flipVertically());
        imageLabel.setIcon(new
            ImageIcon(model.returnBuffer(model.getImage((String)
            imagePackage.getSelectedItem()))));
        histogramPanel.removeAll();
        histogramPanel.add(new ImageHistogram(model.getImage((String)
            imagePackage.getSelectedItem())));
        break;
      }
      default:
        break;

    }

  }

  /**
   * Swaps the state of the options cardbox.
   *
   * @param e The state of the cardbox being observed.
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
    CardLayout cl = (CardLayout) (cardPanel.getLayout());
    cl.show(cardPanel, (String) e.getItem());

  }

}
