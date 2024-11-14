package com.arkadiy.kz.zoo.controller;

import com.arkadiy.kz.zoo.dto.AnimalDTO;
import com.arkadiy.kz.zoo.entity.Animal;
import com.arkadiy.kz.zoo.facade.AnimalFacade;
import com.arkadiy.kz.zoo.repository.AnimalRepository;
import com.arkadiy.kz.zoo.services.AnimalService;
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
    private AnimalFacade animalFacade;
    @Autowired
    private AnimalService animalService;

    @PostMapping("/create")
    private ResponseEntity<Object> createAnimal(@Valid @RequestBody AnimalDTO animalDTO, BindingResult bindingResult) {
        Animal animal = animalService.createAnimal(animalDTO);
        AnimalDTO createdAnimal = animalFacade.animalToAnimalDTO(animal);

        return new ResponseEntity<>(createdAnimal, HttpStatus.OK);
    }

    @GetMapping("/all")
    private ResponseEntity<List<AnimalDTO>> getAllAnimals() {
        List<AnimalDTO> animalDTOList = animalService.getAllAnimals().stream().map(animalFacade::animalToAnimalDTO).toList();

        return new ResponseEntity<>(animalDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<AnimalDTO> getAnimalById(@PathVariable("id") String id) {
        Animal animal = animalService.getAnimalById(Long.parseLong(id));
        AnimalDTO animalDTO = animalFacade.animalToAnimalDTO(animal);

        return new ResponseEntity<>(animalDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{name}")
    private ResponseEntity<AnimalDTO> getAnimalByName(@PathVariable("name") String name) {
        Animal animal = animalService.getAnimalByName(name);
        AnimalDTO animalDTO = animalFacade.animalToAnimalDTO(animal);

        return new ResponseEntity<>(animalDTO, HttpStatus.OK);
    }

    @GetMapping("/gettype/{type}")
    private ResponseEntity<AnimalDTO> getAnimalByType(@PathVariable("type") String type) {
        Animal animal = animalService.getAnimalByType(type);
        AnimalDTO animalDTO = animalFacade.animalToAnimalDTO(animal);

        return new ResponseEntity<>(animalDTO, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    private ResponseEntity<AnimalDTO> deleteAnimal(@PathVariable("id") String id) {
        animalService.deleteAnimal(Long.parseLong(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
