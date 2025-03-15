package com.example.tipsy.domain;

import java.util.List;

public class Drink {

  private String name;

  private List<Ingredient> ingredients;

  public Drink() {
    this.name = null;
    this.ingredients = List.of();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Drink other = (Drink) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (ingredients == null) {
      if (other.ingredients != null)
        return false;
    } else if (!ingredients.equals(other.ingredients))
      return false;
    return true;
  }
}
