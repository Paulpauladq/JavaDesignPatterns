package tradesimulator;

import java.io.InputStreamReader;

import tradesimulator.controller.controller.IController;
import tradesimulator.controller.controller.SimulatorController;
import tradesimulator.model.operation.SimulatorModel;
import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

public class SimulatorDriver {
  /**
   * Main function to run this program.
   *
   * @param args args
   */
  public static void main(String[] args) {
    SimulatorOperations<Stock> model = SimulatorModel.getBuilder().portfolios(16).build();
    IController<Stock> controller1 =
            new SimulatorController(new InputStreamReader(System.in), System.out);
    controller1.execute(model);
  }
}
