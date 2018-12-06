package tradesimulator.controller.command;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * Set commission fee command class.
 */
public class SetCommissionFee implements TradeCommand<Stock> {

  private double commissionFee;

  /**
   * Constructor of set commission fee.
   *
   * @param commissionFee the commission fee
   */
  public SetCommissionFee(double commissionFee) {
    this.commissionFee = commissionFee;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    String str = String.format("Successfully set commission fee to $%.2f\n",
            this.commissionFee);
    m.setCommissionFee(this.commissionFee);
    return str;
  }
}
