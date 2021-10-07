package com.hyoretsu.checkers;

import java.util.Arrays;
import java.util.List;

/** Game board (with 64 positions) */
public class Board {
 private Square[][] squares;
 /** Same value as team color, 0 for White or 1 for Red */
 private Integer turn;

 public Board(Integer dimensions) {
  this.squares = new Square[dimensions][dimensions];
  this.turn = 0;

  for (int x = 0; x < dimensions; x++) {
   for (int y = 0; y < dimensions; y++) {
    this.squares[x][y] = new Square(x, y);
   }
  }

  this.createPieces();
 }

 /** Creates initial pieces */
 private void createPieces() {
  List<Integer> redRows = Arrays.asList(0, 1, 2);
  List<Integer> whiteRows = Arrays.asList(5, 6, 7);
  List<Integer> evenX = Arrays.asList(0, 2, 4, 6);
  List<Integer> oddX = Arrays.asList(1, 3, 5, 7);

  whiteRows.forEach(y -> {
   if (y % 2 == 0) {
    oddX.forEach(x -> new Piece(this.squares[x][y], Piece.WHITE));
   } else {
    evenX.forEach(x -> new Piece(this.squares[x][y], Piece.WHITE));
   }
  });
  redRows.forEach(y -> {
   if (y % 2 == 0) {
    oddX.forEach(x -> new Piece(this.squares[x][y], Piece.RED));
   } else {
    evenX.forEach(x -> new Piece(this.squares[x][y], Piece.RED));
   }
  });

  return;
 }

 /**
  * @param x line
  * @param y column
  *
  * @return Square at coordinate (x, y)
  */
 public Square getSquare(Integer x, Integer y) {
  return this.squares[x][y];
 }

 public Integer getTurn() {
  return this.turn;
 }

 public void nextTurn() {
  // Switch turn
  if (this.turn == 0) {
   this.turn = 1;
  } else {
   this.turn = 0;
  }

  return;
 }
}
