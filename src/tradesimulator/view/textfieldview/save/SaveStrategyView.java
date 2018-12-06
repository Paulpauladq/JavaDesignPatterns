package tradesimulator.view.textfieldview.save;

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
 * Save View is used to save.
 */
public class SaveStrategyView extends JFrame implements ITextFieldView {

  private JTextField[] fields;
  private JButton saveBtn;
  private JButton backBtn;
  private JLabel status;

  /**
   * Construct the save view.
   *
   * @param caption    the caption String
   * @param controller the controller object
   */
  public SaveStrategyView(String caption, IController<Stock> controller) {
    super(caption);
    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String[] labels = {"Strategy Name:", "Money:", "Begin Date(YYYY-MM-DD):",
        "End Date(YYYY-MM-DD):", "Interval(Days):",
        "Stock1:", "Ratio1:", "Stock2:", "Ratio2:", "Stock3:", "Ratio3:", "Stock4:",
        "Ratio4:", "Stock5:", "Ratio5:"};

    TextForm form = new TextForm(labels);

    fields = form.getTextFields();
    JPanel buttonPanel = new JPanel();
    status = new JLabel();

    saveBtn = new JButton("Save");
    saveBtn.setActionCommand("Save Strategy Operation Button");
    backBtn = new JButton("Back");
    backBtn.setActionCommand("Save Strategy Operation Back Button");
    status = new JLabel("");

    buttonPanel.add(status, BorderLayout.NORTH);
    buttonPanel.add(saveBtn, BorderLayout.SOUTH);
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
    saveBtn.addActionListener(listener);
    backBtn.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}

