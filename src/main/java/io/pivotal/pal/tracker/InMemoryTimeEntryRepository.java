package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> memory;
    private long currentId = 1L;


    public TimeEntry create(TimeEntry timeEntry) {
        long id = currentId++;
        TimeEntry timeEntry1 = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        memory.put(id, timeEntry1);

        return timeEntry1;
    }

    @Override
    public TimeEntry find(long timeEntryId) {

        return memory.get(timeEntryId);
    }


    public List<TimeEntry> list() {
        return new ArrayList<>(memory.values());
    }

    public InMemoryTimeEntryRepository() {
        this.memory = new HashMap<Long, TimeEntry>();
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {


        TimeEntry newTimeEntry = memory.get(id);

        if (newTimeEntry == null) return null;

        newTimeEntry.setDate(timeEntry.getDate());
        newTimeEntry.setHours(timeEntry.getHours());
        newTimeEntry.setProjectId(timeEntry.getProjectId());
        newTimeEntry.setUserId(timeEntry.getUserId());

        memory.put(id, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public void delete(long timeEntryId) {
        memory.remove(timeEntryId);

    }

}
