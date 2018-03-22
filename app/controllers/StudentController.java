package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import facades.StudentFacade;
import models.Student;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import validations.groups.Create;
import validations.groups.Update;
import validations.validator.ValidatorUtil;

import java.util.List;

public class StudentController extends Controller {
    private StudentFacade studentFacade;
    private ValidatorUtil validatorUtil;

    @Inject
    public StudentController(StudentFacade studentFacade,
                             ValidatorUtil validatorUtil){
        this.studentFacade = studentFacade;
        this.validatorUtil = validatorUtil;
    }

    public Result get(Long id) throws Exception {
        Student student = this.studentFacade.get(id);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    public Result getAll(String query) throws Exception {
        List<Student> students = this.studentFacade.get(query);
        JsonNode result = Json.toJson(students);
        return ok(result);
    }

    public Result create() throws Exception {
        Student student = this.validatorUtil.parseToObject(Student.class, request().body().asJson(), Create.class);
        student = this.studentFacade.save(student);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    public Result update(Long id) throws Exception {
        Student student = this.validatorUtil.parseToObject(Student.class, request().body().asJson(), Update.class);
        student.studentId = id;
        student = this.studentFacade.update(student);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    public Result delete(Long id) throws Exception {
        this.studentFacade.delete(id);
        return ok();
    }
}
