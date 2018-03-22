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
import javax.validation.constraints.Size;
import java.util.List;

@JsonIgnoreProperties({"_ebean_intercept"})
@Entity
public class Course extends Model {

    @Id
    @NotNull(groups = {Create.class, Update.class})
    public Long code;

    @NotBlank(groups = {Create.class})
    @NotNull(groups = {Update.class})
    public String title;

    @Size(min = 10, max = 240, groups = {Create.class, Update.class})
    public String description;

    @ManyToMany
    public List<Student> students;
}
