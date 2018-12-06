package tradesimulator.view.simpleview;

import java.awt.event.ActionListener;

/**
 * Interface of the simple view without text field.
 */
public interface ISimulatorView {

  /**
   * Add action listener method is used to add action listener of the view.
   * @param listener the listener
   */
  void addActionListener(ActionListener listener);

  /**
   * Reset focus class is used to reset focus.
   */
  void resetFocus();
}
