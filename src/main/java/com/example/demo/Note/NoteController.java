package com.example.demo.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
	
	//List to store notes, atomic to manage id's
	private List<Note> notes = new ArrayList<>(Arrays.asList(
		new Note(1, "Ask Larry about the TPS reports."))
	);
	private AtomicLong nextId = new AtomicLong(1);

    
    // GET All notes with query params
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
    
    //GET one note
    @GetMapping("/api/notes/{id}")
    public Note getNoteById(@PathVariable("id") long noteId) {
    	for (Note note : notes) {
    		if (note.getId() == noteId) {
    			return note;
    		}
    	}
    	throw new IllegalArgumentException();
    }
    
    //POST new note
    @PostMapping("/api/notes")
    public Note createNote(@RequestBody Note note) {
    	note.setId(nextId.incrementAndGet());
    	notes.add(note);
    	return note;
    }
    
    //PUT existing note
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
    	throw new IllegalArgumentException();
    }
    
    //DELETE existing note
    @DeleteMapping("/api/notes/{id}")
    public Note deleteNoteById(@PathVariable("id") long noteId) {
    	for (Note note: notes) {
    		if (note.getId() == noteId) {
    			notes.remove(note);
    			return note;
    		}
    	}
    	throw new IllegalArgumentException();
    }
    
    //Bad ID Error Handler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Note ID not found!")
    @ExceptionHandler(IllegalArgumentException.class)
    public void errorHandler() {
    	
    }
    
}