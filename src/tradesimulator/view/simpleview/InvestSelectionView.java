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
 * Invest selection view class is used to present the invest selection class.
 */
public class InvestSelectionView extends JFrame implements ISimulatorView {

  private JButton investFixedBtn;
  private JButton investSpecifiedBtn;

  /**
   * Constructor of the invest selection.
   * @param caption the caption
   * @param controller the controller
   */
  public InvestSelectionView(String caption, IController<Stock> controller) {
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

    investFixedBtn = new JButton("Invest Fixed");
    investFixedBtn.setActionCommand("Invest Fixed Ratio Button");
    buttonPanel.add(investFixedBtn);

    investSpecifiedBtn = new JButton("Invest Specified");
    investSpecifiedBtn.setActionCommand("Invest Specified Ratio Button");
    buttonPanel.add(investSpecifiedBtn);

    this.add(buttonPanel, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    investFixedBtn.addActionListener(listener);
    investSpecifiedBtn.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
