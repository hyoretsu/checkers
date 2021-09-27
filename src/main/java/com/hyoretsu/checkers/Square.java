package com.hyoretsu.checkers;

/** A square from the board */
public class Square {
 private Piece piece = null;
 private Integer x;
 private Integer y;

 public Square(Integer x, Integer y) {
  this.x = x;
  this.y = y;
 }

 /** @return current piece present in this square or null. */
 public Piece getPiece() {
  return this.piece;
 }

 public Integer getX() {
  return this.x;
 }

 public Integer getY() {
  return this.y;
 }

 /** @return true if there's a piece in this square, otherwise false. */
 public boolean hasPiece() {
  return this.piece != null;
 }

 /** @param piece piece to be slotted in this square */
 public void placePiece(Piece piece) {
  this.piece = piece;

  return;
 }

 /** Removes the square's current piece */
 public void removePiece() {
  this.piece = null;

  return;
 }
}
