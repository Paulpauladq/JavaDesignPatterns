package tradesimulator.controller.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tradesimulator.controller.listener.ButtonListener;
import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;
import tradesimulator.view.textfieldview.create.CreateEmptyPortfolioView;
import tradesimulator.view.textfieldview.create.CreateSpecifiedPortfolioView;
import tradesimulator.view.textfieldview.invest.InvestFixedView;
import tradesimulator.view.textfieldview.invest.InvestSpecifiedView;
import tradesimulator.view.textfieldview.query.QueryCostBasisView;
import tradesimulator.view.textfieldview.query.QueryPortfolioLogView;
import tradesimulator.view.textfieldview.query.QueryPortfolioStatusView;
import tradesimulator.view.textfieldview.query.QueryValueView;
import tradesimulator.view.textfieldview.retrieve.RetrPortfolioView;
import tradesimulator.view.textfieldview.retrieve.RetrStrategyView;
import tradesimulator.view.textfieldview.save.SavePortfolioView;
import tradesimulator.view.textfieldview.save.SaveStrategyView;
import tradesimulator.view.textfieldview.set.SetCommissionFeeView;
import tradesimulator.view.simpleview.CreatePortfolioView;
import tradesimulator.view.simpleview.ISimulatorView;
import tradesimulator.view.textfieldview.ITextFieldView;
import tradesimulator.view.simpleview.InvestSelectionView;
import tradesimulator.view.simpleview.MainView;
import tradesimulator.view.simpleview.QuerySelectionView;
import tradesimulator.view.simpleview.RetrieveSelectionView;
import tradesimulator.view.simpleview.SaveSelectionView;
import tradesimulator.view.simpleview.ShowListView;

public class SimulatorGUIController implements IController<Stock> {

  private SimulatorOperations<Stock> model;
  private ISimulatorView mainView;
  private ISimulatorView showListView;
  private ISimulatorView createView;
  private ITextFieldView createEmptyView;
  private ITextFieldView createSpecifiedView;
  private ISimulatorView investSelectionView;
  private ITextFieldView investFixedView;
  private ITextFieldView investSpecifiedView;
  private ITextFieldView setCommissionFeeView;
  private ISimulatorView querySelectionView;
  private ITextFieldView queryCostBasisView;
  private ITextFieldView queryValueView;
  private ITextFieldView queryPortfStatusView;
  private ITextFieldView queryPortfLogView;
  private ISimulatorView saveSelectionView;
  private ISimulatorView retrSelectionView;
  private ITextFieldView savePortfView;
  private ITextFieldView saveStrategyView;
  private ITextFieldView retrPortfView;
  private ITextFieldView retrStrategyView;

  private static final String SYMBOL_REGEX = "[A-Za-z]+";
  private static final String MONEY_REGEX = "(([1-9][\\d]*|[0])\\.[\\d]*)|([1-9][\\d]*)";
  private static final String RATIO_REGEX = "[0]\\.[\\d]*|[1](\\.[0]*)*";
  private static final String VOLUME_REGEX = "[1-9][\\d]*";

  public SimulatorGUIController(SimulatorOperations<Stock> model) {
    this.model = model;
  }

  @Override
  public SimulatorOperations<Stock> getModel() {
    return model;
  }

