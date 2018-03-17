package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Course extends Model {

    @Id
    public Long code;
    public String title;
    public String description;

    @ManyToMany
    public List<Student> students;
}
