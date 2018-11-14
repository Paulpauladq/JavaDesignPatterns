package tradesimulator.model.operation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import tradesimulator.model.portfolio.Portfolio;
import tradesimulator.model.tradingitem.Stock;

public class SimulatorModel implements SimulatorOperations<Stock>{

  private Map<String, Portfolio> portfolioMap;
  private static final String INIT_NAME = "init";
  private int portfSize;

  public SimulatorModel(int portfSize){
    this.portfSize = portfSize;
    portfolioMap = new HashMap<>();
    portfolioMap.put(INIT_NAME, new Portfolio(INIT_NAME));
  }

  public static class SimulatorBuilder implements SimulatorOperationsBuilder{

    protected int portfSize;

    protected static final int PORTF_SIZE_LIMIT = 16;

    public SimulatorBuilder(){

    }

    @Override
    public SimulatorOperationsBuilder portfolios(int p) throws IllegalArgumentException {
      if(p < 0){
        throw new IllegalArgumentException(ModelErrMess.PORTF_SIZE_CANT_BE_NEG.getMess());
      }
      if(p > PORTF_SIZE_LIMIT){
        throw new IllegalArgumentException(ModelErrMess.TOO_MUCH_PORTF.getMess());
      }
      this.portfSize = p;
      return this;
    }

    @Override
    public <Stock> SimulatorOperations<Stock> build() {
      return (SimulatorOperations<Stock>) new SimulatorModel(this.portfSize);
    }
  }

  @Override
  public void buy(String stockSymbol, int volume, LocalDate date, String portfolioName)
          throws IllegalArgumentException {
    //use api to pass in the value
    double dummyPrice = 100.0;
    if(!portfolioMap.containsKey(portfolioName)){
      throw new IllegalArgumentException(ModelErrMess.NO_MUCH_PORTF.getMess());
    }
    portfolioMap.get(portfolioName).addStock(stockSymbol, date, volume, dummyPrice);

  }


  /**
   * Create nre portfolios.
   * @param portfolioName
   * @throws IllegalArgumentException
   */
  @Override
  public void createPortfolio(String portfolioName) throws IllegalArgumentException {
    if(portfolioMap.size() == portfSize){
      throw new IllegalArgumentException(ModelErrMess.TOO_MUCH_PORTF.getMess());
    }

    if(portfolioMap.containsKey(portfolioName)){
      throw new IllegalArgumentException(ModelErrMess.PORTF_NAME_EXISTS.getMess());
    }

      portfolioMap.put(portfolioName, new Portfolio(portfolioName));
  }

  @Override
  public double getTotalCostBasis(LocalDate date) {
    return 0;
  }

  @Override
  public double getTotalValue(LocalDate date) {
    return 0;
  }

  @Override
  public String getPortfolioState(String portfolioName) throws IllegalArgumentException {
    if(!portfolioMap.containsKey(portfolioName)){
      throw new IllegalArgumentException(ModelErrMess.NO_MUCH_PORTF.getMess());
    }
    return portfolioMap.get(portfolioName).toString();
  }

  @Override
  public String getPortfolioLog(String portfolioName) throws IllegalArgumentException {
    if(!portfolioMap.containsKey(portfolioName)){
      throw new IllegalArgumentException(ModelErrMess.NO_MUCH_PORTF.getMess());
    }
    return portfolioMap.get(portfolioName).recordLog();
  }

  @Override
  public String getPortfolioList() {
    StringBuilder sb = new StringBuilder();
    for(String name : portfolioMap.keySet()){
     sb.append(name + "\n");
    }
    return sb.toString();
  }

  public static SimulatorOperationsBuilder getBuilder() {
    return new SimulatorBuilder();
  }


  public static void main(String []args){
//    SimulatorModel model = new SimulatorModel(3);
//    model.createPortfolio("ppt");
//    model.createPortfolio("yibao");
//
//    try{
//      model.createPortfolio("yibao");
//    }catch (IllegalArgumentException e){
//      System.out.println(e.getMessage());
//    }
//
//    System.out.println(model.getPortfolioState("yibao"));
//
//    model.buy("FUCK", 100,LocalDate.MAX, "yibao");
//    model.buy("ass", 100,LocalDate.of(2012,11,15), "yibao");
//    model.buy("ass", 1100,LocalDate.of(2012,11,15), "ppt");
//    model.buy("ass", 120,LocalDate.of(2012,11,15), "ppt");
//    model.buy("yibaozuimei", 100,LocalDate.of(2012,11,15), "ppt");
//    model.buy("jessie", 11,LocalDate.of(2012,11,15), "ppt");
//
//    System.out.println(model.getPortfolioList());
//    System.out.println(model.getPortfolioState("yibao"));
//    System.out.println(model.getPortfolioLog("yibao"));
//
//    System.out.println(model.getPortfolioState("ppt"));
//    System.out.println(model.getPortfolioLog("ppt"));
//
//    try{
//      model.createPortfolio("sdfdss");
//    }catch (IllegalArgumentException e){
//      System.out.println(e.getMessage());
//    }

    SimulatorOperations yibaoIdiot = SimulatorModel.getBuilder().portfolios(10).build();
    for(int i = 0 ; i < 9; i ++){
      yibaoIdiot.createPortfolio(String.valueOf(i));
    }

    System.out.println(yibaoIdiot.getPortfolioList());

  }
}
