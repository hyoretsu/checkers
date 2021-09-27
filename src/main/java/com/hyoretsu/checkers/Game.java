package com.hyoretsu.checkers;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/** Stores the board and each piece's position */
public class Game {
 private Board board = new Board();

 public Game() {
  new Hooks(this.board);

  this.createPieces();
 }

 /** Creates the game's initial pieces */
 private void createPieces() {
  List<Integer> evenX = Arrays.asList(0, 2, 4, 6);
  List<Integer> oddX = Arrays.asList(1, 3, 5, 7);
  List<Integer> redRows = Arrays.asList(0, 1, 2);
  List<Integer> whiteRows = Arrays.asList(5, 6, 7);

  BiConsumer<Integer, Integer> placeOddOrEven = (y, color) -> {
   if (y % 2 == 0) {
    oddX.forEach(x -> new Piece(this.board.getSquare(x, y), color));
   } else {
    evenX.forEach(x -> new Piece(this.board.getSquare(x, y), color));
   }
  };

  whiteRows.forEach(y -> placeOddOrEven.accept(y, Piece.WHITE));
  redRows.forEach(y -> placeOddOrEven.accept(y, Piece.RED));
 }
}
