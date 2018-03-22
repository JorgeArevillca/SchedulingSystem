package facades;

import com.google.inject.Inject;
import exceptions.ResourceNotFoundException;
import models.Course;
import repositories.CourseRepository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import static utils.ApplicationConstants.CLASS_NOT_FOUND;

public class CourseFacade {
    private CourseRepository courseRepository;

    @Inject
    public CourseFacade(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public Course get(Long id) throws Exception {
        Optional<Course> optionalCourse = this.courseRepository.get(id);

        if(!optionalCourse.isPresent()){
            throw new ResourceNotFoundException(MessageFormat.format(CLASS_NOT_FOUND, id));
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
