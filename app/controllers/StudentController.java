package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import facades.StudenFacade;
import models.Student;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class StudentController extends Controller {
    private StudenFacade studenFacade;

    @Inject
    public StudentController(StudenFacade studenFacade){
        this.studenFacade = studenFacade;
    }

    public Result get(Long id) throws Exception {
        Student student = this.studenFacade.get(id);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    public Result getAll() throws Exception {
        List<Student> students = this.studenFacade.get();
        JsonNode result = Json.toJson(students);
        return ok(result);
    }

    public Result create() throws Exception {
        JsonNode json = request().body().asJson();
        Student student = Json.fromJson(json, Student.class);
        student = this.studenFacade.save(student);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    public Result update(Long id) throws Exception {
        JsonNode json = request().body().asJson();
        Student student = Json.fromJson(json, Student.class);
        student.studentId = id;
        student = this.studenFacade.update(student);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    public Result delete(Long id) throws Exception {
        this.studenFacade.delete(id);
        return ok();
    }
}
