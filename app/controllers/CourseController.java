package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import facades.CourseFacade;
import models.Course;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import validations.groups.Create;
import validations.groups.Update;
import validations.validator.ValidatorUtil;

import java.util.List;

public class CourseController extends Controller{
    private CourseFacade courseFacade;
    private ValidatorUtil validatorUtil;

    @Inject
    public CourseController(CourseFacade courseFacade,
                            ValidatorUtil validatorUtil){
        this.courseFacade = courseFacade;
        this.validatorUtil = validatorUtil;
    }

    public Result get(Long id) throws Exception {
        Course course = this.courseFacade.get(id);
        JsonNode result = Json.toJson(course);
        return ok(result);
    }

    public Result getAll(String query) throws Exception {
        List<Course> courses = this.courseFacade.get(query);
        JsonNode result = Json.toJson(courses);
        return ok(result);
    }

    public Result create() throws Exception {
        Course course = this.validatorUtil.parseToObject(Course.class, request().body().asJson(), Create.class);
        course = this.courseFacade.save(course);
        JsonNode result = Json.toJson(course);
        return ok(result);
    }

    public Result update(Long id) throws Exception {
        Course course = this.validatorUtil.parseToObject(Course.class, request().body().asJson(), Update.class);
        course.code = id;
        course = this.courseFacade.update(course);
        JsonNode result = Json.toJson(course);
        return ok(result);
    }

    public Result delete(Long id) throws Exception {
        this.courseFacade.delete(id);
        return ok();
    }
}
