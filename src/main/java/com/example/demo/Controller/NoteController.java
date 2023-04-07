package com.example.demo.Controller;

import com.example.demo.Model.NoteModel;
import com.example.demo.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @PostMapping("/post")
    public ResponseEntity<NoteModel> addNote(@RequestBody NoteModel note) {
        NoteModel savedNote = noteService.addNote(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }
        @GetMapping("/get")
        public ResponseEntity<List<NoteModel>> getNote () {
            return new ResponseEntity<List<NoteModel>>(noteService.getAllNotes(), HttpStatus.OK);
        }

        @PutMapping("/put/{id}")
        public ResponseEntity<NoteModel> updatedNote (@PathVariable String id, @RequestBody NoteModel updatedNote){
            NoteModel note = noteService.updateNote(id, updatedNote);
            if (note == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(note, HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteNote (@PathVariable String id){
            String message = noteService.deleteNote(id);
            return ResponseEntity.ok().body(message);
        }


}
/**
 *     public ResponseEntity<User> addUser(@RequestBody User user) {
 *         User savedUser = userService.addUser(user);
 *         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
 *     }*/

