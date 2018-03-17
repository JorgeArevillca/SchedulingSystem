package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Student extends Model{

    @Id
    public Long studentId;
    public String lastName;
    public String firstName;

    @ManyToMany
    public List<Course> aClasses;
}
