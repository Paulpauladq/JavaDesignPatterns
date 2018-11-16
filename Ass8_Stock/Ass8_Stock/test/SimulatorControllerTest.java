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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2018-10-12 into portfolio init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 3 GOOG 100 2018-10-12 init q");
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2018-10-12 into portfolio init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock FB 100 shares on 2018-10-12 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock FB 200 shares on 2018-10-12 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 3 GOOG 100 2018-10-12 "
            + "init 3 FB 100 2018-10-12 ppt 3 FB 200 2018-10-12 ppt q");
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n\n" +
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
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
    //System.out.println(out.toString());
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created yibao\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created sese\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created sexaual\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 2 yibao 2 sese 2 sexaual 1 q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(modelTest);
    //System.out.println(out.toString());
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created yibao\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created sese\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created sexaual\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2018-10-12 into portfolio init\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock FB 100 shares on 2018-10-12 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock FB 100 shares on 2018-10-12 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 2 yibao 2 sese 2 sexaual "
            + "1 3 GOOG 100 2018-10-12 init 3 FB 100 2018-10-12 ppt 3 FB 100 2018-10-12 ppt q");
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Portfolio name is too long!\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created qsad\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 2 yibaoisafukingidiothahasdjkqjdsdaa qsad q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    //System.out.println(out.toString());
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 3 goog 100 2017-12-31 ppt q");
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Total cost of q by 2018-12-31 is 200.0\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 3 goog 100 2017-12-31 ppt 3 goog 100 2017-12-31 ppt "
            + "3 goog 100 2017-12-31 ppt 4 2018-12-31 q q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    //System.out.println(out.toString());
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
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "All the Portfolios is as follows:\n" +
            "Didi! Simulator state!\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully created ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid stock symbol:\n" +
            "Enter a valid volume:\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Successfully bought stock GOOG 100 shares on 2017-12-31 into portfolio ppt\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter a valid date(yyyy-MM-dd):\n" +
            "Enter a valid portfolio name:\n" +
            "Total value of ppt by 2018-12-31 is 100.0\n" +
            "\n" +
            "Please enter the following commands to do the corresponding executions:\n" +
            "1 - Show the portfolio lists.\n" +
            "2 - Create the portfolio, the name should be unique!\n" +
            "3 - Buy stock in the designated portfolio.\n" +
            "4 - Determine the cost basis of a portfolio in a certain date.\n" +
            "5 - Determine the value of the portfolio in a certain date.\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Simulator quit prematurely.\n";
    in = new StringReader("1 2 ppt 3 goog 100 2017-12-31 ppt 3 goog 100 2017-12-31 ppt "
            + "3 goog 100 2017-12-31 ppt 5 2018-12-31 ppt q");
    controllerTest = new SimulatorController(in, out);
    controllerTest.execute(mockModelTest);
    assertEquals(expected, out.toString());
  }
}