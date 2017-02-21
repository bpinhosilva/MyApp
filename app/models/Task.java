package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Task extends Model {

    @Id
    @Constraints.Min(10)
    private Long id;

    @Constraints.Required
    @NotNull
    private String name;

    private boolean done;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date dueDate = new Date();

    public static Finder<Long, Task> find = new Finder<Long, Task>(Task.class);

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDueDate() {
        return this.dueDate;
    }
}