package com.karthik.NOTESAPP.controller;


import com.karthik.NOTESAPP.model.Note;
import com.karthik.NOTESAPP.model.User;
import com.karthik.NOTESAPP.service.NoteService;
import com.karthik.NOTESAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/notesapp")
@CrossOrigin(
        origins = "http://localhost:5173",
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class MainController {
    @Autowired
    private NoteService noteservice;
    @Autowired
    UserService userService;
    @PostMapping("createnote")
    public ResponseEntity<String> createnote(Authentication auth, @RequestBody Note note)
    {
        String userName = auth.getName();
        User user = userService.findUser(userName);
        System.out.println(user.getUserName());
        note.setUser(user);
        noteservice.savenote(note);
        return new ResponseEntity<>(note.getTitle(), HttpStatus.CREATED);
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
        Optional<Note> presentnot = noteservice.findnoteById(id);
        if(presentnot.isPresent())
        {
             originalnote = presentnot.get();

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
    @GetMapping("/findnote/title/{title}")
    public ResponseEntity<Note>findNoteByTitle(@PathVariable String title){
        Note notes = noteservice.findNoteBytitle(title);
        if(notes!=null){
            return new ResponseEntity<>(notes,HttpStatus.ACCEPTED) ;
        }
      else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("findnote/{id}")
    public ResponseEntity<Note> findNoteById(@PathVariable long id){
        Optional<Note> note =  noteservice.findnoteById(id);
        Note orinote = new Note();
        if(note.isPresent()) {
            orinote = note.get();
        }else{
            orinote = null;
        }
         return new ResponseEntity<>(orinote,HttpStatus.OK);
    }
}
