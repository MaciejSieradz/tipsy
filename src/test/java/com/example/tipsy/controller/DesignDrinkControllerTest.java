package com.example.tipsy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.tipsy.domain.Ingredient;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(DesignDrinkController.class)
public class DesignDrinkControllerTest {

  @Autowired
  private MockMvc mockMvc;

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
  public void testDesignPage() throws Exception {
    mockMvc.perform(get("/design"))
        .andExpect(status().isOk())
        .andExpect(view().name("design"))
        .andExpect(content().string(
        containsString("Design your drink!")
      ));
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
    var result = controller.filterByType(ingredients, Ingredient.Type.SYRUP);

    assertThat(result).isEmpty();
  }

  @Test
  void filterByType_shouldReturnEmptyList_whenIngredientsListIsEmpty() {
    List<Ingredient> emptyList = List.of();

    var result = controller.filterByType(emptyList, Ingredient.Type.SYRUP);

    assertThat(result).isEmpty();
  }
}
