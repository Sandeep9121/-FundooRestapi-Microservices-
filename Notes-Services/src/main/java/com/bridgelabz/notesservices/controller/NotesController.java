package com.bridgelabz.notesservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.notesservices.dto.NoteDto;
import com.bridgelabz.notesservices.response.Response;
import com.bridgelabz.notesservices.services.INoteServices;

public class NotesController {
	@Autowired
	private INoteServices noteService;
	
	@Autowired
	private RestTemplate restTemplate;


	/**
	 * Api to create note
	 * @param noteDto to map with request object
	 * @param token to get user id
	 * 
	 */
	@PostMapping("/notes/create")
	public ResponseEntity<Response> createNotes(@RequestBody NoteDto noteDto , @RequestHeader String token)
	{

		
		UsersEntity user = restTemplate.getForObject("http://Users-Service/users/getUser/"+token,UsersEntity.class);

//		log.info("response obj:"+response.getObject());
//		User user = (User)response.getObject();
		log.info("user got:"+user.getId());
		if(user!=null)
		{
		noteService.createNote(noteDto , token , user.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Note created ", noteDto));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(new Response("user not found"));
		
	}


	/**
	 * Api to delete note permanently
	 * @param noteId to delete note
	 * @param token to get user id
	 * 
	 */
	@DeleteMapping("/notes/deletePermanently/{noteId}")
	public ResponseEntity<Response> deleteNoteParmanently(@PathVariable long noteId , @RequestHeader String token)
	{
	User user = restTemplate.getForObject("http://user-service/users/getUser/"+token , User.class);
		if(user!=null)
		{
		noteService.deleteNotePermanently(noteId);
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
	public ResponseEntity<Response> updateNote(@PathVariable long noteId , @RequestBody NoteUpdateDto noteUpdateDto , @RequestHeader String token)
	{
		User user = restTemplate.getForObject("http://user-service/users/getUser/"+token , User.class);
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
		User user = restTemplate.getForObject("http://user-service/users/getUser/"+token , User.class);
	
		if(user!=null)
		{
		List<NoteEntity> notes = noteService.fetchAllNotes(user.getId());

		if(notes.size()>0)
			return ResponseEntity<T>.status(HttpStatus.OK).body(new Response("fetched all notes",notes));
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("notes not found!! create note",notes));
	
		}
		else
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("user not found!"));
			
		}
}
