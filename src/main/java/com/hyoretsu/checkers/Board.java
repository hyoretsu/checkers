package com.hyoretsu.checkers;

import java.util.ArrayList;
import java.util.List;

/** Game board (with 64 positions) */
public class Board {
 private Square[][] squares = new Square[8][8];

 public Board() {
  for (Integer x = 0; x < 8; x++) {
   for (Integer y = 0; y < 8; y++) {
    Square square = new Square(x, y);
    this.squares[x][y] = square;
   }
  }
 }

 /**
  * @param x line
  * @param y column
  *
  * @return Square at coordinate (x, y)
  */
 public Square getSquare(Integer x, Integer y) {
  return this.squares[x][y];
 }

 /** Scans the board for possible moves from the given square. */
 public List<Square> validMoves(Square origin) {
  List<Square> possibleMoves = new ArrayList<>();

  for (Square[] line : this.squares) {
   for (Square square : line) {
    // Within 1 square
    Boolean validX = Math.abs(origin.getX() - square.getX()) == 1;

    // Moving forward
    Boolean validY;
    if (origin.getPiece().getColor() == Piece.WHITE) {
     validY = square.getY() == origin.getY() - 1;
    } else {
     validY = square.getY() == origin.getY() + 1;
    }

    if (square.hasPiece() || !validX || !validY) {
     continue;
    }

    possibleMoves.add(square);
   }
  }

  return possibleMoves;
 }
}
