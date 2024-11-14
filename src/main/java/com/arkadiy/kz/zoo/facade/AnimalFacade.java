package com.arkadiy.kz.zoo.facade;

import com.arkadiy.kz.zoo.dto.AnimalDTO;
import com.arkadiy.kz.zoo.entity.Animal;
import org.springframework.stereotype.Component;

@Component
public class AnimalFacade {
    public AnimalDTO animalToAnimalDTO(Animal animal) {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animal.getId());
        animalDTO.setName(animal.getName());
        animalDTO.setAge(animal.getAge());
        animalDTO.setType(animal.getType());

        return animalDTO;
    }
}
