package tradesimulator.view.textfieldview.retrieve;

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
 * Query value view presents the page of query value view.
 */
public class RetrPortfolioView extends JFrame implements ITextFieldView {

  private JLabel status;
  private JTextField[] fields;
  private JButton retrBtn;
  private JButton backBtn;
  private JButton checkBtn;

  /**
   * Constructor of the query value view.
   * @param caption the caption String
   * @param controller the controller object
   */
  public RetrPortfolioView(String caption, IController<Stock> controller) {
    super(caption);
    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String[] labels = {"File Name:", "New Portfolio Name:"};
    TextForm form = new TextForm(labels);

    fields = form.getTextFields();
    JPanel buttonPanel = new JPanel();
    status = new JLabel();

    retrBtn = new JButton("Retrieve");
    retrBtn.setActionCommand("Retrieve Portfolio Operation Button");

    backBtn = new JButton("Back");
    backBtn.setActionCommand("Retrieve Portfolio Operation Back Button");

    checkBtn = new JButton("Check");
    checkBtn.setActionCommand("Check Portfolio List Button");
    status = new JLabel("");

    JPanel checkPanel = new JPanel();
    checkPanel.add(checkBtn, BorderLayout.CENTER);

    buttonPanel.add(status, BorderLayout.NORTH);
    buttonPanel.add(retrBtn, BorderLayout.SOUTH);
    buttonPanel.add(backBtn, BorderLayout.SOUTH);

    this.getContentPane().add(form, BorderLayout.NORTH);
    this.getContentPane().add(checkPanel, BorderLayout.CENTER);
    this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setVisible(true);
  }

  @Override
  public String getInputString() {
    StringBuilder sb = new StringBuilder();
    for (JTextField field : fields) {
      sb.append(field.getText());
      sb.append(" ");
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
    retrBtn.addActionListener(listener);
    backBtn.addActionListener(listener);
    checkBtn.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}

