package tradesimulator.controller.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import tradesimulator.controller.command.Buy;
import tradesimulator.controller.command.CheckLog;
import tradesimulator.controller.command.CheckStatus;
import tradesimulator.controller.command.Create;
import tradesimulator.controller.command.CreateSpecified;
import tradesimulator.controller.command.DetermineCost;
import tradesimulator.controller.command.DetermineValue;
import tradesimulator.controller.command.InvestByStrategy;
import tradesimulator.controller.command.InvestFixed;
import tradesimulator.controller.command.InvestSpecified;
import tradesimulator.controller.command.SetCommissionFee;
import tradesimulator.controller.command.ShowList;
import tradesimulator.controller.command.TradeCommand;
import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * the SimulatorController implements IController.
 */
public class SimulatorController implements IController<Stock> {

  private Readable in;
  private Appendable out;

  private static final String SYMBOL_REGEX = "[A-Za-z]+";
  private static final String VOLUME_REGEX = "[1-9][\\d]*";
  private static final String MONEY_REGEX = "(([1-9][\\d]*|[0])\\.[\\d]*)|([1-9][\\d]*)";
  private static final String RATIO_REGEX = "[0]\\.[\\d]*|[1](\\.[0]*)*";
  private static final String QUIT_REGEX = "[q|Q]";

  private static final int NAME_LEN_LIMIT = 20;

