package com.hyoretsu.checkers.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.hyoretsu.checkers.Piece;
import com.hyoretsu.checkers.Square;

/** GUI of a square */
public class SquareGUI extends JButton {
 // Board Colors
 public static final Color COLOR_LIGHT = new Color(182, 155, 76);
 public static final Color COLOR_DARK = new Color(65, 41, 1);
 private static final Color COLOR_SELECTED = new Color(0, 1, 0, 0.4f);
 // Icons
 public final Icon RED_MAN = new ImageIcon("assets/red_man.png");
 public final Icon RED_KING = new ImageIcon("assets/red_king.png");
 public final Icon WHITE_MAN = new ImageIcon("assets/white_man.png");
 public final Icon WHITE_KING = new ImageIcon("assets/white_king.png");
 // Piece colors index
 public static final Integer NO_PIECE = -1;
 public static final Integer WHITE_PIECE = 0;
 public static final Integer RED_PIECE = 1;

 private Integer x;
 private Integer y;
 private Square square;
 private Color color;

 public SquareGUI(Square square, Color tileColor, Window window) {
  this.square = square;
  this.x = square.getX();
  this.y = square.getY();
  this.color = tileColor;

  // Layout and color
  setBackground(color);
  setContentAreaFilled(false);
  setBorder(BorderFactory.createLineBorder(color, 1));

  addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    window.respond((SquareGUI) e.getSource());
   }
  });
 }

 public void deselect() {
  setBackground(this.color);
 }

 public void draw(Icon pieceType) {
  setIcon(pieceType);
 }

 public Piece getPiece() {
  return this.square.getPiece();
 }

 public Integer getPieceColor() {
  Icon icon = getIcon();

  if (icon == WHITE_MAN || icon == WHITE_KING) {
   return WHITE_PIECE;
  } else if (icon == RED_MAN || icon == RED_KING) {
   return RED_PIECE;
  } else {
   return NO_PIECE;
  }
 }

 public Integer getPosX() {
  return this.x;
 }

 public Integer getPosY() {
  return this.y;
 }

 public Square getSquare() {
  return this.square;
 }

 public boolean hasPiece() {
  return getIcon() != null;
 }

 public void removePiece() {
  setIcon(null);
 }

 public void select() {
  setBackground(COLOR_SELECTED);
 }

 /** Paints the component with the BGM. Accepts RGBA. */
 @Override
 protected void paintComponent(Graphics g) {
  g.setColor(getBackground());
  g.fillRect(0, 0, getWidth(), getHeight());
  super.paintComponent(g);
 }
}
