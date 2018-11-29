package tradesimulator.controller.command;

import java.util.List;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * Create specified portfolio.
 */
public class CreateSpecified implements TradeCommand<Stock> {
  String portfolioName;
  List<String> symbolList;

  /**
   * Create specified portfolio.
   *
   * @param portfolioName portfolio name
   * @param symbolList    symbol list
   */
  public CreateSpecified(String portfolioName, List<String> symbolList) {
    this.portfolioName = portfolioName;
    this.symbolList = symbolList;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    StringBuilder sb = new StringBuilder();
    sb.append("Successfully created specified list ");
    sb.append(this.portfolioName);
    sb.append(" with stocks:\n");
    m.createSpecifiedPortfolio(this.symbolList, this.portfolioName);
    for (int i = 0; i < symbolList.size(); i++) {
      sb.append(" " + symbolList.get(i));
    }
    sb.append("\n");
    return sb.toString();
  }
}