  /**
   * the constructor.
   *
   * @param rd the Readable.
   * @param ap the Appendable.
   */
  public SimulatorController(Readable rd, Appendable ap)
          throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException(
              ControllerErrMess.APPENDABLE_OR_READABLE_IS_NULL.getMess());
    }
    this.in = rd;
    this.out = ap;
  }

  @Override
  public void execute(SimulatorOperations<Stock> model)
          throws IllegalArgumentException, IllegalStateException {
    if (model == null) {
      throw new IllegalArgumentException(ControllerErrMess.MODEL_IS_NULL.getMess());
    }
    Scanner scan = new Scanner(in).useDelimiter("[ \\n]");
    Map<String, Function<Scanner, TradeCommand>> knownCmds = setUpCmdMap();
    Stack<TradeCommand> cmdStack = new Stack<>();
    appendMess(welcomeMess());

    while (true) {
      appendMess(commandPrompt());
      String input = readScan(scan);
      if (input.matches(QUIT_REGEX)) {
        appendMess(TransMess.SIMULATOR_IS_QUITED.getMess());
        return;
      }
      Function<Scanner, TradeCommand> f = knownCmds.getOrDefault(input, null);
      if (f == null) {
        appendMess(TransMess.INVALID_COMMANDS.getMess());
        continue;
      }

      TradeCommand cmd = f.apply(scan);
      cmdStack.push(cmd);
      try {
        String update = cmd.execute(model);
        appendMess(update);
      } catch (IllegalArgumentException e) {
        appendMess(ControllerErrMess.EXECUTION_FAILS.getMess()
                + "\nTip:" + e.getMessage() + "\n");
      }
    }
  }

  /**
   * The private helper to append a message to the appendable object.
   *
   * @param transMes the message String
   * @throws IllegalStateException if the append operation fails
   */
  private void appendMess(String transMes) throws IllegalStateException {
    try {
      out.append(transMes);
      out.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException(ControllerErrMess.APPEND_FAILS.getMess());
    }
  }

  private String readScan(Scanner scan) throws IllegalStateException {
    try {
      return scan.next();
    } catch (Exception e) {
      throw new IllegalStateException(ControllerErrMess.READ_FAILS.getMess());
    }
  }

  /**
   * set up the Cmd Map.
   *
   * @return the Map.
   */
  private Map<String, Function<Scanner, TradeCommand>> setUpCmdMap() {
    Map<String, Function<Scanner, TradeCommand>> cmdMap = new HashMap<>();
    cmdMap.put("1", s -> new ShowList());
    cmdMap.put("2", s -> new Create(appendPortfolioNameCreate(s)));
    cmdMap.put("3", s -> new CreateSpecified(appendPortfolioNameCreate(s), appendSymbolList(s)));
    cmdMap.put("4", s ->
            new Buy(appendSymbol(s), appendVolume(s),
                    appendDate(s), appendPortfolioNameSelect(s)));
    cmdMap.put("5", s ->
            new InvestFixed(appendDate(s), appendMoney(s), appendPortfolioNameSelect(s)));
    cmdMap.put("6", s ->
            new InvestSpecified(appendDate(s), appendMoney(s), appendRatioMap(s),
                    appendPortfolioNameSelect(s)));
    cmdMap.put("7", s -> new InvestByStrategy(appendPortfolioNameSelect(s), appendMoney(s),
            appendRatioMap(s), appendBeginDate(s), appendEndDate(s), appendInterval(s)));
    cmdMap.put("8", s -> new DetermineCost(appendDate(s), appendPortfolioNameSelect(s)));
    cmdMap.put("9", s -> new DetermineValue(appendDate(s), appendPortfolioNameSelect(s)));
    cmdMap.put("10", s -> new SetCommissionFee(appendMoney(s)));
    cmdMap.put("11", s -> new CheckStatus(appendPortfolioNameSelect(s)));
    cmdMap.put("12", s -> new CheckLog(appendPortfolioNameSelect(s)));
    return cmdMap;
  }

  /**
   * append the date.
   *
   * @param scan the scan to be used.
   * @return the date added.
   */
  private LocalDate appendDate(Scanner scan) throws IllegalArgumentException {
    DateTimeFormatter format =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    while (true) {
      try {
        appendMess(TransMess.PROMPT_FOR_DATE.getMess());
        return LocalDate.parse(readScan(scan), format);
      } catch (DateTimeParseException e) {
        appendMess(TransMess.INVALID_DATE.getMess());
      }
    }
  }

  /**
   * append the date.
   *
   * @param scan the scan to be used.
   * @return the date added.
   */
  private LocalDate appendBeginDate(Scanner scan) throws IllegalArgumentException {
    DateTimeFormatter format =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    while (true) {
      try {
        appendMess(TransMess.PROMPT_FOR_BEGIN_DATE.getMess());
        return LocalDate.parse(readScan(scan), format);
      } catch (DateTimeParseException e) {
        appendMess(TransMess.INVALID_DATE.getMess());
      }
    }
  }

  /**
   * append the date.
   *
   * @param scan the scan to be used.
   * @return the date added.
   */
  private LocalDate appendEndDate(Scanner scan) throws IllegalArgumentException {
    DateTimeFormatter format =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    while (true) {
      try {
        appendMess(TransMess.PROMPT_FOR_END_DATE.getMess());
        String input = readScan(scan);
        if (input.matches(QUIT_REGEX)) {
          return LocalDate.now();
        }
        return LocalDate.parse(input, format);
      } catch (DateTimeParseException e) {
        appendMess(TransMess.INVALID_DATE.getMess());
      }
    }
  }

  /**
   * append the symbol.
   *
   * @param scan the scan to be used.
   * @return the symbol to be added.
   */
  private String appendSymbol(Scanner scan) throws IllegalArgumentException {
    while (true) {
      appendMess(TransMess.PROMPT_FOR_STOCK_SYMBOL.getMess());
      String input = readScan(scan);
      if (input.matches(SYMBOL_REGEX)) {
        return input.toUpperCase();
      }
      appendMess(TransMess.INVALID_STOCK_SYMBOL.getMess());
    }
  }

  /**
   * append the name of the portfolio.
   *
   * @param scan the scan to be used.
   * @return the name of the portfolio.
   */
  private String appendPortfolioNameSelect(Scanner scan) throws IllegalArgumentException {
    appendMess(TransMess.PROMPT_FOR_PORTF_NAME.getMess());
    return readScan(scan);
  }

  /**
   * append the name of the portfolio.
   *
   * @param scan the scan to be used.
   * @return the name of the portfolio.
   */
  private String appendPortfolioNameCreate(Scanner scan) throws IllegalArgumentException {
    while (true) {
      appendMess(TransMess.PROMPT_FOR_PORTF_NAME.getMess());
      String input = readScan(scan);
      if (input.length() < NAME_LEN_LIMIT) {
        return input;
      }
      appendMess(TransMess.PORTFOLIO_NAME_TOO_LONG.getMess());
    }
  }

  /**
   * add the volume.
   *
   * @param scan the scan to be used.
   * @return the int.
   */
  private double appendVolume(Scanner scan) throws IllegalArgumentException {
    while (true) {
      appendMess(TransMess.PROMPT_FOR_VOLUME.getMess());
      String input = readScan(scan);
      if (input.matches(VOLUME_REGEX)) {
        return Double.parseDouble(input);
      }
      appendMess(TransMess.INVALID_VOLUMES.getMess());
    }
  }

  private List<String> appendSymbolList(Scanner scan) throws IllegalArgumentException {
    List<String> list = new ArrayList<>();
    while (true) {
      appendMess(TransMess.PROMPT_FOR_STOCK_SYMBOL_LIST.getMess());
      String input = readScan(scan);
      if (!input.matches(SYMBOL_REGEX)) {
        appendMess(TransMess.INVALID_STOCK_SYMBOL_LIST.getMess());
      }
      if (symbolListContainsStr(input, list)) {
        appendMess(TransMess.DUPLICATE_STOCK_SYMBOL.getMess());
      }
      if (input.matches(QUIT_REGEX)) {
        return list;
      }
      list.add(input);
    }
  }

  private boolean symbolListContainsStr(String str, List<String> list) {
    if (list.size() == 0) {
      return false;
    }
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).equals(str)) {
        return true;
      }
    }
    return false;
  }

  private Map<String, Double> appendRatioMap(Scanner scan) throws IllegalArgumentException {
    double ratioSum = 0.0;
    Map<String, Double> map = new HashMap<>();
    while (true) {
      appendMess(TransMess.PROMPT_FOR_STOCK_SYMBOL_LIST.getMess());
      String input = readScan(scan);
      if (!input.matches(SYMBOL_REGEX)) {
        appendMess(TransMess.INVALID_STOCK_SYMBOL_LIST.getMess());
        continue;
      }
      if (map.containsKey(input)) {
        appendMess(TransMess.DUPLICATE_STOCK_SYMBOL.getMess());
        continue;
      }
      if (input.matches(QUIT_REGEX)) {
        if (ratioSum != 1) {
          appendMess(TransMess.RATIO_SUM_DOESNT_EQUAL_ONE.getMess());
          ratioSum = 0.0;
          map = new HashMap<>();
          continue;
        }
        appendMess(TransMess.SUCCESSFULLY_SELECT_RATIOS.getMess());
        return map;
      }
      String key = input;
      appendMess(TransMess.PROMPT_FOR_RATIO.getMess() + key + ":");
      String inputRatio = readScan(scan);
      if (!inputRatio.matches(RATIO_REGEX)) {
        appendMess(TransMess.INVALID_RATIO.getMess());
        continue;
      }
      double tempRatio = Double.parseDouble(inputRatio);
      ratioSum += tempRatio;
      map.put(key, tempRatio);
    }
  }

  private double appendMoney(Scanner scan) throws IllegalArgumentException {
    while (true) {
      appendMess(TransMess.PROMPT_FOR_MONEY.getMess());
      String input = readScan(scan);
      if (input.matches(MONEY_REGEX)) {
        return Double.parseDouble(input);
      }
      appendMess(TransMess.INVALID_MONEY.getMess());
    }
  }

  private int appendInterval(Scanner scan) throws IllegalArgumentException {
    while (true) {
      appendMess(TransMess.PROMPT_FOR_INTERVAL.getMess());
      String input = readScan(scan);
      if (input.matches(VOLUME_REGEX)) {
        return Integer.parseInt(input);
      }
      appendMess(TransMess.INVALID_INTERVAL.getMess());
    }
  }

  /**
   * the welcome message.
   */
  private static String welcomeMess() {
    StringBuilder sb = new StringBuilder();
    sb.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
    sb.append("$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n");
    sb.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    return sb.toString();
  }

  private static String commandPrompt() {
    StringBuilder sb = new StringBuilder();
    sb.append("Please enter the following commands to do the corresponding executions:\n");
    sb.append("1 - Show the portfolio lists.\n");
    sb.append("2 - Create the empty portfolio, the name should be unique!\n");
    sb.append("3 - Create the preset portfolio, the name should be unique!.\n");
    sb.append("4 - Buy stock in the designated portfolio.\n");
    sb.append("5 - Invest to the designated portfolio using average ratios.\n");
    sb.append("6 - Modify invest plan and invest to the designated portfolio\n");
    sb.append("7 -  Modify High-level invest plan and invest to the designated portfolio.\n");
    sb.append("8 - Determine the cost basis of a portfolio in a certain date.\n");
    sb.append("9 - Determine the value of the portfolio in a certain date.\n");
    sb.append("10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n");
    sb.append("11 - Check status for designated portfolio.\n");
    sb.append("12 - Check log for designated portfolio.\n");
    sb.append("q/Q - quit the system.\n");
    return sb.toString();
  }
}
