package com.hyoretsu.checkers;

import javax.swing.JOptionPane;

import com.hyoretsu.checkers.gui.Window;
import com.hyoretsu.checkers.util.Const;
import com.hyoretsu.checkers.util.Hooks;

/** Stores the board and each piece's position */
public class Game {
 private static Board board = new Board(Const.size);

 private Game() {
  new Hooks(board);
  new Window();
 }

 public static void main(String[] args) {
  new Game();
 }

 public static void updateTeamCount() {
  Integer[] count = board.getPieceCount();

  if (count[0] == 0 || count[1] == 0) {
   String congratsMessage = "";

   if (count[0] == 0) {
    congratsMessage = "Congratulations! The red team has won the game.";
   } else {
    congratsMessage = "Congratulations! The white team has won the game.";
   }

   JOptionPane.showConfirmDialog(null, congratsMessage, "Game won", JOptionPane.DEFAULT_OPTION,
     JOptionPane.PLAIN_MESSAGE);

   System.exit(0);
  }

  return;
 }
}
