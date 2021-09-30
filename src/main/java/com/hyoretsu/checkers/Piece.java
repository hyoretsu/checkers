package com.hyoretsu.checkers;

import java.util.ArrayList;
import java.util.List;

import com.hyoretsu.checkers.dtos.Change;

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
  * @return changes that were made, to be used by the GUI.
  */
 public List<Change> move(Square destination) {
  List<Change> changes = new ArrayList<>();

  this.square.removePiece();
  destination.placePiece(this);

  // King transformation
  if (this.color == Piece.WHITE && destination.getPosY() == 0
    || this.color == Piece.RED && destination.getPosY() == 7) {
   this.isKing = true;
  }

  Integer deltaX = destination.getPosX() - this.square.getPosX();
  Integer deltaY = destination.getPosY() - this.square.getPosY();

  // Moving more than 1 square (capture)
  if (Math.abs(deltaX) > 1 || Math.abs(deltaY) > 1) {
   Integer[] offset = { (deltaX > 0 ? -1 : 1), (deltaY > 0 ? -1 : 1) };

   Integer x = this.square.getPosX() + (deltaX + offset[0]);
   Integer y = this.square.getPosY() + (deltaY + offset[1]);
   Square capturedSquare = Hooks.getSquare(x, y);

   capturedSquare.removePiece();
   changes.add(new Change(capturedSquare, "remove"));
  }

  changes.add(new Change(this.square, "move", destination));
  this.square = destination;

  return changes;
 }
}
