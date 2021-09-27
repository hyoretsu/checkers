package com.hyoretsu.checkers.gui;

import java.awt.Color;
import javax.swing.JPanel;

import com.hyoretsu.checkers.Hooks;
import com.hyoretsu.checkers.Piece;

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
  for (Integer y = 0; y < 8; y++) {
   // Left-right
   for (Integer x = 0; x < 8; x++) {
    Color tileColor = this.defineColor(x, y);
    SquareGUI square = new SquareGUI(x, y, tileColor, window);
    this.squares[x][y] = square;
    add(square);
   }
  }
 }

 private Color defineColor(Integer x, Integer y) {
  if (x % 2 == 0) { // Even line
   if (y % 2 == 0) { // Even column
    return SquareGUI.COLOR_DARK;
   } else { // Odd column
    return SquareGUI.COLOR_LIGHT;
   }
  } else { // Odd line
   if (y % 2 == 0) { // Even column
    return SquareGUI.COLOR_LIGHT;
   } else { // Odd column
    return SquareGUI.COLOR_DARK;
   }
  }
 }

 public SquareGUI[][] getSquares() {
  return this.squares;
 }

 public void update() {
  for (Integer x = 0; x < 8; x++) {
   for (Integer y = 0; y < 8; y++) {
    SquareGUI squareGUI = this.squares[x][y];

    if (Hooks.hasPiece(squareGUI)) {
     Piece piece = Hooks.getPiece(squareGUI);

     // Decide piece's icon
     if (piece.getColor() == Piece.WHITE) {
      if (piece.isKing()) {
       squareGUI.draw(SquareGUI.WHITE_KING);
      } else {
       squareGUI.draw(SquareGUI.WHITE_MAN);
      }
     } else {
      if (piece.isKing()) {
       squareGUI.draw(SquareGUI.RED_KING);
      } else {
       squareGUI.draw(SquareGUI.RED_MAN);
      }
     }
    } else { // Piece's captured in the last round
     squareGUI.removePiece();
    }

    squareGUI.deselect();
   }
  }

  return;
 }
}
