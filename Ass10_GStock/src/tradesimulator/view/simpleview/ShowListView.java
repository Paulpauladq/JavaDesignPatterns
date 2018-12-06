package tradesimulator.view.simpleview;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import tradesimulator.controller.controller.IController;

import tradesimulator.model.tradingitem.Stock;

/**
 * Show list class is used to present the show list page.
 */
public class ShowListView extends JFrame implements ISimulatorView {

  private JButton backButton;

  /**
   * Constructor of the show list view.
   *
   * @param caption    the caption String
   * @param list       the String list
   * @param controller the controller object
   */
  public ShowListView(String caption, String list, IController<Stock> controller) {
    super(caption);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(450, 500));
    JPanel listPanel = new JPanel();

    list = list.replaceAll("\\n", "<br/>");
    list = "<html>" + list + "</html>";

    JLabel display = new JLabel(list);
    listPanel.add(display, BorderLayout.CENTER);

    backButton = new JButton("Back");
    backButton.setActionCommand("Show List Back Button");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(backButton, BorderLayout.CENTER);

    this.add(listPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
    setVisible(true);
    pack();
  }

  @Override
  public void addActionListener(ActionListener listener) {
    backButton.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
