package com.hyoretsu.checkers;

/** A square from the board */
public class Square {
 private int x;
 private int y;
 private Piece piece;

 public Square(int x, int y) {
  this.x = x;
  this.y = y;
  this.piece = null;
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
