package com.hyoretsu.checkers.gui;

import java.awt.Color;
import javax.swing.JPanel;

import com.hyoretsu.checkers.Board;
import com.hyoretsu.checkers.Game;
import com.hyoretsu.checkers.Piece;
import com.hyoretsu.checkers.Square;

/** GUI of the game board */
public class BoardGUI extends JPanel {
 private Window window;
 private SquareGUI[][] squares;

 public BoardGUI(Window window) {
  this.window = window;
  setLayout(new java.awt.GridLayout(8, 8));
  this.createSquares();
 }

 /** Fills the board with 64 squares */
 private void createSquares() {
  this.squares = new SquareGUI[8][8];
  // Up-down
  for (Integer y = 7; y >= 0; y--) {
   // Left-right
   for (Integer x = 0; x < 8; x++) {
    Color color = this.defineColor(x, y);
    SquareGUI square = new SquareGUI(x, y, color, this);
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

 public Window getWindow() {
  return this.window;
 }

 public void update(Game game) {
  for (Integer x = 0; x < 8; x++) {
   for (Integer y = 0; y < 8; y++) {
    SquareGUI squareGUI = this.squares[x][y];

    Board board = game.getBoard();
    Square square = board.getSquare(x, y);
    if (square.hasPiece()) {
     Piece piece = square.getPiece();

     if (piece.getColor() == Piece.WHITE) {
      if (piece.isKing()) {
       squareGUI.draw(squareGUI.WHITE_KING);
      } else {
       squareGUI.draw(squareGUI.WHITE_MAN);
      }
     } else {
      if (piece.isKing()) {
       squareGUI.draw(squareGUI.RED_KING);
      } else {
       squareGUI.draw(squareGUI.RED_MAN);
      }
     }
    } else {
     squareGUI.removePiece();
    }
   }
  }
 }
}
