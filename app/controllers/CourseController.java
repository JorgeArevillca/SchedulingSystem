package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import facades.CourseFacade;
import io.swagger.annotations.*;
import models.Course;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import validations.groups.Create;
import validations.groups.Update;
import validations.validator.ValidatorUtil;

import java.util.List;

@Api(value = "Class", description = "Api to manage Classes")
public class CourseController extends Controller{
    private CourseFacade courseFacade;
    private ValidatorUtil validatorUtil;

    @Inject
    public CourseController(CourseFacade courseFacade,
                            ValidatorUtil validatorUtil){
        this.courseFacade = courseFacade;
        this.validatorUtil = validatorUtil;
    }

    @ApiOperation(value = "Get a Class by its id", notes = "Return a class")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Course.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result get(Long id) throws Exception {
        Course course = this.courseFacade.get(id);
        JsonNode result = Json.toJson(course);
        return ok(result);
    }

    @ApiOperation(value = "Get all the Classes", notes = "Return a list of classes")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Course.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result getAll(String query) throws Exception {
        List<Course> courses = this.courseFacade.get(query);
        JsonNode result = Json.toJson(courses);
        return ok(result);
    }

    @ApiOperation(value = "Creates a Class", notes = "Return the created class")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Course.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result create() throws Exception {
        Course course = this.validatorUtil.parseToObject(Course.class, request().body().asJson(), Create.class);
        course = this.courseFacade.save(course);
        JsonNode result = Json.toJson(course);
        return ok(result);
    }

    @ApiOperation(value = "Updates a Class", notes = "Return the created class")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Course.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result update(Long id) throws Exception {
        Course course = this.validatorUtil.parseToObject(Course.class, request().body().asJson(), Update.class);
        course.code = id;
        course = this.courseFacade.update(course);
        JsonNode result = Json.toJson(course);
        return ok(result);
    }

    @ApiOperation(value = "Delete a Class by its id")
    @ApiResponses(value = {
            @ApiResponse(code = Http.Status.OK, message = "Ok", response = Course.class),
            @ApiResponse(code = Http.Status.BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = Http.Status.NOT_FOUND, message = "Missing or Invalid parameters")
    })
    public Result delete(Long id) throws Exception {
        this.courseFacade.delete(id);
        return ok();
    }
}
