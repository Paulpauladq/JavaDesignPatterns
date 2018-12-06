package tradesimulator.view.simpleview;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import tradesimulator.controller.controller.IController;
import tradesimulator.controller.controller.SimulatorGUIController;
import tradesimulator.model.operation.SimulatorModel;
import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * Main View class is used to present the main view of the class.
 */
public class MainView extends JFrame implements ISimulatorView {

  private JButton showListButton;
  private JButton exitButton;
  private JButton createPortfButton;
  private JButton modifyPortfButton;
  private JButton investButton;
  private JButton queryButton;
  private JButton saveButton;
  private JButton retrieveButton;

  /**
   * Main view constructor is used to construct the main class.
   *
   * @param caption    caption of the view
   * @param controller controller object
   */
  public MainView(String caption, IController<Stock> controller) {
    super(caption);

    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel welcomePanel = new JPanel();
    welcomePanel.setPreferredSize(new Dimension(100, 50));

    JPanel buttonPanel = new JPanel();

    buttonPanel.setPreferredSize(new Dimension(350, 420));
    GridLayout gridLayout = new GridLayout(4, 2);
    gridLayout.setHgap(30);
    gridLayout.setVgap(30);
    buttonPanel.setLayout(gridLayout);

    showListButton = new JButton("Show Lists");
    showListButton.setActionCommand("Show List Button");
    buttonPanel.add(showListButton);

    createPortfButton = new JButton("Create Portfolio");
    createPortfButton.setActionCommand("Create Portfolio Button");
    buttonPanel.add(createPortfButton);

    modifyPortfButton = new JButton("Set Commission Fee");
    modifyPortfButton.setActionCommand("Set Commission Fee Button");
    buttonPanel.add(modifyPortfButton);

    investButton = new JButton("Invest");
    investButton.setActionCommand("Invest Button");
    buttonPanel.add(investButton);

    queryButton = new JButton("Query");
    queryButton.setActionCommand("Query Button");
    buttonPanel.add(queryButton);

    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save Button");
    buttonPanel.add(saveButton);

    retrieveButton = new JButton("Retrieve");
    retrieveButton.setActionCommand("Retrieve Button");
    buttonPanel.add(retrieveButton);

    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    buttonPanel.add(exitButton);

    buttonPanel.setBorder(new EmptyBorder(new Insets(10, 25, 30, 25)));
    JLabel welcome = welcome = new JLabel("WELCOME TO TRADE SIMULATOR SYSTEM!", JLabel.CENTER);
    welcome.setSize(200, 400);
    welcomePanel.add(welcome, BorderLayout.CENTER);

    this.getContentPane().add(welcomePanel, BorderLayout.BEFORE_FIRST_LINE);
    this.getContentPane().add(buttonPanel, BorderLayout.CENTER);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    exitButton.addActionListener(listener);
    showListButton.addActionListener(listener);
    createPortfButton.addActionListener(listener);
    modifyPortfButton.addActionListener(listener);
    investButton.addActionListener(listener);
    queryButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    retrieveButton.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * Main functions.
   * @param args the args
   */
  public static void main(String[] args) {
    SimulatorOperations<Stock> model = SimulatorModel.getBuilder().portfolios(16).build();
    IController<Stock> controller = new SimulatorGUIController(model);
    ISimulatorView view = new MainView("Trade Simulator", controller);
    controller.setView(view);
  }
}
