package com.bridgelabz.notesservices.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.notesservices.customexception.NoteNotFoundException;
import com.bridgelabz.notesservices.dto.NoteDto;
import com.bridgelabz.notesservices.dto.NoteUpdate;
import com.bridgelabz.notesservices.model.NotesEntity;
import com.bridgelabz.notesservices.repository.INoteRepository;
@Service
public class NoteServiceImp implements INoteServices {
  private NotesEntity notes=new NotesEntity();
  @Autowired
  private INoteRepository noteRepository;
	@Override
	public boolean createNote(NoteDto noteDto,String token, long userId) {
		BeanUtils.copyProperties(noteDto, notes);
		notes.setUpdateDate(LocalDateTime.now());
		notes.setArchieved(false);
		notes.setTrashed(false);
		notes.setPinned(false);
		notes.setColor("white");
		notes.setUserId(userId);
		noteRepository.createNote(notes);
		return true;
	}

	@Override
	public boolean updateNote(long notesId, NoteUpdate updateNote) {
		
		NotesEntity note=noteRepository.findBynotesId(notesId);
		if(note!=null) {
			note.setTitle(updateNote.getTitle());
			note.setTitle(updateNote.getTitle());
			note.setDescription(updateNote.getDescription());
			note.setUpdateDate(LocalDateTime.now());
			noteRepository.createNote(note);
			return true;
		}else {

			throw  new NoteNotFoundException("Note Not Found" , HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public boolean deleteNote(long notesId) {
		NotesEntity note=noteRepository.findBynotesId(notesId);
		if(note!=null) 
		{
		  noteRepository.deleteNote(notesId, note);
			return true;
		
		}
		else {

			throw  new NoteNotFoundException("Note Not Found" , HttpStatus.NOT_FOUND);
		}
	
	}

	@Override
	public List<NotesEntity> getAllnotes(long userId) {
		List<NotesEntity> note = noteRepository.getAllNotes(userId);	
      return note;
	}

}
