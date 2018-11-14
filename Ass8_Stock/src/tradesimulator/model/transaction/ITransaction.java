package tradesimulator.model.transaction;

import java.time.LocalDate;

public interface ITransaction {

  LocalDate getDate();

  double getPrice();

  int getAmount();

  boolean isBuy();
}
