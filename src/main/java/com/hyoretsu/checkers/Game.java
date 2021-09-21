package com.hyoretsu.checkers;

/** Stores the board and each piece's position */
public class Game {
 private Board board;

 public Game() {
  board = new Board();
  createPieces();
 }

 /** Creates the game's initial pieces */
 private void createPieces() {
  Square square1 = board.getSquare(0, 0);
  Piece piece1 = new Piece(square1, Piece.WHITE);

  Square square2 = board.getSquare(7, 7);
  Piece piece2 = new Piece(square2, Piece.RED_KING);
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
 public void movePiece(int originX, int originY, int destinationX, int destinationY) {
  Square origin = board.getSquare(originX, originY);
  Square destination = board.getSquare(destinationX, destinationY);
  Piece piece = origin.getPiece();
  piece.move(destination);
 }
}
