package com.hyoretsu.checkers;

/** A checkers piece */
public class Piece {
 public static final int WHITE = 0;
 public static final int RED = 1;

 private Square square;
 private Integer color;
 private Boolean isKing = false;

 public Piece(Square square, Integer color) {
  this.square = square;
  this.color = color;
  square.placePiece(this);
 }

 public Piece(Square square, Integer color, Boolean isKing) {
  this.isKing = isKing;
  this.square = square;
  this.color = color;
  square.placePiece(this);
 }

 /** @return color of the piece. */
 public Integer getColor() {
  return this.color;
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

  // King transformation
  if (this.color == Piece.WHITE && destination.getY() == 0 || this.color == Piece.RED && destination.getY() == 7) {
   this.isKing = true;
  }

  Integer deltaX = destination.getX() - this.square.getX();
  Integer deltaY = destination.getY() - this.square.getY();

  // Moving more than 1 square (capture)
  if (Math.abs(deltaX) > 1 || Math.abs(deltaY) > 1) {
   Integer[] offset = { (deltaX > 0 ? -1 : 1), (deltaY > 0 ? -1 : 1) };

   Integer x = this.square.getX() + (deltaX + offset[0]);
   Integer y = this.square.getY() + (deltaY + offset[1]);

   Hooks.getSquare(x, y).removePiece();
  }

  this.square = destination;
  return;
 }
}
