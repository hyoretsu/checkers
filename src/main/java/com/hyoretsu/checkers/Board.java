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
  // You need this in order to take care of the inverted Y directions
  Integer colorOffset = origin.getPiece().getColor() == Piece.WHITE ? -1 : 1;

  for (Square[] line : this.squares) {
   for (Square square : line) {
    // Within 1 square
    Boolean validX = Math.abs(origin.getX() - square.getX()) == 1;
    // Moving forward
    Boolean validY = square.getY() == origin.getY() + colorOffset;

    if (!validX || !validY) {
     continue;
    }

    if (square.hasPiece()) {
     if (square.getPiece().getColor() != origin.getPiece().getColor()) {
      Integer x = square.getX() - (origin.getX() - square.getX());
      Integer y = square.getY() - (origin.getY() - square.getY());

      if ((x >= 0 && x < 8) && (y >= 0 && y < 8)) {
       Square destination = this.squares[x][y];

       if (destination.hasPiece()) {
        continue;
       }

       possibleMoves.add(destination);
      }
     }

     continue;
    }

    possibleMoves.add(square);
   }
  }

  return possibleMoves;
 }
}
