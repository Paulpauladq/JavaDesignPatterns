package tradesimulator.view.textform;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Text form class is used to present the text form in standard format.
 */
public class TextForm extends JPanel {

  private JTextField[] textFields;

  /**
   * Constructor of the text form class.
   *
   * @param labels the text labels
   */
  public TextForm(String[] labels) {
    super(new BorderLayout());
    JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
    JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
    add(labelPanel, BorderLayout.WEST);
    add(fieldPanel, BorderLayout.CENTER);
    textFields = new JTextField[labels.length];

    for (int i = 0; i < labels.length; i++) {
      textFields[i] = new JTextField();
      textFields[i].setColumns(15);

      JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
      lab.setLabelFor(textFields[i]);

      labelPanel.add(lab);
      JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
      p.add(textFields[i]);
      fieldPanel.add(p);
    }
  }

  /**
   * getTextFields() method is used to get the text fields.
   *
   * @return the text field array
   */
  public JTextField[] getTextFields() {
    return textFields;
  }
}
