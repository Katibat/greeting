package com.example.greeting.storage;

import com.example.greeting.entities.Greeting;

import java.util.Collection;

public interface GreetingStorage {
//    Greeting saveGreeting(Greeting greeting);
    void saveGreeting(Greeting greeting);
    Greeting getGreetingById(Long id);
    Collection<Greeting> getAllGreetings();
}
