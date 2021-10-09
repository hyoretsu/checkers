package com.hyoretsu.checkers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hyoretsu.checkers.dtos.Change;
import com.hyoretsu.checkers.util.Const;
import com.hyoretsu.checkers.util.Hooks;

/** A checkers piece */
public class Piece {
 // Team colors
 public static final int WHITE = 0;
 public static final int RED = 1;

 private Square square;
 /** 0 for White and 1 for Red */
 private Integer color;
 private Boolean isKing;

 public Piece(Square square, Integer color) {
  this.isKing = false;
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

 public List<Square> filterCaptures() {
  List<Square> movesList = this.validMoves();

  if (this.isKing) {
   Square[][] diagonals = this.findDiagonals();
   // Separate moves into each diagonal
   Square[][] filteredDiagonals = new Square[4][7];

   // Scan each diagonal
   for (int direction = 0; direction < 4; direction++) {
    Integer position = 0;

    // Scan possible moves
    for (Square move : movesList) {
     // Go through each position
     for (int j = 0; j < diagonals[direction].length; j++) {
      // Move is part of that diagonal
      if (move == diagonals[direction][j]) {
       // Update index to put in
       if (filteredDiagonals[direction][position] != null) {
        position += 1;
       }

       // Add it to the array with each diagonal's possible moves
       filteredDiagonals[direction][position] = move;
      }
     }
    }
   }
   movesList.clear();

   // Filter captures
   for (Square[] diagonal : filteredDiagonals) {
    // Skip empty diagonals
    if (diagonal[0] == null && diagonal[1] == null) {
     continue;
    }

    Integer validX, previousX = this.square.getPosX();
    // To the right of piece, incrementing X
    if (diagonal[0].getPosX() - this.square.getPosX() > 0) {
     validX = 1;
    } else { // To the left of piece, decrementing X
     validX = -1;
    }

    for (int i = 0; i < diagonal.length; i++) {
     Square move = diagonal[i];

     // Stop after reaching the end
     if (move == null) {
      continue;
     }

     // Normal move
     if (move.getPosX() - previousX == validX) {
      previousX = move.getPosX();
      continue;
     } else { // 1 square was skipped, meaning the rest are part of a valid capture
      movesList.add(move);
     }
    }
   }
  }

  // Filter captures
  movesList.removeIf(move -> {
   Integer deltaX = Math.abs(move.getPosX() - this.square.getPosX());
   Integer deltaY = Math.abs(move.getPosY() - this.square.getPosY());

   // Not a capture
   if (deltaX == 1 && deltaY == 1) {
    return true;
   }

   return false;
  });

  return movesList;
 }

 private Square[][] findDiagonals() {
  Square[][] diagonals = new Square[4][7];
  Integer originX = this.square.getPosX();
  Integer originY = this.square.getPosY();

  for (int i = 0; i < 4; i++) {
   Integer[] delta = new Integer[2];

   if (i == 0) {
    // Left-up
    delta[0] = -1;
    delta[1] = -1;
   } else if (i == 1) {
    // Left-down
    delta[0] = -1;
    delta[1] = 1;
   } else if (i == 2) {
    // Right-up
    delta[0] = 1;
    delta[1] = -1;
   } else if (i == 3) {
    // Right-down
    delta[0] = 1;
    delta[1] = 1;
   }

   for (int j = 1; j <= 7; j++) {
    Integer x = originX + (delta[0] * j);
    Integer y = originY + (delta[1] * j);

    if (!this.withinBoard(x, y)) {
     break;
    }

    diagonals[i][j - 1] = Hooks.getSquare(x, y);
   }
  }

  return diagonals;
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

  this.handleCapture(destination, changes);

  changes.add(new Change(this.square, "move", destination));
  this.square = destination;

  // Moving to the last square
  if (this.color * 0 == destination.getPosY() || this.color * 7 == destination.getPosY()) {
   // Either force turn or ask, if the streak hasn't ended yet
   if (this.validMoves().isEmpty() || JOptionPane.showConfirmDialog(null, "Do you want to turn your piece into a king?",
     "King transformation", JOptionPane.YES_NO_OPTION) == 1) {
    this.isKing = true;
   }
  }

  return changes;
 }

 private void handleCapture(Square destination, List<Change> changes) {
  // [deltaX, deltaY]
  Integer[] delta = new Integer[2];
  delta[0] = destination.getPosX() - this.square.getPosX();
  delta[1] = destination.getPosY() - this.square.getPosY();

  // Moving 1 square (not capturing)
  if (Math.abs(delta[0]) == 1 || Math.abs(delta[1]) == 1) {
   return;
  }

  // [offsetX, offsetY]
  Integer[] offset = new Integer[2];
  offset[0] = delta[0] > 0 ? -1 : 1;
  offset[1] = delta[1] > 0 ? -1 : 1;

  Integer x = this.square.getPosX() + (delta[0] + offset[0]);
  Integer y = this.square.getPosY() + (delta[1] + offset[1]);
  Square capturedSquare = Hooks.getSquare(x, y);

  if (this.isKing == true) {
   // Scan all in-between squares
   for (int i = 0; i < 7; i++) {
    // Increment coordinate to next square (or start at first one)
    x += (i > 0) ? offset[0] : 0;
    y += (i > 0) ? offset[1] : 0;

    // Stop as soon as it reaches outside of the board
    if (!withinBoard(x, y)) {
     break;
    }

    capturedSquare = Hooks.getSquare(x, y);

    // Ignore empty squares
    if (!capturedSquare.hasPiece()) {
     continue;
    }

    // Unable to capture friendly pieces
    if (capturedSquare.getPiece().getColor() == this.color) {
     return;
    }

    // Stop at first piece found
    break;
   }
  }

  Hooks.decreaseTeamCount(capturedSquare.getPiece());
  capturedSquare.removePiece();
  changes.add(new Change(capturedSquare, "remove"));

  return;
 }

 /** Scans the board for possible moves. */
 public List<Square> validMoves() {
  List<Square> possibleMoves = new ArrayList<>();

  Integer colorOffset = this.color == Piece.WHITE ? -1 : 1; // Takes care of each color's movement direction
  Integer[] origin = { this.square.getPosX(), this.square.getPosY() }; // Coordinates (x, y) for origin square

  List<Square> diagonalSquares = new ArrayList<>();

  Integer[] sidewaysX = { origin[0] - 1, origin[0] + 1 }; // [left, right]
  Integer[] directionY = { origin[1] + colorOffset, origin[1] - colorOffset }; // [forward, backward]
  for (int i = 0; i <= 1; i++) {
   for (int j = 0; j <= 1; j++) {
    if (this.withinBoard(sidewaysX[i], directionY[j])) {
     diagonalSquares.add(Hooks.getSquare(sidewaysX[i], directionY[j]));
    }
   }
  }

  diagonalSquares.forEach(square -> this.handleValidCheck(square, possibleMoves));

  return possibleMoves;
 }

 private void handleValidCheck(Square square, List<Square> validList) {
  Integer limit = this.isKing ? 6 : 1; // Number of possible squares after capturing
  Boolean alreadyEating = false;

  // Tells which diagonal to scan
  Integer[] delta = { square.getPosX() - this.square.getPosX(), square.getPosY() - this.square.getPosY() };
  for (int i = 0; i < limit; i++) {
   Integer currentX = square.getPosX() + (delta[0] * i);
   Integer currentY = square.getPosY() + (delta[1] * i);
   if (!this.withinBoard(currentX, currentY)) {
    break;
   }

   Square currentSquare = Hooks.getSquare(currentX, currentY);

   // Capturing logic
   if (currentSquare.hasPiece()) {
    // Same team, unable to capture
    if (currentSquare.getPiece().getColor() == this.color) {
     break;
    }

    // Cannot eat two pieces in the same move
    if (alreadyEating) {
     break;
    }

    Integer captureX = currentSquare.getPosX() + delta[0];
    Integer captureY = currentSquare.getPosY() + delta[1];

    // Outside of the board, invalid move
    if (!this.withinBoard(captureX, captureY)) {
     break;
    }

    Square destination = Hooks.getSquare(captureX, captureY);

    // Next square also has a piece, unable to move
    if (destination.hasPiece()) {
     break;
    }

    validList.add(destination);
    alreadyEating = true;
    i += 1;

    continue;
   }

   Integer colorOffset = this.color == Piece.WHITE ? -1 : 1;
   // Normal piece trying to move backwards
   if (!this.isKing && delta[1] != colorOffset) {
    break;
   }

   validList.add(currentSquare);
   continue;
  }
 }

 private Boolean withinBoard(Integer x, Integer y) {
  return (x >= 0 && x < Const.size) && (y >= 0 && y < Const.size);
 }
}
