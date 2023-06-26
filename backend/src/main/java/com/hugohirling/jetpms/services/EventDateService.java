package com.hugohirling.jetpms.services;

import com.hugohirling.jetpms.entities.EventDate;
import com.hugohirling.jetpms.repositories.EventDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventDateService {

    @Autowired
    private EventDateRepository eventDateRepository;

    public List<EventDate> getAll() {
        List<EventDate> list = new ArrayList<>();
        eventDateRepository.findAll().forEach(list::add);
        return list;
    }

    public Optional<EventDate> get(final int id) {
        return eventDateRepository.findById(id);
    }

    public void addNew(final EventDate eventDate) {
        eventDateRepository.save(eventDate);
    }
}
