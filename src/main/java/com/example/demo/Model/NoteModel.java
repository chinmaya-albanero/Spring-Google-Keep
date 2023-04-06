package com.example.demo.Model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "google-keep")
public class NoteModel {
    @Id
    private String id;

    private  String title;
    private String description;

    public NoteModel(String id,String title, String description){
        this.id = id;
        this.title = title;
        this.description =description;
    }

    // getter and setters

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public  void setTitle(String title){
        this.title = title;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String Description){
        this.description = description;
    }
}
