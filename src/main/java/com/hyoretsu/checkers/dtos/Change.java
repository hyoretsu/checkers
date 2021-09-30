package com.hyoretsu.checkers.dtos;

import com.hyoretsu.checkers.Square;

public class Change {
 public Square origin;
 public String action;
 public Square destination;

 public Change(Square origin, String action) {
  this.origin = origin;
  this.action = action;
 }

 public Change(Square origin, String action, Square destination) {
  this.origin = origin;
  this.action = action;
  this.destination = destination;
 }
}
