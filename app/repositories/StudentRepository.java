package repositories;

import io.ebean.*;
import models.Student;

import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private static Finder<Long, Student> finder = new Finder<>(Student.class);

    public Optional<Student> get(Long id) throws Exception {
        Student student = finder.byId(id);
        return Optional.ofNullable(student);

    }

    public List<Student> get(String query) throws Exception {

        List<Student> results;

        if(query != null){
            RawSql rawSql = RawSqlBuilder.parse("select student_id, last_name, first_name from Student " + query).create();
            Query<Student> ebeanQuery = Ebean.find(Student.class);
            ebeanQuery.setRawSql(rawSql);
            results = ebeanQuery.findList();
        } else {
            results = finder.all();
        }

        return results;
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
