package com.bridgelabz.notesservices.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.notesservices.model.NotesEntity;


@Repository
public class NotesRepository implements INoteRepository {
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public NotesEntity createNote(NotesEntity note) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(note);
		return note;
	}

	@Transactional
	public NotesEntity findBynotesId(long notesId) {
		Session session = entityManager.unwrap(Session.class);
		Query<?> q = session.createQuery("from NotesEntity where notesId=:notesId",NotesEntity.class);
		q.setParameter("notesId", notesId);
		return (NotesEntity) q.uniqueResult();
	}

	@Override
	@Transactional
	public int deleteNote(long notesId, NotesEntity note) {
		Session session = entityManager.unwrap(Session.class);
		Query<?> q = session.createQuery("delete from NotesEntity where notesId=:notesId",NotesEntity.class);
		q.setParameter("notesId", notesId);
		return q.executeUpdate();
	}

	@Transactional
	public boolean setTrashed(String token, long notesId) {
		Session session = entityManager.unwrap(Session.class);
		NotesEntity notes = findBynotesId(notesId);
	if (!notes.isTrashed() && !notes.isPinned()) {
		notes.setTrashed(true);
 notes.setUpdateDate(LocalDateTime.now());
notes.setNotesCreatedDate(LocalDateTime.now());
		session.save(notes);	
		return true;}
		return false;
	}

	@Transactional
	public boolean setRestored(String token, long notesId) {
		Session session = entityManager.unwrap(Session.class);
		NotesEntity notes = findBynotesId(notesId);
		if (notes.isTrashed()) {
			notes.setTrashed(false);
		notes.setNotesCreatedDate(LocalDateTime.now());
		session.saveOrUpdate(notes);
		return true;
	}

		return false;
	}

	@Transactional
	public List<NotesEntity> getAllNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
		List list = session.createQuery("from NotesEntity where user_id='" + userId + "'").getResultList();
		//Query<NotesEntity> q = session.createQuery("from NotesEntity where user_id=id",NotesEntity.class);
		//q.setParameter("", value)
		return list;//q.getResultList();
	}

	@Override
	public List<NotesEntity> getTrashed(long userId) {
		Session session=entityManager.unwrap(Session.class);
		List list =  session.createQuery("from NotesEntity where user_id='" + userId + "'"+"and is_trashed=true").getResultList();
		return list;
	}

	@Override
	public List<NotesEntity> getArchieved(long userId) {
		Session session=entityManager.unwrap(Session.class);
		List list =  session.createQuery("from NotesEntity where user_id='" + userId + "'"+" and  is_archieved=true").getResultList();
		return list;
	}

	@Override
	public List<NotesEntity> getPinned(Long userId) {
		Session session=entityManager.unwrap(Session.class);
		List list =  session.createQuery("from NotesEntity where user_id='" + userId + "'"+" and  is_pinned=true").getResultList();
		return list;
	}

	@Override
	public List<NotesEntity> fetchByTitle(String title, long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query<NotesEntity> query = session.createQuery("from NotesEntity where title=:title and user_id=:userId" , NotesEntity.class);
		query.setParameter("title", title);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
	
/*	@Transactional
	public List<NotesEntity> getAllNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
    		/*Query<?> q = session.createQuery(" from NotesEntity where notesId='"+userId+"'"+"and  is_trashed=false and  is_archieved=false ORDER BY notes_id DESC");
		return (List<NotesEntity>) q.getResultList();*/
	//	return session.createQuery(" from NotesEntity where notesId='"+userId+"'"+"and  is_trashed=false and  is_archieved=false ORDER BY notes_id DESC").getResultList();
	//}*/





}