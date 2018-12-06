package tradesimulator.view.textfieldview;

import tradesimulator.view.simpleview.ISimulatorView;

/**
 * Interface of the view containing text fields.
 */
public interface ITextFieldView extends ISimulatorView {

  /**
   * getInputString() is used to get the input String.
   * @return the input String
   */
  String getInputString();

  /**
   * Clear input String of all the text fields.
   */
  void clearInputString();

  /**
   * Set the status bar.
   * @param mess the String message
   */
  void setStatus(String mess);

}
