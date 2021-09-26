package com.hyoretsu.checkers;

/** A checkers piece */
public class Piece {
 public static final int WHITE = 0;
 public static final int RED = 1;

 private Square square;
 private Square captureTarget;
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

 public void addCaptureTarget(Square target) {
  this.captureTarget = target;
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

  Integer deltaX = destination.getX() - this.square.getX();
  Integer deltaY = destination.getY() - this.square.getY();

  // Moving more than 1 square (capture)
  if (Math.abs(deltaX) > 1 || Math.abs(deltaY) > 1) {
   this.captureTarget.removePiece();
   this.captureTarget = null;
  }

  this.square = destination;
 }
}
