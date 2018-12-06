package tradesimulator;

import java.io.InputStreamReader;

import tradesimulator.controller.controller.IController;
import tradesimulator.controller.controller.SimulatorController;
import tradesimulator.controller.controller.SimulatorGUIController;
import tradesimulator.model.operation.SimulatorModel;
import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;
import tradesimulator.view.simpleview.ISimulatorView;
import tradesimulator.view.simpleview.MainView;

public class SimulatorDriver {
  /**
   * Main function to run this program.
   *
   * @param args args
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      return;
    }
    SimulatorOperations<Stock> model = SimulatorModel.getBuilder().portfolios(16).build();
    String userArg = args[1];
    if (userArg.equals("console")) {
      IController<Stock> controller =
              new SimulatorController(new InputStreamReader(System.in), System.out, model);
      controller.execute();
    } else if (userArg.equals("gui")) {
      IController<Stock> guiController = new SimulatorGUIController(model);
      ISimulatorView view = new MainView("Trade Simulator", guiController);
      guiController.setView(view);
    } else {
      return;
    }
  }
}
