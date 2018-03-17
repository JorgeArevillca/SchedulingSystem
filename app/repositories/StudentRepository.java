package repositories;

import io.ebean.Finder;
import models.Student;

import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private static Finder<Long, Student> finder = new Finder<>(Student.class);

    public Optional<Student> get(Long id) throws Exception {
        Student classe = finder.byId(id);
        return Optional.ofNullable(classe);

    }

    public List<Student> get() throws Exception {
        return finder.all();
    }

    public Student save(Student student) throws Exception {
        student.save();
        return student;
    }

    public Student update(Student student) throws Exception {
        student.update();
        return student;
    }

    public boolean delete(Long id) throws Exception {
        Optional<Student> optional = this.get(id);
        if (optional.isPresent()) {
            optional.get().delete();
            return true;
        }
        return false;
    }
}
