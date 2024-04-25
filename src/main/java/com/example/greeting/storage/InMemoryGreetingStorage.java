package com.example.greeting.storage;

import com.example.greeting.entities.Greeting;
import com.example.greeting.exception.GreetingNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class InMemoryGreetingStorage implements GreetingStorage {
    private final Map<Long, Greeting> greetings;

    @Autowired
    public InMemoryGreetingStorage() {
        greetings = new HashMap<>();
    }

//    @Override
//    public Greeting saveGreeting(Greeting greeting) {
////        for (Greeting g : greetings.values()) {
////            // написать проверку на наличие информации в базе данных
////        }
//        greetings.put(greeting.getId(), greeting);
//        log.info("InMemoryGreetingStorage: Добавлен Greeting: {}", greeting);
//        return greeting;
//    }

    @Override
    public void saveGreeting(Greeting greeting) {
//        for (Greeting g : greetings.values()) {
//            // написать проверку на наличие информации в базе данных
//        }
        greetings.put(greeting.getGreetingId(), greeting);
//        log.info("InMemoryGreetingStorage: Добавлен Greeting: {}", greeting);
    }

    @Override
    public Greeting getGreetingById(Long id) {
        if (greetings.containsKey(id)) {
            return greetings.get(id);
        } else {
            throw new GreetingNotFoundException("InMemoryGreetingStorage: Отсутсвует Greeting с идентификатором " + id);
        }
    }

    @Override
    public Collection<Greeting> getAllGreetings() {
        return new ArrayList<>(greetings.values());
    }
}