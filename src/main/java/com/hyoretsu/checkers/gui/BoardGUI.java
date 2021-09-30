package com.hyoretsu.checkers.gui;

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

import com.hyoretsu.checkers.Hooks;
import com.hyoretsu.checkers.dtos.Change;

/** GUI of the game board */
public class BoardGUI extends JPanel {
 private SquareGUI[][] squares = new SquareGUI[8][8];

 public BoardGUI(Window window) {
  setLayout(new java.awt.GridLayout(8, 8));
  this.createSquares(window);
 }

 /** Fills the board with 64 squares */
 private void createSquares(Window window) {
  // Up-down
  for (int y = 0; y < 8; y++) {
   // Left-right
   for (int x = 0; x < 8; x++) {
    Color tileColor = this.defineColor(x, y);
    SquareGUI square = new SquareGUI(x, y, tileColor, window);
    this.squares[x][y] = square;
    add(square);

    if (Hooks.hasPiece(square)) {
     square.draw(Hooks.getPiece(square));
    }
   }
  }

  return;
 }

 private Color defineColor(Integer x, Integer y) {
  if (x % 2 == 0) { // Even line
   if (y % 2 == 0) { // Even column
    return SquareGUI.COLOR_LIGHT;
   } else { // Odd column
    return SquareGUI.COLOR_DARK;
   }
  } else { // Odd line
   if (y % 2 == 0) { // Even column
    return SquareGUI.COLOR_DARK;
   } else { // Odd column
    return SquareGUI.COLOR_LIGHT;
   }
  }
 }

 public SquareGUI[][] getSquares() {
  return this.squares;
 }

 public void update(List<Change> changes, List<SquareGUI> moveOptions) {
  changes.forEach(change -> {
   SquareGUI originSquare = this.squares[change.origin.getPosX()][change.origin.getPosY()];

   if (change.action == "move") {
    originSquare.removePiece();

    SquareGUI destSquare = this.squares[change.destination.getPosX()][change.destination.getPosY()];
    destSquare.draw(Hooks.getPiece(destSquare));
   } else if (change.action == "remove") {
    originSquare.removePiece();
   }
  });
  moveOptions.forEach(square -> square.deselect());

  return;
 }
}
