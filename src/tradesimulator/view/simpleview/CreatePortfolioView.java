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
 * Create PortfolioView Page is used to present the create portfolio page.
 */
public class CreatePortfolioView extends JFrame implements ISimulatorView {

  private JButton emptyPortfBtn;
  private JButton specifiesPortfBtn;

  /**
   * Constructor of the create portfolio page.
   * @param caption the caption
   * @param controller the controller
   */
  public CreatePortfolioView(String caption, IController<Stock> controller) {
    super(caption);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(450, 500));
    JPanel buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(350, 420));

    GridLayout gridLayout = new GridLayout(2, 1);
    gridLayout.setHgap(100);
    gridLayout.setVgap(100);
    buttonPanel.setLayout(gridLayout);
    buttonPanel.setBorder(new EmptyBorder(new Insets(100, 80, 100, 80)));

    emptyPortfBtn = new JButton("Create Empty");
    emptyPortfBtn.setActionCommand("Create Empty Portfolio Button");
    buttonPanel.add(emptyPortfBtn);

    specifiesPortfBtn = new JButton("Create Specified");
    specifiesPortfBtn.setActionCommand("Create Specified Portfolio Button");
    buttonPanel.add(specifiesPortfBtn);

    this.add(buttonPanel, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    emptyPortfBtn.addActionListener(listener);
    specifiesPortfBtn.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
