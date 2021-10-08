package com.hyoretsu.checkers.util;

import java.util.List;

import com.hyoretsu.checkers.Board;
import com.hyoretsu.checkers.Piece;
import com.hyoretsu.checkers.Square;
import com.hyoretsu.checkers.gui.SquareGUI;

public class Hooks {
 private static Board board;

 public Hooks(Board gameBoard) {
  board = gameBoard;
 }

 public static void decreaseTeamCount(Piece piece) {
  board.decreaseTeamCount(piece);

  return;
 }

 public static Piece getPiece(SquareGUI squareGUI) {
  Square square = board.getSquare(squareGUI.getPosX(), squareGUI.getPosY());

  return square.getPiece();
 }

 public static Square getSquare(Integer x, Integer y) {
  return board.getSquare(x, y);
 }

 public static Square getSquare(SquareGUI squareGUI) {
  return board.getSquare(squareGUI.getPosX(), squareGUI.getPosY());
 }

 public static Integer getTurn() {
  return board.getTurn();
 }

 public static Boolean hasPiece(SquareGUI squareGUI) {
  Square square = board.getSquare(squareGUI.getPosX(), squareGUI.getPosY());

  return square.hasPiece();
 }

 public static void nextTurn() {
  board.nextTurn();

  return;
 }

 public static List<Square> validMoves(SquareGUI destination) {
  Piece piece = board.getSquare(destination.getPosX(), destination.getPosY()).getPiece();

  return piece.validMoves();
 }
}
