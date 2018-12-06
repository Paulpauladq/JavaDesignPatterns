package tradesimulator.view.textfieldview.query;

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
 * Query portfolio status view class.
 */
public class QueryPortfolioStatusView extends JFrame implements ITextFieldView {

  private JLabel status;
  private JTextField[] fields;
  private JButton queryBtn;
  private JButton backBtn;

  /**
   * Constructor of the query portfolio status view.
   *
   * @param caption    the caption String
   * @param controller the controller object
   */
  public QueryPortfolioStatusView(String caption, IController<Stock> controller) {
    super(caption);
    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String[] labels = {"Portfolio Name:"};
    TextForm form = new TextForm(labels);

    fields = form.getTextFields();
    JPanel buttonPanel = new JPanel();
    status = new JLabel();

    JPanel statusPanel = new JPanel();

    queryBtn = new JButton("Query");
    queryBtn.setActionCommand("Query Portfolio Status Operation Button");

    backBtn = new JButton("Back");
    backBtn.setActionCommand("Query Portfolio Status Operation Back Button");
    status = new JLabel("");

    statusPanel.add(status);
    buttonPanel.add(queryBtn, BorderLayout.SOUTH);
    buttonPanel.add(backBtn, BorderLayout.SOUTH);

    this.getContentPane().add(form, BorderLayout.NORTH);
    this.getContentPane().add(statusPanel, BorderLayout.CENTER);
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
    mess = mess.replaceAll("\\n", "<br/>");
    mess = "<html>" + mess + "</html>";
    status.setText(mess);
  }


  @Override
  public void addActionListener(ActionListener listener) {
    queryBtn.addActionListener(listener);
    backBtn.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
