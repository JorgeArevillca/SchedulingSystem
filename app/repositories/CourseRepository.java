package repositories;


import io.ebean.*;
import models.Course;
import models.Student;

import java.util.List;
import java.util.Optional;

public class CourseRepository {

    private static Finder<Long, Course> finder = new Finder<>(Course.class);

    public Optional<Course> get(Long id) throws Exception {
        Course classe = finder.byId(id);
        return Optional.ofNullable(classe);

    }

    public List<Course> get(String query) throws Exception {

        List<Course> results;

        if(query != null){
            RawSql rawSql = RawSqlBuilder.parse("select code, title, description from Course " + query).create();
            Query<Course> ebeanQuery = Ebean.find(Course.class);
            ebeanQuery.setRawSql(rawSql);
            results = ebeanQuery.findList();
        } else {
            results = finder.all();
        }

        return results;
    }

    public Course save(Course course) throws Exception {
        course.save();
        return course;
    }

    public Course update(Course course) throws Exception {
        course.update();
        return course;
    }

    public boolean delete(Long id) throws Exception {
        Optional<Course> optional = this.get(id);
        if (optional.isPresent()) {
            optional.get().delete();
            return true;
        }
        return false;
    }
}
