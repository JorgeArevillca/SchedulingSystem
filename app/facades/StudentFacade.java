package facades;

import com.google.inject.Inject;
import exceptions.ResourceNotFoundException;
import models.Student;
import repositories.StudentRepository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import static utils.ApplicationConstants.STUDENT_NOT_FOUND;

public class StudentFacade {
    private StudentRepository studentRepository;

    @Inject
    public StudentFacade(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student get(Long id) throws Exception {
        Optional<Student> optionalStudent = this.studentRepository.get(id);

        if(!optionalStudent.isPresent()){
            throw new ResourceNotFoundException(MessageFormat.format(STUDENT_NOT_FOUND, id));
        }

        return optionalStudent.get();
    }

    public List<Student> get(String query) throws Exception {
        return this.studentRepository.get(query);
    }

    public Student save(Student student) throws Exception {
        return this.studentRepository.save(student);
    }

    public Student update(Student student) throws Exception {
        return this.studentRepository.update(student);
    }

    public boolean delete(Long id) throws Exception {
        return this.studentRepository.delete(id);
    }
}
