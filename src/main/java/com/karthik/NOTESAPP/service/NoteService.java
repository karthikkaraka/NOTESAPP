package com.karthik.NOTESAPP.service;

import com.karthik.NOTESAPP.model.Note;
import com.karthik.NOTESAPP.model.User;
import com.karthik.NOTESAPP.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class NoteService {
    @Autowired
    private NoteRepo noteRepo;
    public void savenote( Note note) {
        noteRepo.save(note);
    }

    public List<Note> getAllNotes(Integer id) {
       return  noteRepo.findAllNotes(id);
    }

    public Optional<Note> findnoteById(Long id) {
        return noteRepo.findById(id);
    }

    public Note updatenote(Note originalnote) {
        return noteRepo.save(originalnote);
    }

    public String deletenote(User user, Long id) {
       Optional<Note> optionalnote = noteRepo.findById(id);
       Note originalnote = null;
       if(optionalnote.isPresent())
       {
           originalnote = optionalnote.get();
       }
       if(originalnote.getUser().getId()==user.getId())
       {
           noteRepo.delete(originalnote);
           return "deleted the note titled as"+originalnote.getTitle();
       }
       else{
           return "not deleted...failure";
       }
    }

    public Note findNoteBytitle(String title) {
       Note note =  noteRepo.findBytitle(title);
        System.out.println(note);
       return note;
    }
}
