package com.hyoretsu.checkers;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/** Stores the board and each piece's position */
public class Game {
 private Board board = new Board();

 public Game() {
  createPieces();
 }

 /** Creates the game's initial pieces */
 private void createPieces() {
  List<Integer> evenX = Arrays.asList(0, 2, 4, 6);
  List<Integer> oddX = Arrays.asList(1, 3, 5, 7);
  List<Integer> whiteRows = Arrays.asList(0, 1, 2);
  List<Integer> redRows = Arrays.asList(5, 6, 7);

  BiConsumer<Integer, Integer> placeOddOrEven = (y, color) -> {
   if (y % 2 == 0) {
    evenX.forEach(x -> new Piece(board.getSquare(x, y), color));
   } else {
    oddX.forEach(x -> new Piece(board.getSquare(x, y), color));
   }
  };

  whiteRows.forEach(y -> placeOddOrEven.accept(y, Piece.WHITE));
  redRows.forEach(y -> placeOddOrEven.accept(y, Piece.RED));
 }

 /** @return current board */
 public Board getBoard() {
  return board;
 }

 /**
  * Moves a piece from origin to the given destination.
  *
  * @param originX      Origin square's line
  * @param originY      Origin square's column
  * @param destinationX Destination square's line
  * @param destinationY Destination square's column
  */
 public void movePiece(Integer originX, Integer originY, Integer destinationX, Integer destinationY) {
  Square origin = board.getSquare(originX, originY);
  Square destination = board.getSquare(destinationX, destinationY);
  Piece piece = origin.getPiece();
  piece.move(destination);
 }
}
