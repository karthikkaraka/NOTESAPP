package com.karthik.NOTESAPP.repo;

import com.karthik.NOTESAPP.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoteRepo extends JpaRepository<Note,Long> {
   @Query("select n from Note n where n.user.id =?1")
    List<Note> findAllNotes(Integer id);
    Optional<Note> findById(Long id);
}
