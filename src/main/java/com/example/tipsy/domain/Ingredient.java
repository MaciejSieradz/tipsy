package com.example.tipsy.domain;

public record Ingredient(String name, Type type) {
  
  public enum Type {
    ALCOHOL, JUICE, SOUP
  }
}
