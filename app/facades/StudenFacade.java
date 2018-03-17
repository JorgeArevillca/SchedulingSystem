package facades;

import com.google.inject.Inject;
import models.Student;
import repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudenFacade {
    private StudentRepository studentRepository;

    @Inject
    public StudenFacade(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student get(Long id) throws Exception {
        Optional<Student> optionalStudent = this.studentRepository.get(id);

        if(!optionalStudent.isPresent()){
            throw new Exception("Student not found");
        }

        return optionalStudent.get();
    }

    public List<Student> get() throws Exception {
        return this.studentRepository.get();
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
