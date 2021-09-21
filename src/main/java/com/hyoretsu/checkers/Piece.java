package com.hyoretsu.checkers;

/** A checkers piece */
public class Piece {
 public static final int WHITE = 0;
 public static final int WHITE_KING = 1;
 public static final int RED = 2;
 public static final int RED_KING = 3;

 private Square square;
 private int type;

 public Piece(Square square, int type) {
  this.square = square;
  this.type = type;
  square.placePiece(this);
 }

 /** @return type of the piece. */
 public int getType() {
  return type;
 }

 /**
  * Moves the piece to a new square.
  *
  * @param destination new square of this piece.
  */
 public void move(Square destination) {
  square.removePiece();
  destination.placePiece(this);
  square = destination;
 }
}
