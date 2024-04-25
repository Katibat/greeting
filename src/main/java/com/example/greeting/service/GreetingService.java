package com.example.greeting.service;

import com.example.greeting.entities.Greeting;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface GreetingService {
//    Greeting saveGreeting(Greeting greeting);
    void saveGreeting(Greeting greeting);
    Greeting getGreetingById(Long id);
    Collection<Greeting> getAllGreetings();
}