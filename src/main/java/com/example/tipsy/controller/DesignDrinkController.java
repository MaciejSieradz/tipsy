package com.example.tipsy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.tipsy.domain.Drink;
import com.example.tipsy.domain.Ingredient;

@Controller
@RequestMapping("/design")
@SessionAttributes("drink")
public class DesignDrinkController {

  private static final Logger log = LoggerFactory.getLogger(DesignDrinkController.class);

  @ModelAttribute
  public void addIngredientsToModel(final Model model) {
    final List<Ingredient> ingredients = Arrays.asList(
        new Ingredient("Vodka", Ingredient.Type.ALCOHOL),
        new Ingredient("Rum", Ingredient.Type.ALCOHOL),
        new Ingredient("Gin", Ingredient.Type.ALCOHOL),
        new Ingredient("Apple juice", Ingredient.Type.JUICE),
        new Ingredient("Orange juice", Ingredient.Type.JUICE),
        new Ingredient("Lime juice", Ingredient.Type.JUICE));

    final var types = Ingredient.Type.values();
    Stream.of(types).forEach(type -> {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    });
  }

  @ModelAttribute(name = "drink")
  public Drink drink() {
    return new Drink();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  Iterable<Ingredient> filterByType(final List<Ingredient> ingredients, final Ingredient.Type type) {

    return ingredients
        .stream()
        .filter(ingredient -> ingredient.type().equals(type))
        .collect(Collectors.toList());

  }
}
