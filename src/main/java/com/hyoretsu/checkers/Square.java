package com.hyoretsu.checkers;

/** A square from the board */
public class Square {
 private Integer x;
 private Integer y;
 private Piece piece = null;

 public Square(Integer x, Integer y) {
  this.x = x;
  this.y = y;
 }

 /** @return current piece present in this square or null. */
 public Piece getPiece() {
  return piece;
 }

 /** @return true if there's a piece in this square, otherwise false. */
 public boolean hasPiece() {
  return piece != null;
 }

 /** @param piece piece to be slotted in this square */
 public void placePiece(Piece piece) {
  this.piece = piece;
 }

 /** Removes the square's current piece */
 public void removePiece() {
  piece = null;
 }
}
