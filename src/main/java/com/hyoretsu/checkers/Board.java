package com.hyoretsu.checkers;

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

 /** Checks if the given move is valid. */
 public Boolean validMove(Square origin, Square destination) {
  if (destination.hasPiece()) {
   return false;
  }

  // Within 1 square
  Boolean validX = Math.abs(origin.getX() - destination.getX()) == 1;

  // Moving forward
  Boolean validY;
  if (origin.getPiece().getColor() == Piece.WHITE) {
   validY = destination.getY() == origin.getY() - 1;
  } else {
   validY = destination.getY() == origin.getY() + 1;
  }

  if (!validX || !validY) {
   return false;
  }

  return true;
 }
}
