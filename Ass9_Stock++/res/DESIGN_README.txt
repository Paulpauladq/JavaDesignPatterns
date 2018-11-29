$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$ TRADE_SIMULATOR_SYSTEM DESIGN $$$$$$
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

DESIGN:
1)Our design is based on MVC design pattern.
2)We put all the mechanisms and logic of our trader simulator in the model layer.
3)As for the controller layer, we use the command design pattern to design it.
4)As for the view layer, we currently use text-based commands to control it.
5)Regarding the changes of Stock++, we extend the model interface and implements accordingly
  in the model class.
6)investFixedAmount(), investSpecifiedAmount(), investPeriodically(), createSpecifiedPortfolio(),
  setCommissionFee() added to the model interface are used to implement the to implement new
  functions.
7)Also, we and some commands in the controller to enhance the control panel.

SUPPLEMENT MATERIALS:
1)Javadoc is in the res folder.
2)Class diagrams are in the res folder.
3)All the mvc classes are in the src folder.
4)All the tests are in the default package in test folder.