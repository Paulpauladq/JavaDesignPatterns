package tradesimulator.model.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DailyParser implements IParser {

  public double getPrice(String stockSymbol, LocalDate date) {
    stockSymbol = stockSymbol.toUpperCase();

    try {
      String apiKey = "E7F2RG3KQVWKX4MR";
      URL url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
      InputStream in = url.openStream();
      StringBuilder allRecord = new StringBuilder();
      int b;
      while ((b = in.read()) != -1) {
        allRecord.append((char) b);
      }
      if (!allRecord.substring(0, 1).equals("t")) {
        throw new IllegalArgumentException("Illegal input for stock!");
      }

      SimpleDateFormat makeFormat = new SimpleDateFormat("yyyy-MM-dd");
      String time = makeFormat.format(date);
      if (allRecord.indexOf(time) == -1) {
        throw new IllegalArgumentException("Illegal input for date!");
      } else {
        String subString1 = allRecord.substring(allRecord.indexOf(time));
        String subString2 = subString1.split("\n")[0];
        String closePrice = subString2.split(",")[4];
        return Double.valueOf(closePrice);
      }
    }catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
  }

}