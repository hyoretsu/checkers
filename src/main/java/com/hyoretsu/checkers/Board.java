package com.hyoretsu.checkers;

import java.util.ArrayList;
import java.util.List;

/** Game board (with 64 positions) */
public class Board {
 private Square[][] squares = new Square[8][8];

 public Board() {
  for (Integer x = 0; x < 8; x++) {
   for (Integer y = 0; y < 8; y++) {
    this.squares[x][y] = new Square(x, y);
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
  Integer colorOffset = origin.getPiece().getColor() == Piece.WHITE ? -1 : 1; // Takes care of the inverted Y directions

  for (Square[] line : this.squares) {
   for (Square square : line) {
    Boolean validX = Math.abs(origin.getX() - square.getX()) == 1; // Within 1 square
    Integer validY = origin.getY() + colorOffset; // Moving forward

    // Capturing logic
    if (square.hasPiece()) {
     // Should be able to move backwards (1 line further) if capturing
     validY += 1;

     // Is an enemy piece
     if (square.getPiece().getColor() != origin.getPiece().getColor()) {
      Integer x = square.getX() - (origin.getX() - square.getX());
      Integer y = square.getY() - (origin.getY() - square.getY());

      // Within the board
      if ((x >= 0 && x < 8) && (y >= 0 && y < 8)) {
       Square destination = this.squares[x][y];

       // Can't move or move isn't valid
       if (destination.hasPiece() || !validX || validY == square.getY()) {
        continue;
       }

       possibleMoves.add(destination);
      }
     }
     // You cannot capture friendly pieces
     continue;
    }

    // Move isn't valid
    if (!validX || validY != square.getY()) {
     continue;
    }

    possibleMoves.add(square);
   }
  }

  return possibleMoves;
 }
}
