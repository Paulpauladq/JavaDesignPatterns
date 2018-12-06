package tradesimulator.model.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;

public class DailyParser implements IParser {

  private static final String apiKey = "E7F2RG3KQVWKX4MR";
  private HashMap<String, HashMap<LocalDate, Double>> stockRecord;


  /**
   * the constructor of the purchase.
   */
  public DailyParser() {
    this.stockRecord = new HashMap<>();
  }

  /**
   * getPrice method is used to get the price of the api.
   *
   * @param stockSymbol the stock Symbol.
   * @param date        the date to be used.
   */
  public double getPrice(String stockSymbol, LocalDate date) {
    if (stockSymbol == null || date == null || stockSymbol.length() == 0) {
      throw new IllegalArgumentException("Illegal input!");
    }
    stockSymbol = stockSymbol.toUpperCase();
    if (!stockRecord.containsKey(stockSymbol)) {
      addRecordToMap(stockSymbol);
    }
    return getPriceFromMap(stockSymbol, date);
  }

  /**
   * Get price from the map that stores the history price of the stock.
   *
   * @param stockSymbol the stock symbol.
   * @param date        the date of the purchase.
   * @return the price of the stock at the date.
   */
  public double getPriceFromMap(String stockSymbol, LocalDate date) {
    HashMap<LocalDate, Double> search1 = stockRecord.get(stockSymbol);
    while (search1.get(date) == null) {
      date = date.plusDays(1);
    }
    Double price = search1.get(date);
    return price;
  }

  @Override
  public LocalDate getDateFromMap(String stockSymbol, LocalDate date) {
    if (!stockRecord.containsKey(stockSymbol)) {
      addRecordToMap(stockSymbol);
    }
    HashMap<LocalDate, Double> search1 = stockRecord.get(stockSymbol);
    while (search1.get(date) == null) {
      date = date.plusDays(1);
    }
    return date;
  }

  /**
   * Add history price record of a new stock to the map.
   *
   * @param stockSymbol the stock symbol.
   */
  private void addRecordToMap(String stockSymbol) {
    String newLine;
    HashMap<LocalDate, Double> oneStockRecord = new HashMap<>();
    URL myURL = getURL(stockSymbol);

    try {
      InputStreamReader reader1 = new InputStreamReader(myURL.openStream());
      BufferedReader reader2 = new BufferedReader(reader1);
      String firstLine;
      if ((firstLine = reader2.readLine()).charAt(0) != 't') {
        throw new IllegalArgumentException("Illegal input for stockSymbol!");
      }

      while ((newLine = reader2.readLine()) != null) {
        String[] record = newLine.split(",");
        Double newP = Double.parseDouble(record[3]);
        LocalDate newDate = LocalDate.parse(record[0]);
        oneStockRecord.put(newDate, newP);
      }
    } catch (IOException ioE) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    stockRecord.put(stockSymbol, oneStockRecord);
  }

  /**
   * Get the URL.
   *
   * @param stockSymbol the stock symbol.
   * @return the URL of the stock.
   */
  private URL getURL(String stockSymbol) {
    URL myURL = null;
    try {
      myURL = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
    return myURL;
  }

  @Override
  public void checkValidStockSymbol(String stockSymbol) {
    URL myURL = getURL(stockSymbol);
    try {
      InputStreamReader reader1 = new InputStreamReader(myURL.openStream());
      BufferedReader reader2 = new BufferedReader(reader1);
      String firstLine = reader2.readLine();
      if (firstLine.charAt(0) != 't') {
        throw new IllegalArgumentException("Stock symbol doesn't exist!");
      }
    } catch (IOException ioE) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
  }
}