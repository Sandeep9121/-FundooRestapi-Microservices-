package com.bridgelabz.notesservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.notesservices.dto.NoteDto;
import com.bridgelabz.notesservices.dto.NoteUpdate;
import com.bridgelabz.notesservices.dto.UsersEntity;
import com.bridgelabz.notesservices.model.NotesEntity;
import com.bridgelabz.notesservices.response.Response;
import com.bridgelabz.notesservices.services.INoteServices;

import lombok.extern.slf4j.Slf4j;
@RestController

@Slf4j
public class NotesController {
	@Autowired
	private INoteServices noteService;
	
	@Autowired
	private RestTemplate restTemplate;
//	@Value("${note.getUser}")
	//private String getUser;
//	public User getUser(String token) {
//		
//		User user = restTemplate.getForObject("http://user-service/users/getUser/"+token ,User.class)
//		return user;
//	}


	
	
	@PostMapping("/notes/create")
	public ResponseEntity<Response> createNotes(@RequestBody NoteDto noteDto , @RequestHeader String token)
	{

		
		UsersEntity user = restTemplate.getForObject("http://User-Services/users/getUser/"+token,UsersEntity.class);

		if(user!=null)
		{
		noteService.createNote(noteDto , token ,user.getUserId());
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Note created ", noteDto));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(new Response("user not found"));
		
	}


	
	@DeleteMapping("/notes/deletePermanently/{noteId}")
	public ResponseEntity<Response> deleteNoteParmanently(@PathVariable long notesId , @RequestHeader String token)
	{
	UsersEntity user = restTemplate.getForObject("http://User-Services/users/getUser/"+token , UsersEntity.class);
		if(user!=null)
		{
		noteService.deleteNote(notesId);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("note deleted success"));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(new Response("user not found"));
		}
		

	/**
	 * Api to update note
	 * @param noteUpdateDto to bind the request object
	 * @param token to get user id
	 * 
	 */
	@PutMapping("/notes/update/{noteId}")
	public ResponseEntity<Response> updateNote(@PathVariable long noteId , @RequestBody NoteUpdate noteUpdateDto , @RequestHeader String token)
	{
		UsersEntity user = restTemplate.getForObject("http://User-Services/users/getUser/"+token , UsersEntity.class);
		if(user!=null)
		{
		noteService.updateNote(noteId , noteUpdateDto);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("update success"));
	}
		else
			return ResponseEntity.status(HttpStatus.OK).body(new Response("user not found"));
	}
	/**
	 * Api to get all notes
	 * @param token to identify user
	 * @return list of notes
	 */
	@GetMapping("/notes/getNotes")
	public ResponseEntity<Response> getAllNotes(@RequestHeader String token)
	{
		UsersEntity user = restTemplate.getForObject("http://User-Services/users/getUser/"+token , UsersEntity.class);
	
		if(user!=null)
		{
		List<NotesEntity> notes = noteService.getAllnotes(user.getUserId());

		if(notes.size()>0) {
			//return ResponseEntity<T>.status(HttpStatus.OK).body(new Response("fetched all notes",notes));
			return  ResponseEntity.status(HttpStatus.OK).body(new Response("fetched all notes",notes));
		}
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("notes not found!! create note",notes));
	
		}
		else
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("user not found!"));
			
		}
}