  @Override
  public void setView(ISimulatorView view) {
    this.mainView = view;
    configureButtonListener();
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    //***********************Main************************
    buttonClickedMap.put("Show List Button", () -> {
      String tempList = model.getPortfolioList();
      showListView = (ISimulatorView) new ShowListView(
              "Show Portfolio List", tempList, this);
      showListView.addActionListener(buttonListener);
      ((JFrame) this.mainView).dispose();
    });

    //2
    buttonClickedMap.put("Create Portfolio Button", () -> {
      createView = new CreatePortfolioView("Create Portfolios", this);
      createView.addActionListener(buttonListener);
      ((JFrame) this.mainView).dispose();
    });

    //3
    buttonClickedMap.put("Set Commission Fee Button", () -> {
      setCommissionFeeView = new SetCommissionFeeView("Set Commission Fee", this);
      setCommissionFeeView.addActionListener(buttonListener);
      ((JFrame) this.mainView).dispose();
    });

    //4
    buttonClickedMap.put("Invest Button", () -> {
      investSelectionView = new InvestSelectionView("Invest Selection", this);
      investSelectionView.addActionListener(buttonListener);
      ((JFrame) this.mainView).dispose();
    });

    //5
    buttonClickedMap.put("Query Button", () -> {
      querySelectionView = new QuerySelectionView("Query Selection", this);
      querySelectionView.addActionListener(buttonListener);
      ((JFrame) this.mainView).dispose();
    });

    //6
    buttonClickedMap.put("Save Button", () -> {
      saveSelectionView = new SaveSelectionView("Query Selection", this);
      saveSelectionView.addActionListener(buttonListener);
      ((JFrame) this.mainView).dispose();
    });

    //7
    buttonClickedMap.put("Retrieve Button", () -> {
      retrSelectionView = new RetrieveSelectionView("Query Selection", this);
      retrSelectionView.addActionListener(buttonListener);
      ((JFrame) this.mainView).dispose();
    });

    //8
    buttonClickedMap.put("Exit Button", () -> {
      System.exit(0);
    });

    //**********************Show List Page*********************
    buttonClickedMap.put("Show List Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.showListView).dispose();
    });

