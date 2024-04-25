package com.example.greeting.service;

import com.example.greeting.entities.Greeting;
import com.example.greeting.storage.GreetingStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@Qualifier("GreetingDbService")
public class GreetingDbService implements GreetingService {
    private final GreetingStorage storage;

    @Autowired
    public GreetingDbService(@Qualifier("GreetingDbStorage") GreetingStorage storage) {
        this.storage = storage;
    }

    @Override
    public void saveGreeting(Greeting greeting) {
        storage.saveGreeting(greeting);
    }

    @Override
    public Greeting getGreetingById(Long id) {
        return storage.getGreetingById(id);
    }

    @Override
    public Collection<Greeting> getAllGreetings() {
        return storage.getAllGreetings();
    }
}