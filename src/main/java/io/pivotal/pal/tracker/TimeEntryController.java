package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;

    }

   @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

       TimeEntry newTimeEntry = timeEntryRepository.create(timeEntryToCreate);
       return new ResponseEntity(newTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {

        TimeEntry newTimeEntry = timeEntryRepository.find(timeEntryId);
        if(newTimeEntry == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else return ResponseEntity.ok(newTimeEntry);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return  ResponseEntity.ok(timeEntryRepository.list());
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry newEntry = timeEntryRepository.update(timeEntryId, expected);
        if (newEntry == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(newEntry);

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable ("id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
