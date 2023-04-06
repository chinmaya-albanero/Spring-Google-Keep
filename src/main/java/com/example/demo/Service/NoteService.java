package com.example.demo.Service;

import com.example.demo.Model.NoteModel;
import com.example.demo.Repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;



@Service
public class NoteService {

    Logger logger = LoggerFactory.getLogger(NoteModel.class);
@Autowired
    NoteRepository noteRepository;
    public NoteModel addNote(NoteModel note) {

        NoteModel newNote = noteRepository.save(note);

        return newNote;
    }
    public List<NoteModel> getAllNotes(){

        List<NoteModel> allNote = noteRepository.findAll();

        return allNote;
    }
    public NoteModel updateNote(String id,NoteModel noteModel){
NoteModel note = noteRepository.findById(id).orElse(null);
if(note == null){return  null;}
        note.setTitle(updatedNote.getTitle());
         note.setDescription(updatedNote.getDescription());
         return  noteRepository.save(note);

    }

    public void deleteNote(String id) {
       noteRepository.deleteById(id);
   }

}
