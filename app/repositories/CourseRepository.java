package repositories;


import io.ebean.Finder;
import io.ebean.Model;
import models.Course;

import java.util.List;
import java.util.Optional;

public class CourseRepository {

    private static Finder<Long, Course> finder = new Finder<>(Course.class);

    public Optional<Course> get(Long id) throws Exception {
        Course classe = finder.byId(id);
        return Optional.ofNullable(classe);

    }

    public List<Course> get() throws Exception {
        return finder.all();
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
