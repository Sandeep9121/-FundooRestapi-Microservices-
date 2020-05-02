package com.bridgelabz.notesservices.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.notesservices.model.NotesEntity;
@Service
public interface INoteRepository {

	NotesEntity createNote(NotesEntity note);

	NotesEntity findBynotesId(long notesid);

	int deleteNote(long notesId, NotesEntity note);

	boolean setTrashed(String token, long notesId);

	boolean setRestored(String token, long notesId);
    
	 List<NotesEntity> getAllNotes(long userId);
	 
	 List<NotesEntity> getTrashed(long userId);
	 
	 List<NotesEntity> getArchieved(long userId);

	List<NotesEntity> getPinned(Long userId);

	List<NotesEntity> fetchByTitle(String title, long userId);	 

}