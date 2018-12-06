package tradesimulator.view.textfieldview.create;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tradesimulator.controller.controller.IController;
import tradesimulator.model.tradingitem.Stock;
import tradesimulator.view.textfieldview.ITextFieldView;
import tradesimulator.view.textform.TextForm;

/**
 * Create empty portfolio view is used to present the create empty portfolio view.
 */
public class CreateEmptyPortfolioView extends JFrame implements ITextFieldView {

  private JLabel status;
  private JTextField[] fields;
  private JButton createButton;
  private JButton backButton;

  /**
   * Create empty portfolio view constructor.
   * @param caption the caption String
   * @param controller the controller object
   */
  public CreateEmptyPortfolioView(String caption, IController<Stock> controller) {
    super(caption);
    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String[] labels = {"Portfolio Name:"};
    TextForm form = new TextForm(labels);

    fields = form.getTextFields();
    JPanel buttonPanel = new JPanel();
    status = new JLabel();

    createButton = new JButton("Create");
    createButton.setActionCommand("Create Empty Portfolio Operation Button");

    backButton = new JButton("Back");
    backButton.setActionCommand("Create Empty Portfolio Operation Back Button");
    status = new JLabel("");

    buttonPanel.add(status, BorderLayout.NORTH);
    buttonPanel.add(createButton, BorderLayout.SOUTH);
    buttonPanel.add(backButton, BorderLayout.SOUTH);

    this.getContentPane().add(form, BorderLayout.NORTH);
    this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setVisible(true);
  }

  @Override
  public String getInputString() {
    StringBuilder sb = new StringBuilder();
    for (JTextField field : fields) {
      sb.append(field.getText());
    }
    return sb.toString();
  }

  @Override
  public void clearInputString() {
    for (JTextField field : fields) {
      field.setText("");
    }
  }

  @Override
  public void setStatus(String mess) {
    status.setText(mess);
  }


  @Override
  public void addActionListener(ActionListener listener) {
    createButton.addActionListener(listener);
    backButton.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
