package com.arkadiy.kz.zoo.services;

import com.arkadiy.kz.zoo.entity.Animal;
import com.arkadiy.kz.zoo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public AnimalService(AnimalRepository animalRepository, KafkaProducerService kafkaProducerService) {
        this.animalRepository = animalRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Animal createAnimal(Animal animal) {
        Animal newAnimal = animalRepository.save(animal);
        kafkaProducerService.sendMessage("Animal added: " + newAnimal);
        return newAnimal;
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Животное не найдено"));
    }

    public Animal updateAnimal(Long id, Animal newAnimal) {
        return animalRepository.findById(id).map(animal -> {
            animal.setName(newAnimal.getName());
            animal.setAge(newAnimal.getAge());
            animal.setType(newAnimal.getType());
            Animal updatedAnimal = animalRepository.save(animal);
            kafkaProducerService.sendMessage("Animal updated: " + updatedAnimal);
            return updatedAnimal;
        }).orElseThrow(() -> new RuntimeException("Животное не найдено"));
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
        kafkaProducerService.sendMessage("Animal deleted with id: " + id);
    }
}
