package com.hyoretsu.checkers;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/** Stores the board and each piece's position */
public class Game {
 private Board board = new Board();

 public Game() {
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
    evenX.forEach(x -> new Piece(this.board.getSquare(x, y), color));
   } else {
    oddX.forEach(x -> new Piece(this.board.getSquare(x, y), color));
   }
  };

  whiteRows.forEach(y -> placeOddOrEven.accept(y, Piece.WHITE));
  redRows.forEach(y -> placeOddOrEven.accept(y, Piece.RED));
 }

 /** @return current board */
 public Board getBoard() {
  return this.board;
 }

 /**
  * Moves a piece from origin to the given destination.
  *
  * @param origin      Origin square
  * @param destination Destination square
  */
 public void movePiece(Square origin, Square destination) {
  origin.getPiece().move(destination);
 }
}
