package tradesimulator.view.textfieldview.invest;

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
 * Invest fixed view is used to present the Invest Fixed View.
 */
public class InvestFixedView extends JFrame implements ITextFieldView {

  private JTextField[] fields;
  private JButton investBtn;
  private JButton backBtn;
  private JLabel status;

  /**
   * Constructor of the invest fixed view.
   *
   * @param caption    the String caption
   * @param controller the controller object
   */
  public InvestFixedView(String caption, IController<Stock> controller) {
    super(caption);
    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String[] labels = {"Date(YYYY_MM_DD):", "Money:", "Portfolio Name:"};

    TextForm form = new TextForm(labels);

    fields = form.getTextFields();
    JPanel buttonPanel = new JPanel();
    status = new JLabel();

    investBtn = new JButton("Invest");
    investBtn.setActionCommand("Invest Fixed Operation Button");
    backBtn = new JButton("Back");
    backBtn.setActionCommand("Invest Fixed Operation Back Button");
    status = new JLabel("");

    buttonPanel.add(status, BorderLayout.NORTH);
    buttonPanel.add(investBtn, BorderLayout.SOUTH);
    buttonPanel.add(backBtn, BorderLayout.SOUTH);

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
    investBtn.addActionListener(listener);
    backBtn.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
