package tradesimulator.view.simpleview;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tradesimulator.controller.controller.IController;
import tradesimulator.model.tradingitem.Stock;

/**
 * Query selection view class is used to present the selection view.
 */
public class QuerySelectionView extends JFrame implements ISimulatorView {

  private JButton queryCostBtn;
  private JButton queryValueBtn;
  private JButton queryPortfStatusBtn;
  private JButton queryPortfLogBtn;

  /**
   * Constructor of the query selection view.
   * @param caption the caption
   * @param controller the controller object
   */
  public QuerySelectionView(String caption, IController<Stock> controller) {
    super(caption);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(450, 500));
    JPanel buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(350, 420));

    GridLayout gridLayout = new GridLayout(4, 1);
    gridLayout.setHgap(30);
    gridLayout.setVgap(30);
    buttonPanel.setLayout(gridLayout);
    buttonPanel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

    queryCostBtn = new JButton("Query Cost Basis");
    queryCostBtn.setActionCommand("Query Cost Basis Button");
    buttonPanel.add(queryCostBtn);

    queryValueBtn = new JButton("Query Value");
    queryValueBtn.setActionCommand("Query Value Button");
    buttonPanel.add(queryValueBtn);

    queryPortfStatusBtn = new JButton("Query Portfolio Status");
    queryPortfStatusBtn.setActionCommand("Query Portfolio Status Button");
    buttonPanel.add(queryPortfStatusBtn);

    queryPortfLogBtn = new JButton("Query Portfolio Log");
    queryPortfLogBtn.setActionCommand("Query Portfolio Log Button");
    buttonPanel.add(queryPortfLogBtn);

    this.add(buttonPanel, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    queryCostBtn.addActionListener(listener);
    queryValueBtn.addActionListener(listener);
    queryPortfStatusBtn.addActionListener(listener);
    queryPortfLogBtn.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
