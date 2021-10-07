package com.hyoretsu.checkers;

import com.hyoretsu.checkers.gui.Window;
import com.hyoretsu.checkers.util.Const;
import com.hyoretsu.checkers.util.Hooks;

/** Stores the board and each piece's position */
public class Game {
 private Board board = new Board(Const.size);

 private Game() {
  new Hooks(this.board);
  new Window();
 }

 public static void main(String[] args) {
  new Game();
 }
}
