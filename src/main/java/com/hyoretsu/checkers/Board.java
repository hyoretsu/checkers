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

 private List<Square> blockDiagonal(Integer x, Integer y, Integer[] delta) {
  List<Square> blacklist = new ArrayList<>();

  // Remove all further squares from validation
  for (int i = 0; i < 6; i++) {
   Integer blackX = x + (delta[0] * i);
   Integer blackY = y + (delta[1] * i);

   // Stop as soon as squares get out of the board
   if (!Hooks.withinBoard(blackX, blackY)) {
    break;
   }

   blacklist.add(this.squares[blackX][blackY]);
  }

  return blacklist;
 }

 /** Creates initial pieces */
 private void createPieces() {
  List<Integer> evenX = Arrays.asList(0, 2, 4, 6);
  List<Integer> oddX = Arrays.asList(1, 3, 5, 7);
  List<Integer> redRows = Arrays.asList(0, 1, 2);
  List<Integer> whiteRows = Arrays.asList(5, 6, 7);

  BiConsumer<Integer, Integer> placeOddOrEven = (y, color) -> {
   if (y % 2 == 0) {
    oddX.forEach(x -> new Piece(this.squares[x][y], color));
   } else {
    evenX.forEach(x -> new Piece(this.squares[x][y], color));
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
  List<Square> blacklist = new ArrayList<>();
  Integer colorOffset = origin.getPiece().getColor() == Piece.WHITE ? -1 : 1; // Takes care of the inverted Y directions

  for (Square[] line : this.squares) {
   for (Square square : line) {
    Boolean isKing = origin.getPiece().isKing();
    Integer[] delta = { square.getPosX() - origin.getPosX(), square.getPosY() - origin.getPosY() };

    // Invalid move, either outside of diagonal or the diagonal's blocked
    if ((Math.abs(delta[0]) != Math.abs(delta[1])) || blacklist.contains(square)) {
     continue;
    }

    // Isn't king and move isn't within 1 square
    if (!isKing && Math.abs(delta[0]) != 1) {
     continue;
    }

    // Capturing logic
    if (square.hasPiece()) {
     Integer captureX = square.getPosX() + delta[0];
     Integer captureY = square.getPosY() + delta[1];

     if (isKing) {
      // Blocked by another piece
      if (Hooks.withinBoard(captureX, captureY) && this.squares[captureX][captureY].hasPiece()) {
       // Block off all subsequent squares
       blacklist.addAll(this.blockDiagonal(captureX, captureY, delta));
      }

      continue;
     }

     // Is an enemy piece
     if (square.getPiece().getColor() != origin.getPiece().getColor()) {
      // Next square is still inside of the board
      if (Hooks.withinBoard(captureX, captureY)) {
       Square destination = this.squares[captureX][captureY];

       // Starting position + color move direction + backwards capture
       Integer validY = origin.getPosY() + colorOffset + 1;

       if (destination.hasPiece() || validY == square.getPosY()) { // Can't move or is attempting to move sideways
        continue;
       }

       possibleMoves.add(destination);
      }
     }

     continue;
    }

    // Kings are able to move to any square (if code reached this point)
    if (isKing) {
     possibleMoves.add(square);

     continue;
    }

    // Contrary to the piece's color direction
    if (delta[1] != colorOffset) {
     continue;
    }

    possibleMoves.add(square);
   }
  }

  return possibleMoves;
 }
}
