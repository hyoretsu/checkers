package com.hyoretsu.checkers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/** Game board (with 64 positions) */
public class Board {
 private Square[][] squares = new Square[8][8];

 public Board() {
  for (int x = 0; x < 8; x++) {
   for (int y = 0; y < 8; y++) {
    this.squares[x][y] = new Square(x, y);
   }
  }

  this.createPieces();
 }

 /** Creates initial pieces */
 private void createPieces() {
  List<Integer> evenX = Arrays.asList(0, 2, 4, 6);
  List<Integer> oddX = Arrays.asList(1, 3, 5, 7);
  List<Integer> redRows = Arrays.asList(0, 1, 2);
  List<Integer> whiteRows = Arrays.asList(5, 6, 7);

  BiConsumer<Integer, Integer> placeOddOrEven = (y, color) -> {
   if (y % 2 == 0) {
    evenX.forEach(x -> new Piece(this.squares[x][y], color));
   } else {
    oddX.forEach(x -> new Piece(this.squares[x][y], color));
   }
  };

  whiteRows.forEach(y -> placeOddOrEven.accept(y, Piece.WHITE));
  redRows.forEach(y -> placeOddOrEven.accept(y, Piece.RED));

  return;
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
    Boolean validX = Math.abs(origin.getPosX() - square.getPosX()) == 1; // Within 1 square
    Integer validY = origin.getPosY() + colorOffset; // Moving forward

    // Capturing logic
    if (square.hasPiece()) {
     // Should be able to move backwards (1 line further) if capturing
     validY += 1;

     // Is an enemy piece
     if (square.getPiece().getColor() != origin.getPiece().getColor()) {
      Integer x = square.getPosX() - (origin.getPosX() - square.getPosX());
      Integer y = square.getPosY() - (origin.getPosY() - square.getPosY());

      // Within the board
      if ((x >= 0 && x < 8) && (y >= 0 && y < 8)) {
       Square destination = this.squares[x][y];

       // Can't move or move isn't valid
       if (destination.hasPiece() || !validX || validY == square.getPosY()) {
        continue;
       }

       possibleMoves.add(destination);
      }
     }
     // You cannot capture friendly pieces
     continue;
    }

    // Move isn't valid
    if (!validX || validY != square.getPosY()) {
     continue;
    }

    possibleMoves.add(square);
   }
  }

  return possibleMoves;
 }
}
