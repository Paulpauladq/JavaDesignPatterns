package tradesimulator.controller.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import tradesimulator.controller.command.Buy;
import tradesimulator.controller.command.Create;
import tradesimulator.controller.command.DetermineCost;
import tradesimulator.controller.command.DetermineValue;
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
    cmdMap.put("3", s ->
            new Buy(appendSymbol(s), appendVolume(s), appendDate(s), appendPortfolioNameSelect(s)));
    cmdMap.put("4", s -> new DetermineCost(appendDate(s), appendPortfolioNameSelect(s)));
    cmdMap.put("5", s -> new DetermineValue(appendDate(s), appendPortfolioNameSelect(s)));
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
  private int appendVolume(Scanner scan) throws IllegalArgumentException {
    while (true) {
      appendMess(TransMess.PROMPT_FOR_VOLUME.getMess());
      String input = scan.next();
      if (input.matches(VOLUME_REGEX)) {
        return Integer.parseInt(input);
      }
      appendMess(TransMess.INVALID_VOLUMES.getMess());
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
    sb.append("2 - Create the portfolio, the name should be unique!\n");
    sb.append("3 - Buy stock in the designated portfolio.\n");
    sb.append("4 - Determine the cost basis of a portfolio in a certain date.\n");
    sb.append("5 - Determine the value of the portfolio in a certain date.\n");
    sb.append("q/Q - quit the system.\n");
    return sb.toString();
  }
}
