package freecell.controller;

import java.util.List;

import freecell.model.FreecellOperations;

/**
 * Interface for the freecell game controller. An implementation will work with
 * the IFreeCellModel interface to provide a game of freecell
 */
public interface IFreecellController<K> {

  /**
   * Start and play a new game of freecell with the provided deck. This deck
   * should be used as-is. This method returns only when the game is over
   * (either by winning or by quitting)
   *
   * @param deck    the deck to be used to play this game
   * @param model   the model for the game
   * @param shuffle shuffle the deck if true, false otherwise
   * @throws IllegalArgumentException if the deck is null or invalid, or if the
   *                                  model is null
   * @throws IllegalStateException    if the controller is unable to read input
   *                                  or transmit output
   */
  void playGame(List<K> deck, FreecellOperations<K> model, boolean shuffle) throws
          IllegalArgumentException, IllegalStateException;

}
