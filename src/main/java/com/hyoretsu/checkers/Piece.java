package com.hyoretsu.checkers;

/** A checkers piece */
public class Piece {
 public static final int WHITE = 0;
 public static final int RED = 1;

 private Boolean isKing = false;
 private Square square;
 private Integer type;

 public Piece(Square square, Integer type) {
  this.square = square;
  this.type = type;
  square.placePiece(this);
 }

 public Piece(Square square, Integer type, Boolean isKing) {
  this.isKing = isKing;
  this.square = square;
  this.type = type;
  square.placePiece(this);
 }

 /** @return type of the piece. */
 public Integer getColor() {
  return this.type;
 }

 public Boolean isKing() {
  return this.isKing;
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
