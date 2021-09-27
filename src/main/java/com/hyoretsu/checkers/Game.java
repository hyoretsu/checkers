package com.hyoretsu.checkers;

/** Stores the board and each piece's position */
public class Game {
 private Board board = new Board();

 /** @return current board */
 public Board getBoard() {
  return this.board;
 }
}
