package com.example.demo.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
	
	private List<Note> notes = new ArrayList<>(Arrays.asList(
			new Note(1, "Ask Larry about the TPS reports."))
			);
	private AtomicLong nextId = new AtomicLong(1);
	private Note errorNote = new Note(0, "Error: Note not found!");


    @RequestMapping("/api/greeting")
    public String greet() {
        return "hey";
    }
    
    @GetMapping("/api/notes/{id}")
    public Note getNoteById(@PathVariable("id") long noteId) {
    	for (Note note : notes) {
    		if (note.getId() == noteId) {
    			return note;
    		}
    	}
    	return errorNote;
    }
    
    
    @GetMapping("/api/notes")
    public List<Note> note(@RequestParam(value="query", defaultValue="") String queryValue) {
    	final List<Note> matchingNotes = new ArrayList<>();
    	for (Note note : notes) {
    		if (note.getBody().contains(queryValue)) {
    			matchingNotes.add(note);
    		}
    	}
    	return matchingNotes;
    }
    
    @PostMapping("/api/notes")
    public Note createNote(@RequestBody Note note) {
    	note.setId(nextId.incrementAndGet());
    	notes.add(note);
    	return note;
    }
    
    @PutMapping("/api/notes/{id}")
    public Note updateNoteById(@PathVariable("id") long noteId, @RequestBody Note noteUpdate){
    	for (Note note: notes) {
    		if (note.getId() == noteId) {
    			notes.remove(note);
    			noteUpdate.setId(noteId);
    			notes.add(noteUpdate);
    			return noteUpdate;
    		}
    	}
    	return errorNote;
    }
    
    @DeleteMapping("/api/notes/{id}")
    public Note deleteNoteById(@PathVariable("id") long noteId) {
    	for (Note note: notes) {
    		if (note.getId() == noteId) {
    			notes.remove(note);
    			return note;
    		}
    	}
    	return errorNote;
    }
    
}