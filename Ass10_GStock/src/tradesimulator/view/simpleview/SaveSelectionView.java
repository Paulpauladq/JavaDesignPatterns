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
 * Save selection view class is used to present the invest selection class.
 */
public class SaveSelectionView extends JFrame implements ISimulatorView {

  private JButton savePortfBtn;
  private JButton saveStrategyBtn;

  /**
   * Constructor of the save selection.
   * @param caption the caption
   * @param controller the controller
   */
  public SaveSelectionView(String caption, IController<Stock> controller) {
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

    savePortfBtn = new JButton("Save Portfolio");
    savePortfBtn.setActionCommand("Save Portfolio Button");
    buttonPanel.add(savePortfBtn);

    saveStrategyBtn = new JButton("Save Strategy");
    saveStrategyBtn.setActionCommand("Save Strategy Button");
    buttonPanel.add(saveStrategyBtn);

    this.add(buttonPanel, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    savePortfBtn.addActionListener(listener);
    saveStrategyBtn.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
