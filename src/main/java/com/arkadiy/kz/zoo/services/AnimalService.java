package com.arkadiy.kz.zoo.services;

import com.arkadiy.kz.zoo.dto.AnimalDTO;
import com.arkadiy.kz.zoo.entity.Animal;
import com.arkadiy.kz.zoo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal createAnimal(AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setName(animalDTO.getName());
        animal.setType(animalDTO.getType());
        animal.setAge(animalDTO.getAge());

        return animalRepository.save(animal);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findAnimalById(id)
                .orElseThrow();
    }

    public Animal getAnimalByName(String name) {
        return animalRepository.findAnimalByName(name)
                .orElseThrow();
    }

    public Animal getAnimalByType(String type) {
        return animalRepository.findAnimalByType(type)
                .orElseThrow();
    }

    public void deleteAnimal(Long id) {
        animalRepository.findAnimalById(id);
    }
}
