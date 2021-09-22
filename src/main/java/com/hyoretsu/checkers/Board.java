package com.hyoretsu.checkers;

/** Game board (with 64 positions) */
public class Board {
 private Square[][] squares = new Square[8][8];

 public Board() {
  for (Integer x = 0; x < 8; x++) {
   for (Integer y = 0; y < 8; y++) {
    Square square = new Square(x, y);
    this.squares[x][y] = square;
   }
  }
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
}
