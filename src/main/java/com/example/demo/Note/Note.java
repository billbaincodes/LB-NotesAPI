package com.example.demo.Note;

public class Note {
	
    private long id;
    private String body;
    
    public Note() {
    	
    }

    public Note(long id, String body) {
        this.id = id;
        this.body = body;
    }

    public void setId(long id) {
		this.id = id;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }



}
