import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import tradesimulator.controller.controller.IController;
import tradesimulator.controller.controller.SimulatorController;
import tradesimulator.model.operation.SimulatorModel;
import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

import static org.junit.Assert.assertEquals;

/**
 * Simulator controller test class to test all the cases.
 */
public class SimulatorControllerTest {

  private IController<Stock> controllerTest;
  private SimulatorOperations<Stock> modelTest;
  private SimulatorOperations<Stock> mockModelTest;
  private Reader in;
  private StringBuffer out;

  /**
   * Set up.
   */
  @Before
  public void setUp() {
    out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    modelTest = SimulatorModel.getBuilder().portfolios(10).build();
    String mockState = "Didi! Simulator state!";
    mockModelTest = new MockModel(log, 200.0, 100.0, mockState);

  }

  /**
   * Test buy using normal model.
   */
  @Test
  public void testBuyUsingNormalModel() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid money($):\n" +
            "Invalid money! Please enter again:\n" +
            "Enter a valid money($):\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid ratio for FB:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid ratio for MSFT:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Stocks and corresponding ratios are added successfully!\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully invest $1000.0 on 2018-01-01 into portfolio init by specified ratios\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Total cost of init by 2018-10-10 is 990.0\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("6 2018-01-01 2018-10-01 1000 FB 0.5 MSFT" +
            " 0.5 q init 8 2018-10-10 init q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(modelTest);
    //System.out.println(out.toString());
    assertEquals(expected, out.toString());
  }

  /**
   * Buy multiple times using normal model.
   */
  @Test
  public void testBuyMultipleTimesUsingNormalModel() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100.0 shares on 2018-10-12 into portfolio init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock FB 100.0 shares on 2018-10-12 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock FB 200.0 shares on 2018-10-12 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 4 GOOG 100 2018-10-12 "
            + "init 4 FB 100 2018-10-12 ppt 4 FB 200 2018-10-12 ppt q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(modelTest);
    assertEquals(expected, out.toString());
  }

  /**
   * Test null readable exception.
   */
  @Test
  public void testNullReadableException() {
    try {
      controllerTest = new SimulatorController(in, out);
    } catch (IllegalArgumentException e) {
      assertEquals("Readable or Appendable parameter is null!", e.getMessage());
    }
  }

  /**
   * Test appendable exception.
   */
  @Test
  public void testNullAppendableException() {
    in = new StringReader("1 2 ppt 3 GOOG 100 2018-10-12 "
            + "init 3 FB 100 2018-10-12 ppt 3 FB 200 2018-10-12 ppt q");
    out = null;
    try {
      controllerTest = new SimulatorController(in, out);
    } catch (IllegalArgumentException e) {
      assertEquals("Readable or Appendable parameter is null!", e.getMessage());
    }
  }

  /**
   * Test null readable and appendable exception.
   */
  @Test
  public void testNullReadableAndAppendableException() {
    in = null;
    out = null;
    try {
      controllerTest = new SimulatorController(in, out);
    } catch (IllegalArgumentException e) {
      assertEquals("Readable or Appendable parameter is null!", e.getMessage());
    }
  }

  /**
   * Test model is null.
   */
  @Test
  public void testModelIsNull() {
    in = new StringReader("1 2 ppt 3 GOOG 100 2018-10-12 "
            + "init 3 FB 100 2018-10-12 ppt 3 FB 200 2018-10-12 ppt q");
    controllerTest = new SimulatorController(in, out);
    modelTest = null;

    try {
      controllerTest.execute(modelTest);
    } catch (IllegalArgumentException e) {
      assertEquals("model parameter is null!", e.getMessage());
    }
  }

  /**
   * Test read fails exception.
   */
  @Test
  public void testReadFailsException() {
    in = new StringReader("1");
    controllerTest = new SimulatorController(in, out);
    try {
      controllerTest.execute(modelTest);
    } catch (IllegalStateException e) {
      assertEquals("Read operation fails!", e.getMessage());
    }

  }

  /**
   * Test controller show init list.
   */
  @Test
  public void testControllerShowInitList() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(modelTest);
    //System.out.println(out.toString());
    assertEquals(expected, out.toString());
  }

  /**
   * Test controller show init list create and show.
   */
  @Test
  public void testControllerShowInitListCreateAndShow() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 1 q");
    controllerTest = new SimulatorController(in, out);
    try {
      controllerTest.execute(modelTest);
    } catch (IllegalStateException e) {
      assertEquals("Read operation fails!", e.getMessage());
    }
    assertEquals(expected, out.toString());
  }

  /**
   * Test controller show init list create multiple times.
   */
  @Test
  public void testControllerShowInitListCreateMultiple() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created yibao\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created sese\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created sexaual\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "sese\n" +
            "ppt\n" +
            "yibao\n" +
            "sexaual\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 2 yibao 2 sese 2 sexaual 1 q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(modelTest);
    assertEquals(expected, out.toString());
  }

  /**
   * Test controller create and buy.
   */
  @Test
  public void testControllerCreateAndBuy() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created yibao\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created sese\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created sexaual\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100.0 shares on 2018-10-12 into portfolio init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock FB 100.0 shares on 2018-10-12 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock FB 100.0 shares on 2018-10-12 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 2 yibao 2 sese 2 sexaual "
            + "1 4 GOOG 100 2018-10-12 init 4 FB 100 2018-10-12 ppt 4 FB 100 2018-10-12 ppt q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    //System.out.println(out.toString());
    assertEquals(expected, out.toString());
  }

  /**
   * Test controller create too long.
   */
  @Test
  public void testControllerCreateTooLong() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Portfolio name is too long!\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created qsad\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 2 yibaoisafukingidiothahasdjkqjdsdaa qsad q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);

    assertEquals(expected, out.toString());
  }

  /**
   * Test controller buy operations fails.
   */
  @Test
  public void testControllerBuyOperationsFails() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100.0 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 4 goog 100 2017-12-31 ppt q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    //System.out.println(out.toString());
    assertEquals(expected, out.toString());
  }

  /**
   * Test controller buy buy buy and get cost.
   */
  @Test
  public void testControllerBuyBuyBuyAndGetMoneyRichRich() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100.0 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100.0 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100.0 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Total cost of q by 2018-12-31 is 200.0\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 4 goog 100 2017-12-31 ppt 4 goog 100 2017-12-31 ppt "
            + "4 goog 100 2017-12-31 ppt 8 2018-12-31 q q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    assertEquals(expected, out.toString());
  }

  /**
   * Test controller buy buy buy and get value.
   */
  @Test
  public void testControllerBuyBuyBuyAndGetValue() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100.0 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100.0 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100.0 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Total value of ppt by 2018-12-31 is 100.0\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 4 goog 100 2017-12-31 ppt 4 goog 100 2017-12-31 ppt "
            + "4 goog 100 2017-12-31 ppt 9 2018-12-31 ppt q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    assertEquals(expected, out.toString());
  }

  @Test
  public void testControllerCreateSpecifiedPortf() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Successfully created specified list ppt with stocks:\n" +
            " APPL FB AMAZ MSFT\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Successfully created specified list yibao with stocks:\n" +
            " APPL FB\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 3 ppt APPL FB AMAZ MSFT q 3 yibao APPL FB q 1 q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    assertEquals(expected, out.toString());
  }

  @Test
  public void testControllerInvestFixedRatio() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Successfully created specified list ppt with stocks:\n" +
            " APPL FB AMAZ MSFT\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Successfully created specified list yibao with stocks:\n" +
            " APPL FB\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid money($):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully invest $100.0 on 2018-10-10 into portfolio ppt by fixed ratios\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid money($):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully invest $200.1 on 2018-03-12 into portfolio yibao by fixed ratios\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 3 ppt APPL FB AMAZ MSFT q 3 yibao APPL "
            + "FB q 5 2018-10-10 100 ppt 5 2018-03-12 200.10 yibao q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    assertEquals(expected, out.toString());
  }

  @Test
  public void testControllerInvestSpecifiedRatio() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid money($):\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid ratio for appl:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid ratio for fb:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Stocks and corresponding ratios are added successfully!\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully invest $1000.0 on 2018-01-01 into portfolio init by specified ratios\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 6 2018-01-01 1000 appl 0.9 " +
            "fb 0.1 q init q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    assertEquals(expected, out.toString());
  }

  @Test
  public void testControllerInvestSpecifiedRatioInputWrong() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Invalid date! Please enter again:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Invalid date! Please enter again:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid money($):\n" +
            "Invalid money! Please enter again:\n" +
            "Enter a valid money($):\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid ratio for appl:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid ratio for fb:\n" +
            "Invalid ratio! Please try again.\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid ratio for fb:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Stocks and corresponding ratios are added successfully!\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully invest $1000.0 on 2018-01-01 into portfolio init by specified ratios\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 6 ads 023 2018-01-01 adp 1000 appl 0.9 " +
            "fb sad fb 0.1 q init q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    assertEquals(expected, out.toString());
  }

  @Test
  public void testControllerMakeHighLevelDate() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Enter a valid money($):\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid ratio for appl:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Enter a valid ratio for fb:\n" +
            "Enter a valid stock symbol or q/Q to stop:\n" +
            "Stocks and corresponding ratios are added successfully!\n" +
            "Enter a valid begin date(yyyy-MM-dd):\n" +
            "Enter a valid end date(yyyy-MM-dd) or enter q/Q to make the plan ongoing:\n" +
            "Enter a valid interval:\n" +
            "Successfully invest $10000.0 from 2018-01-01 to 2018-10-01 periodically " +
            "into portfolio init by specified ratios\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("7 init 10000 appl 0.9 fb 0.1 q 2018-01-01 2018-10-01 10 q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    assertEquals(expected, out.toString());
  }

  @Test
  public void testControllerSetCommissionFee() {
    String expected = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "$$$$$$$$$$ WELCOME TO TRADE SIMULATOR SYSTEM! $$$$$$$$$$\n" +
            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid money($):\n" +
            "Successfully set commission fee to $10.10\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the empty portfolio, the name should be unique!\n" +
            "3 - Create the preset portfolio, the name should be unique!.\n" +
            "4 - Buy stock in the designated portfolio.\n" +
            "5 - Invest to the designated portfolio using average ratios.\n" +
            "6 - Modify invest plan and invest to the designated portfolio\n" +
            "7 -  Modify High-level invest plan and invest to the designated portfolio.\n" +
            "8 - Determine the cost basis of a portfolio in a certain date.\n" +
            "9 - Determine the value of the portfolio in a certain date.\n" +
            "10 - Set commission fee($) for each transaction.(Default value is 5.00$)\n" +
            "11 - Check status for designated portfolio.\n" +
            "12 - Check log for designated portfolio.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("10 10.10 q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    assertEquals(expected, out.toString());
  }
}