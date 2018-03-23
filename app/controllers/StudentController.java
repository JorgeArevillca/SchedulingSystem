package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import facades.StudentFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import models.Course;
import models.Student;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import validations.groups.Create;
import validations.groups.Update;
import validations.validator.ValidatorUtil;

import java.util.List;

@Api(value = "Student", description = "Api to manage Students")
public class StudentController extends Controller {
    private StudentFacade studentFacade;
    private ValidatorUtil validatorUtil;

    @Inject
    public StudentController(StudentFacade studentFacade,
                             ValidatorUtil validatorUtil){
        this.studentFacade = studentFacade;
        this.validatorUtil = validatorUtil;
    }

    @ApiOperation(value = "Get an Student by its id", notes = "Return a student")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Student.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result get(Long id) throws Exception {
        Student student = this.studentFacade.get(id);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    @ApiOperation(value = "Get all Students", notes = "Return a list of students")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Student.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result getAll(String query) throws Exception {
        List<Student> students = this.studentFacade.get(query);
        JsonNode result = Json.toJson(students);
        return ok(result);
    }

    @ApiOperation(value = "Create a Student", notes = "Return the created Student")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Student.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result create() throws Exception {
        Student student = this.validatorUtil.parseToObject(Student.class, request().body().asJson(), Create.class);
        student = this.studentFacade.save(student);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    @ApiOperation(value = "Update a Student", notes = "Return the updated Student")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Student.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result update(Long id) throws Exception {
        Student student = this.validatorUtil.parseToObject(Student.class, request().body().asJson(), Update.class);
        student.studentId = id;
        student = this.studentFacade.update(student);
        JsonNode result = Json.toJson(student);
        return ok(result);
    }

    @ApiOperation(value = "Delete a Student")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Student.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result delete(Long id) throws Exception {
        this.studentFacade.delete(id);
        return ok();
    }
}
