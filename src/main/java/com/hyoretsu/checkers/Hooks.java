package com.hyoretsu.checkers;

import java.util.List;

import com.hyoretsu.checkers.gui.SquareGUI;

public class Hooks {
 private static Board board;
 public static volatile Hooks instance;

 private Hooks() {
 }

 public Hooks(Board gameBoard) {
  board = gameBoard;
  instance = new Hooks();
 }

 public static Piece getPiece(SquareGUI squareGUI) {
  return board.getSquare(squareGUI.getPosX(), squareGUI.getPosY()).getPiece();
 }

 public static Square getSquare(Integer x, Integer y) {
  return board.getSquare(x, y);
 }

 public static Square getSquare(SquareGUI squareGUI) {
  return board.getSquare(squareGUI.getPosX(), squareGUI.getPosY());
 }

 public static Boolean hasPiece(SquareGUI squareGUI) {
  return board.getSquare(squareGUI.getPosX(), squareGUI.getPosY()).hasPiece();
 }

 public static List<Square> validMoves(SquareGUI destination) {
  Square square = board.getSquare(destination.getPosX(), destination.getPosY());

  return board.validMoves(square);
 }
}
