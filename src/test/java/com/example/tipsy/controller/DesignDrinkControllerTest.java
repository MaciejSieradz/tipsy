package com.example.tipsy.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.tipsy.domain.Ingredient;

@ExtendWith(MockitoExtension.class)
public class DesignDrinkControllerTest {

  @InjectMocks
  private DesignDrinkController controller;

  private List<Ingredient> ingredients;

  @BeforeEach
  void setUp() {
    ingredients = Arrays.asList(
        new Ingredient("Vodka", Ingredient.Type.ALCOHOL),
        new Ingredient("Rum", Ingredient.Type.ALCOHOL),
        new Ingredient("Gin", Ingredient.Type.ALCOHOL),
        new Ingredient("Apple juice", Ingredient.Type.JUICE),
        new Ingredient("Orange juice", Ingredient.Type.JUICE),
        new Ingredient("Lime juice", Ingredient.Type.JUICE));
  }

  @Test
  void filterByType_shouldReturnAlcoholIngredients_whenAlcoholTypeProvided() {
    var result = controller.filterByType(ingredients, Ingredient.Type.ALCOHOL);

    assertThat(result).hasSize(3);
    assertThat(result).extracting("name")
        .containsExactlyInAnyOrder("Vodka", "Rum", "Gin");
    assertThat(result).allMatch(ingredient -> ingredient.type().equals(Ingredient.Type.ALCOHOL));
  }

  @Test
  void filterByType_shouldReturnJuiceIngredients_whenJuiceTypeProvided() {
    var result = controller.filterByType(ingredients, Ingredient.Type.JUICE);

    assertThat(result).hasSize(3);
    assertThat(result).extracting("name")
        .containsExactlyInAnyOrder("Apple juice", "Orange juice", "Lime juice");
    assertThat(result).allMatch(ingredient -> ingredient.type().equals(Ingredient.Type.JUICE));
  }

  @Test
  void filterByType_shouldReturnEmptyList_whenTypeHasNoMatches() {
    var result = controller.filterByType(ingredients, Ingredient.Type.SOUP);

    assertThat(result).isEmpty();
  }

  @Test
  void filterByType_shouldReturnEmptyList_whenIngredientsListIsEmpty() {
    List<Ingredient> emptyList = List.of();

    var result = controller.filterByType(emptyList, Ingredient.Type.SOUP);

    assertThat(result).isEmpty();
  }
}
