package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.ebean.Model;
import org.hibernate.validator.constraints.NotBlank;
import validations.groups.Create;
import validations.groups.Update;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties({"_ebean_intercept"})
@Entity
public class Student extends Model {

    @Id
    @NotNull(groups = {Create.class, Update.class})
    public Long studentId;

    @NotBlank(groups = {Create.class, Update.class})
    public String lastName;

    @NotBlank(groups = {Create.class, Update.class})
    public String firstName;

    @ManyToMany
    public List<Course> classes;
}
