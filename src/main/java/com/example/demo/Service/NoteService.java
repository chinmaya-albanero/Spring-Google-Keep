package com.example.demo.Service;

import com.example.demo.Model.NoteModel;
import com.example.demo.Repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Service
public class NoteService {

    public static final Logger LOGGER = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    NoteRepository noteRepository;

    public NoteModel addNote(NoteModel note) {
        LOGGER.debug("Adding new note: {}", note);
        NoteModel newNote = noteRepository.save(note);
        LOGGER.debug("New note added successfully: {}", newNote);
        return newNote;
    }

    public List<NoteModel> getAllNotes() {
        LOGGER.debug("Fetching all notes from database");
        List<NoteModel> allNote = noteRepository.findAll();
        LOGGER.debug("Returning all notes: {}", allNote);
        return allNote;
    }

    public NoteModel getNoteByTitle(String title) {
        LOGGER.debug("Fecthing note from database");
        NoteModel note =   noteRepository.findBytitle(title);
              LOGGER.debug("Returning note:", note);
              return  note;

    }
    public NoteModel updateNote(String id, NoteModel noteModel) {
        LOGGER.debug("Updating note with ID: {}", id);
        NoteModel note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            LOGGER.debug("Note with ID {} not found", id);
            return null;
        }

        if (noteModel.getTitle() != null && !noteModel.getTitle().isEmpty()) {
            note.setTitle(noteModel.getTitle());
        }
        if (noteModel.getDescription() != null && !noteModel.getDescription().isEmpty()) {
            note.setDescription(noteModel.getDescription());
        }

        NoteModel updatedNote = noteRepository.save(note);
        LOGGER.debug("Note updated successfully: {}", updatedNote);
        return updatedNote;
    }

    public String deleteNote(String id) {
        LOGGER.debug("Deleting note with ID: {}", id);
        try {
            noteRepository.deleteById(id);
            LOGGER.debug("Note with ID {} deleted successfully", id);
            return "Note with ID " + id + " has been deleted successfully";
        } catch (Exception e) {
            LOGGER.error("Failed to delete note with ID {}: {}", id, e.getMessage());
            return "Failed to delete note with ID " + id;
        }
    }

}
