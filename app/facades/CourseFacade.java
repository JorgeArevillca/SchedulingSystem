package facades;

import com.google.inject.Inject;
import models.Course;
import repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseFacade {
    private CourseRepository courseRepository;

    @Inject
    public CourseFacade(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public Course get(Long id) throws Exception {
        Optional<Course> optionalCourse = this.courseRepository.get(id);

        if(!optionalCourse.isPresent()){
            throw new Exception("Class not found");
        }

        return optionalCourse.get();
    }

    public List<Course> get(String query) throws Exception {
        return this.courseRepository.get(query);
    }

    public Course save(Course course) throws Exception {
        return this.courseRepository.save(course);
    }

    public Course update(Course course) throws Exception {
        return this.courseRepository.update(course);
    }

    public boolean delete(Long id) throws Exception {
        return this.courseRepository.delete(id);
    }
}
