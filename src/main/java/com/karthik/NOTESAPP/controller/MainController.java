package com.karthik.NOTESAPP.controller;


import com.karthik.NOTESAPP.model.Note;
import com.karthik.NOTESAPP.model.User;
import com.karthik.NOTESAPP.service.NoteService;
import com.karthik.NOTESAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/notesapp")
public class MainController {
    @Autowired
    private NoteService noteservice;
    @Autowired
    UserService userService;
    @PostMapping("createnote")
    public String createnote(Authentication auth, @RequestBody Note note)
    {
        String userName = auth.getName();
        User user = userService.findUser(userName);
        System.out.println(user.getUserName());
        note.setUser(user);
        noteservice.savenote(note);
        return note.getContent();
    }
    @GetMapping("getallnotes")
    public List<Note> getallNotes(Authentication auth)
    {
        String userName = auth.getName();
        User user = userService.findUser(userName);
        Integer id  = user.getId();
        return noteservice.getAllNotes(id);
    }
    @PutMapping("updatenote/{id}")
    public Note updatenote(Authentication auth,@PathVariable("id") Long id,@RequestBody Note note)
    {
        String username = auth.getName();
        User user = userService.findUser(username);
        Note originalnote = null;
        Optional<Note> presentnote = noteservice.findnoteById(id);
        if(presentnote.isPresent())
        {
             originalnote = presentnote.get();

        }
        else{
            System.out.println("not found");
        }
        if(originalnote.getUser().getId()==user.getId())
        {
            originalnote.setTitle(note.getTitle());
            originalnote.setContent(note.getContent());
            return noteservice.updatenote(originalnote);
        }
        else{
            return note;
        }
    }
    @DeleteMapping("deletenote/{id}")
    public String deletenote( @PathVariable("id") Long id,Authentication auth)
    {
        System.out.println("entery method");
        String username = auth.getName();
        User user =userService.findUser(username);
         return noteservice.deletenote(user,id);
    }
}
