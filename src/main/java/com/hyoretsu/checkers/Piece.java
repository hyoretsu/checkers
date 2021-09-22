package com.hyoretsu.checkers;

/** A checkers piece */
public class Piece {
 public static final int WHITE = 0;
 public static final int WHITE_KING = 1;
 public static final int RED = 2;
 public static final int RED_KING = 3;

 private Square square;
 private Integer type;

 public Piece(Square square, Integer type) {
  this.square = square;
  this.type = type;
  square.placePiece(this);
 }

 /** @return type of the piece. */
 public Integer getType() {
  return this.type;
 }

 /**
  * Moves the piece to a new square.
  *
  * @param destination new square of this piece.
  */
 public void move(Square destination) {
  this.square.removePiece();
  destination.placePiece(this);
  this.square = destination;
 }
}