    //**************************Create Empty******************************
    buttonClickedMap.put("Create Empty Portfolio Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.createEmptyView).dispose();
    });

    buttonClickedMap.put("Create Empty Portfolio Operation Button", () -> {
      String temp = createEmptyView.getInputString();
      try {
        model.createPortfolio(temp);
        createEmptyView.setStatus("Portfolio created successfully!");
        createEmptyView.clearInputString();
      } catch (IllegalArgumentException e) {
        createEmptyView.setStatus(e.getMessage());
        createEmptyView.clearInputString();
      }
    });

    //****************************Create Selection****************************
    buttonClickedMap.put("Create Specified Portfolio Button", () -> {
      createSpecifiedView = new CreateSpecifiedPortfolioView(
              "Create Specified Portfolios", this);
      createSpecifiedView.addActionListener(buttonListener);
      ((JFrame) this.createView).dispose();
    });

    buttonClickedMap.put("Create Empty Portfolio Button", () -> {
      createEmptyView = new CreateEmptyPortfolioView(
              "Create Empty Portfolios", this);
      createEmptyView.addActionListener(buttonListener);
      ((JFrame) this.createView).dispose();
    });

    //*******************************Create Specified *************************
    buttonClickedMap.put("Create Specified Portfolio Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.createSpecifiedView).dispose();
    });

    buttonClickedMap.put("Create Specified Portfolio Operation Button", () -> {
      String temp = createSpecifiedView.getInputString();
      String[] tokens = temp.split(" ");
      String name = tokens[0];
      List<String> stocks = new ArrayList<>();
      for (int i = 1; i < tokens.length; i++) {
        stocks.add(tokens[i]);
      }
      try {
        model.createSpecifiedPortfolio(stocks, name);
        createSpecifiedView.setStatus("Specified portfolio created successfully!");
        createSpecifiedView.clearInputString();
      } catch (IllegalArgumentException e) {
        createSpecifiedView.setStatus(e.getMessage());
        createSpecifiedView.clearInputString();
      }

    });

    //**************************Invest Selection*******************
    buttonClickedMap.put("Invest Fixed Ratio Button", () -> {
      investFixedView = new InvestFixedView("Invest Fixed Ratio", this);
      investFixedView.addActionListener(buttonListener);
      ((JFrame) this.investSelectionView).dispose();
    });

    buttonClickedMap.put("Invest Specified Ratio Button", () -> {
      investSpecifiedView = new InvestSpecifiedView("Invest Specified Ratio", this);
      investSpecifiedView.addActionListener(buttonListener);
      ((JFrame) this.investSelectionView).dispose();
    });

    //**************************Invest Fixed*******************
    buttonClickedMap.put("Invest Fixed Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.investFixedView).dispose();
    });

    buttonClickedMap.put("Invest Fixed Operation Button", () -> {
      String text = investFixedView.getInputString();
      String[] tokens = text.split(" ");
      String dateStr = tokens[0];
      String moneyStr = tokens[1];
      String stockStr = tokens[2];
      LocalDate date = LocalDate.now();
      double money = 0.0;
      DateTimeFormatter format =
              DateTimeFormatter.ofPattern("yyyy-MM-dd");
      try {
        date = LocalDate.parse(dateStr, format);
        if (!moneyStr.matches(MONEY_REGEX)) {
          investFixedView.setStatus(TransMess.INVALID_MONEY.getMess());
          investFixedView.clearInputString();
        } else if (!stockStr.matches(SYMBOL_REGEX)) {
          investFixedView.setStatus(TransMess.INVALID_STOCK_SYMBOL.getMess());
          investFixedView.clearInputString();
        } else {
          money = Double.parseDouble(moneyStr);
          try {
            model.investFixedAmount(date, money, stockStr);
            investFixedView.setStatus("Successfully invest fixed Amount!");
            investFixedView.clearInputString();
          } catch (IllegalArgumentException e) {
            investFixedView.setStatus(e.getMessage());
            investFixedView.clearInputString();
          }
        }
      } catch (DateTimeParseException e) {
        investFixedView.setStatus(TransMess.INVALID_DATE.getMess());
        investFixedView.clearInputString();
      }
    });

    //**************************Invest Specified*******************
    buttonClickedMap.put("Invest Specified Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.investSpecifiedView).dispose();
    });

    buttonClickedMap.put("Invest Specified Operation Button", () -> {
      String text = investSpecifiedView.getInputString();
      String[] tokens = text.split(" ");
      boolean moneyFlag = false;
      boolean stockFlag = false;

      Map<String, Double> ratioMap = new HashMap<>();

      double sum = 0.0;
      String dateStr = tokens[0];
      String moneyStr = tokens[1];
      String stockStr = tokens[2];
      for (int i = 3; i < tokens.length; i += 2) {
        if (!tokens[i].matches(SYMBOL_REGEX)) {
          stockFlag = true;
          break;
        }
        if (!tokens[i + 1].matches(RATIO_REGEX)) {
          moneyFlag = true;
          break;
        }
        ratioMap.put(tokens[i], Double.parseDouble(tokens[i + 1]));
      }
      for (double ratio : ratioMap.values()) {
        sum += ratio;
      }

      if (stockFlag) {
        investSpecifiedView.setStatus(TransMess.INVALID_STOCK_SYMBOL.getMess());
        investSpecifiedView.clearInputString();
      } else if (moneyFlag) {
        investSpecifiedView.setStatus(TransMess.INVALID_RATIO.getMess());
        investSpecifiedView.clearInputString();
      } else if (sum != 1) {
        investSpecifiedView.setStatus(TransMess.RATIO_SUM_DOESNT_EQUAL_ONE.getMess());
        investSpecifiedView.clearInputString();
      } else {
        LocalDate date = LocalDate.now();
        double money = 0.0;
        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
          date = LocalDate.parse(dateStr, format);
          if (!moneyStr.matches(MONEY_REGEX)) {
            investSpecifiedView.setStatus(TransMess.INVALID_MONEY.getMess());
            investSpecifiedView.clearInputString();
          } else if (!stockStr.matches(SYMBOL_REGEX)) {
            investSpecifiedView.setStatus(TransMess.INVALID_STOCK_SYMBOL.getMess());
            investSpecifiedView.clearInputString();
          } else {
            money = Double.parseDouble(moneyStr);
            try {
              model.investSpecifiedAmount(date, money, ratioMap, stockStr);
              investSpecifiedView.setStatus("Successfully invest specified amount!");
              investSpecifiedView.clearInputString();
            } catch (IllegalArgumentException e) {
              investSpecifiedView.setStatus(e.getMessage());
              investSpecifiedView.clearInputString();
            }
          }
        } catch (DateTimeParseException e) {
          investSpecifiedView.setStatus(TransMess.INVALID_DATE.getMess());
          investSpecifiedView.clearInputString();
        }
      }
    });

    //*********************Set Commission Fee***********************
    buttonClickedMap.put("Set Commission Fee Operation Button", () -> {

      String str = setCommissionFeeView.getInputString();
      if (str.matches(MONEY_REGEX)) {
        double fee = Double.parseDouble(str);
        try {
          model.setCommissionFee(fee);
          setCommissionFeeView.setStatus("Set Commission Fee to $" + fee + " Successfully!");
          setCommissionFeeView.clearInputString();
        } catch (IllegalArgumentException e) {
          setCommissionFeeView.setStatus(e.getMessage());
          setCommissionFeeView.clearInputString();
        }
      } else {
        setCommissionFeeView.setStatus(TransMess.INVALID_MONEY.getMess());
      }
    });

    buttonClickedMap.put("Set Commission Fee Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.setCommissionFeeView).dispose();
    });

    //*********************Query Selection************************
    buttonClickedMap.put("Query Cost Basis Button", () -> {
      queryCostBasisView = new QueryCostBasisView("Query Cost Basis", this);
      queryCostBasisView.addActionListener(buttonListener);
      ((JFrame) this.querySelectionView).dispose();
    });

    buttonClickedMap.put("Query Value Button", () -> {
      queryValueView = new QueryValueView("Query Value", this);
      queryValueView.addActionListener(buttonListener);
      ((JFrame) this.querySelectionView).dispose();
    });

    buttonClickedMap.put("Query Portfolio Status Button", () -> {
      queryPortfStatusView = new QueryPortfolioStatusView(
              "Query Portfolio Status", this);
      queryPortfStatusView.addActionListener(buttonListener);
      ((JFrame) this.querySelectionView).dispose();
    });

    buttonClickedMap.put("Query Portfolio Log Button", () -> {
      queryPortfLogView = new QueryPortfolioLogView(
              "Query Portfolio Log", this);
      queryPortfLogView.addActionListener(buttonListener);
      ((JFrame) this.querySelectionView).dispose();
    });

    //********************Query Cost******************************
    buttonClickedMap.put("Query Cost Basis Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.queryCostBasisView).dispose();
    });

    buttonClickedMap.put("Query Cost Basis Operation Button", () -> {
      String text = queryCostBasisView.getInputString();
      String[] arr = text.split(" ");
      String dateStr = arr[0];
      String name = arr[1];

      LocalDate date = LocalDate.now();
      DateTimeFormatter format =
              DateTimeFormatter.ofPattern("yyyy-MM-dd");
      try {
        date = LocalDate.parse(dateStr, format);
        try {
          double cost = model.getTotalCostBasis(date, name);
          queryCostBasisView.setStatus(String.valueOf(cost));
          queryCostBasisView.clearInputString();
        } catch (IllegalArgumentException e) {
          queryCostBasisView.setStatus(e.getMessage());
          queryCostBasisView.clearInputString();
        }

      } catch (DateTimeParseException e) {
        queryCostBasisView.setStatus(TransMess.INVALID_DATE.getMess());
        queryCostBasisView.clearInputString();
      }
    });

    //*********************Query Value****************************
    buttonClickedMap.put("Query Value Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.queryValueView).dispose();
    });

    buttonClickedMap.put("Query Value Operation Button", () -> {
      String text = queryValueView.getInputString();
      String[] arr = text.split(" ");
      String dateStr = arr[0];
      String name = arr[1];

      LocalDate date = LocalDate.now();
      DateTimeFormatter format =
              DateTimeFormatter.ofPattern("yyyy-MM-dd");
      try {
        date = LocalDate.parse(dateStr, format);
        try {
          double cost = model.getTotalValue(date, name);
          queryValueView.setStatus(String.valueOf(cost));
          queryValueView.clearInputString();
        } catch (IllegalArgumentException e) {
          queryValueView.setStatus(e.getMessage());
          queryValueView.clearInputString();
        }

      } catch (DateTimeParseException e) {
        queryValueView.setStatus(TransMess.INVALID_DATE.getMess());
        queryValueView.clearInputString();
      }
    });

    //*********************Query Portf Status****************************
    buttonClickedMap.put("Query Portfolio Status Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.queryPortfStatusView).dispose();
    });

    buttonClickedMap.put("Query Portfolio Status Operation Button", () -> {
      String name = queryPortfStatusView.getInputString();
      try {
        String status = model.getPortfolioState(name);
        queryPortfStatusView.setStatus(status);
        queryPortfStatusView.clearInputString();
      } catch (IllegalArgumentException e) {
        queryPortfStatusView.setStatus(e.getMessage());
        queryPortfStatusView.clearInputString();
      }
    });

    //*********************Query Portf Log****************************
    buttonClickedMap.put("Query Portfolio Log Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.queryPortfLogView).dispose();
    });

    buttonClickedMap.put("Query Portfolio Log Operation Button", () -> {
      String name = queryPortfLogView.getInputString();
      try {
        String status = model.getPortfolioLog(name);
        queryPortfLogView.setStatus(status);
        queryPortfLogView.clearInputString();
      } catch (IllegalArgumentException e) {
        queryPortfLogView.setStatus(e.getMessage());
        queryPortfLogView.clearInputString();
      }
    });

    //*************************Save Selection**************************
    buttonClickedMap.put("Save Portfolio Button", () -> {
      savePortfView = new SavePortfolioView("Save Portfolio Button", this);
      savePortfView.addActionListener(buttonListener);
      ((JFrame) this.saveSelectionView).dispose();
    });

    buttonClickedMap.put("Save Strategy Button", () -> {
      saveStrategyView = new SaveStrategyView("Save Strategy Button", this);
      saveStrategyView.addActionListener(buttonListener);
      ((JFrame) this.saveSelectionView).dispose();
    });

    //************************Save Portfolio**************************
    buttonClickedMap.put("Save Portfolio Operation Button", () -> {
      String name = savePortfView.getInputString();
      try {
        model.savePortfolio(name);
        savePortfView.setStatus("Portfolio " + name
                + " saved to res\\Portfolios folder");
        savePortfView.clearInputString();
      } catch (IllegalArgumentException | IOException e) {
        savePortfView.setStatus(e.getMessage());
        savePortfView.clearInputString();
      }
    });

    buttonClickedMap.put("Save Portfolio Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.savePortfView).dispose();
    });

    //************************Save Strategy**************************
    buttonClickedMap.put("Save Strategy Operation Button", () -> {
      String text = saveStrategyView.getInputString();
      String[] tokens = text.split(" ");
      boolean moneyFlag = false;
      boolean stockFlag = false;

      Map<String, Double> ratioMap = new HashMap<>();

      double sum = 0.0;
      String nameStr = tokens[0];
      String moneyStr = tokens[1];
      String bDateStr = tokens[2];
      String eDateStr = tokens[3];
      String intervalStr = tokens[4];
      for (int i = 5; i < tokens.length; i += 2) {
        if (!tokens[i].matches(SYMBOL_REGEX)) {
          stockFlag = true;
          break;
        }
        if (!tokens[i + 1].matches(RATIO_REGEX)) {
          moneyFlag = true;
          break;
        }
        ratioMap.put(tokens[i], Double.parseDouble(tokens[i + 1]));
      }
      for (double ratio : ratioMap.values()) {
        sum += ratio;
      }

      if (stockFlag) {
        saveStrategyView.setStatus(TransMess.INVALID_STOCK_SYMBOL.getMess());
        saveStrategyView.clearInputString();
      } else if (moneyFlag) {
        saveStrategyView.setStatus(TransMess.INVALID_RATIO.getMess());
        saveStrategyView.clearInputString();
      } else if (sum != 1) {
        saveStrategyView.setStatus(TransMess.RATIO_SUM_DOESNT_EQUAL_ONE.getMess());
        saveStrategyView.clearInputString();
      } else {
        if (!moneyStr.matches(MONEY_REGEX)) {
          saveStrategyView.setStatus(TransMess.INVALID_MONEY.getMess());
          saveStrategyView.clearInputString();
        } else {
          LocalDate bDate = LocalDate.now();
          LocalDate eDate = LocalDate.now();
          int interval = 0;

          double money = 0.0;
          DateTimeFormatter format =
                  DateTimeFormatter.ofPattern("yyyy-MM-dd");
          try {
            bDate = LocalDate.parse(bDateStr, format);
            try {
              eDate = LocalDate.parse(eDateStr, format);
              if (!intervalStr.matches(VOLUME_REGEX)) {
                saveStrategyView.setStatus(TransMess.INVALID_INTERVAL.getMess());
                saveStrategyView.clearInputString();
              } else {
                interval = Integer.parseInt(intervalStr);
                money = Double.parseDouble(moneyStr);
                try {
                  model.saveStrategy(nameStr, money, ratioMap, bDate, eDate, interval);
                  saveStrategyView.setStatus("Strategy " + nameStr + " saved successfully!");
                  saveStrategyView.clearInputString();
                } catch (IllegalArgumentException | IOException e) {
                  saveStrategyView.setStatus(e.getMessage());
                  saveStrategyView.clearInputString();
                }
              }
            } catch (DateTimeParseException e) {
              saveStrategyView.setStatus("Invalid end date! Please enter again!");
              saveStrategyView.clearInputString();
            }

          } catch (DateTimeParseException e) {
            saveStrategyView.setStatus("Invalid begin date! Please enter again!");
            saveStrategyView.clearInputString();
          }
        }
      }
    });

    buttonClickedMap.put("Save Strategy Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.saveStrategyView).dispose();
    });

    //***********************Retrieve Selection*************************
    buttonClickedMap.put("Retrieve Portfolio Button", () -> {
      retrPortfView = new RetrPortfolioView("Retrieve Portfolio Button", this);
      retrPortfView.addActionListener(buttonListener);
      ((JFrame) this.retrSelectionView).dispose();
    });

    buttonClickedMap.put("Retrieve Strategy Button", () -> {
      retrStrategyView = new RetrStrategyView("Retrieve Strategy Button", this);
      retrStrategyView.addActionListener(buttonListener);
      ((JFrame) this.retrSelectionView).dispose();
    });

    //**********************Retrieve Strategy*******************************
    buttonClickedMap.put("Retrieve Strategy Operation Button", () -> {
      String temp = retrStrategyView.getInputString();
      String[] arr = temp.split("\\n");
      String fileName = arr[0];
      String pName = arr[1];

      try {
        model.retrieveStrategyToPort(fileName, pName);
        retrStrategyView.setStatus("<html>Strategy " + fileName
                + " retrieved successfully<br/> to portfolio " + pName + "!</html>");
        retrStrategyView.clearInputString();
      } catch (IOException | IllegalArgumentException e) {
        retrStrategyView.setStatus(e.getMessage());
        retrStrategyView.clearInputString();
      }
    });

    buttonClickedMap.put("Retrieve Strategy Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.retrStrategyView).dispose();
    });

    buttonClickedMap.put("Check Strategy List Button", () -> {
      String text = model.getAllSavedStrategy();
      final JFrame popUp = new JFrame();
      JOptionPane.showMessageDialog(popUp, text);
    });

    //**********************Retrieve Portfolio******************************
    buttonClickedMap.put("Retrieve Portfolio Operation Button", () -> {
      String text = retrPortfView.getInputString();
      String[] arr = text.split(" ");
      String fileName = arr[0];
      String pName = arr[1];

      try {
        model.retrievePortfolio(fileName, pName);
        retrPortfView.setStatus("Create new portfolio succuessfully!");
        retrPortfView.clearInputString();
      } catch (IllegalArgumentException | IOException e) {
        retrPortfView.setStatus(e.getMessage());
        retrPortfView.clearInputString();
      }
    });

    buttonClickedMap.put("Retrieve Portfolio Operation Back Button", () -> {
      mainView = new MainView("Trade Simulator", this);
      mainView.addActionListener(buttonListener);
      ((JFrame) this.retrPortfView).dispose();
    });

    buttonClickedMap.put("Check Portfolio List Button", () -> {
      String text = model.getAllSavedPortfolio();
      final JFrame popUp = new JFrame();
      JOptionPane.showMessageDialog(popUp, text);
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.mainView.addActionListener(buttonListener);
  }

  @Override
  public void execute() throws IllegalArgumentException, IllegalStateException {
    return;
  }
}
