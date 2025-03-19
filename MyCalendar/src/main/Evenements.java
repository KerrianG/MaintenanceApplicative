package main;

import main.Evenement.Event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Evenements implements Iterable<Event> {

    private final List<Event> events;

    public Evenements() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void removeEvent(int index) {
        events.remove(index);
    }

    public void clearEvents() {
        events.clear();
    }

    public Event getEvent(int index) {
        return events.get(index);
    }

    public int size() {
        return events.size();
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }

    public String toString() {
        return events.toString();
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
