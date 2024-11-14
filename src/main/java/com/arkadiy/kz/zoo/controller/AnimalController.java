package com.arkadiy.kz.zoo.controller;

import com.arkadiy.kz.zoo.entity.Animal;
import com.arkadiy.kz.zoo.services.AnimalService;
import com.arkadiy.kz.zoo.validation.ResponseErrorValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("animal")
@CrossOrigin
public class AnimalController {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("")
    private ResponseEntity<Object> createAnimal(@Valid @RequestBody Animal animalNew, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (errors != null && errors.hasBody()) return errors;

        Animal animal = animalService.createAnimal(animalNew);

        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @GetMapping("")
    private ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animalList = animalService.getAllAnimals();

        return new ResponseEntity<>(animalList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Animal> getAnimalById(@PathVariable("id") String id) {
        Animal animal = animalService.getAnimalById(Long.parseLong(id));

        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    private ResponseEntity<Object> updateAnimal(@PathVariable("id") String id, @Valid @RequestBody Animal animal, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (errors != null && errors.hasBody()) return errors;

        animalService.updateAnimal(Long.parseLong(id), animal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Animal> deleteAnimal(@PathVariable("id") String id) {
        animalService.deleteAnimal(Long.parseLong(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
