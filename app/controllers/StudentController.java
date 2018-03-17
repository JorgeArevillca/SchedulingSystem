package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import facades.StudentFacade;
import models.Student;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class StudentController extends Controller {
    private StudentFacade studentFacade;

    @Inject
    public StudentController(StudentFacade studentFacade){
        this.studentFacade = studentFacade;
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
        JsonNode json = request().body().asJson();
        Student student = Json.fromJson(json, Student.class);
        student = this.studentFacade.save(student);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    public Result update(Long id) throws Exception {
        JsonNode json = request().body().asJson();
        Student student = Json.fromJson(json, Student.class);
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
