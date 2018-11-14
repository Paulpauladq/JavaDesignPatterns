package tradesimulator.model.parser;

import java.time.LocalDate;

public interface IParser {

  double getPrice(String stockSymbol, LocalDate date);
}
