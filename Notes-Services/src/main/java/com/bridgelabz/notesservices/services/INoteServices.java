
package com.bridgelabz.notesservices.services;

import java.util.List;

import com.bridgelabz.notesservices.dto.NoteDto;
import com.bridgelabz.notesservices.dto.NoteUpdate;
import com.bridgelabz.notesservices.model.NotesEntity;

public interface INoteServices {

	boolean createNote(NoteDto noteDto,long userId);

	boolean updateNote(long notesId,NoteUpdate updateNote);

	boolean deleteNote(long notesId);
	
	List<NotesEntity> getAllnotes(long userId);


}