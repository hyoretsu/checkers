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

/** GUI of a square */
public class SquareGUI extends JButton {
 // Board Colors
 public static final Color COLOR_LIGHT = new Color(182, 155, 76);
 public static final Color COLOR_DARK = new Color(65, 41, 1);
 private static final Color COLOR_SELECTED = new Color(0, 1, 0, 0.4f);
 // Icons
 private final Icon[] icons;

 private Integer x;
 private Integer y;
 private Color color;

 public SquareGUI(Integer x, Integer y, Color tileColor, Window window) {
  this.x = x;
  this.y = y;
  this.color = tileColor;

  this.icons = new Icon[4];
  this.icons[0] = new ImageIcon("assets/white_man.png");
  this.icons[1] = new ImageIcon("assets/red_man.png");
  this.icons[2] = new ImageIcon("assets/white_king.png");
  this.icons[3] = new ImageIcon("assets/red_king.png");

  // Layout and color
  setBackground(this.color);
  setContentAreaFilled(false);
  setBorder(BorderFactory.createLineBorder(this.color, 1));

  addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    window.respond((SquareGUI) e.getSource());

    return;
   }
  });
 }

 public void deselect() {
  setBackground(this.color);

  return;
 }

 public void draw(Piece piece) {
  Integer colorIndex = piece.getColor() + ((piece.isKing() ? 1 : 0) * 2);
  setIcon(this.icons[colorIndex]);

  return;
 }

 public Integer getPosX() {
  return this.x;
 }

 public Integer getPosY() {
  return this.y;
 }

 public void removePiece() {
  setIcon(null);

  return;
 }

 public void select() {
  setBackground(COLOR_SELECTED);

  return;
 }

 /** Paints the component with the BGM. Accepts RGBA. */
 @Override
 protected void paintComponent(Graphics g) {
  g.setColor(getBackground());
  g.fillRect(0, 0, getWidth(), getHeight());
  super.paintComponent(g);
 }
}
