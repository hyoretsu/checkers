package com.hyoretsu.checkers.gui;

import java.awt.Color;
import javax.swing.JPanel;

import com.hyoretsu.checkers.Board;
import com.hyoretsu.checkers.Piece;
import com.hyoretsu.checkers.Square;

/** GUI of the game board */
public class BoardGUI extends JPanel {
 private Board board;
 private SquareGUI[][] squares = new SquareGUI[8][8];

 public BoardGUI(Window window) {
  this.board = window.getGame().getBoard();

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
    SquareGUI square = new SquareGUI(this.board.getSquare(x, y), tileColor, window);
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

    Square square = this.board.getSquare(x, y);
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

    squareGUI.deselect();
   }
  }
 }
}
