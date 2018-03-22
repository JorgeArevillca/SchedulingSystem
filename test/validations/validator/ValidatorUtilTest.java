package validations.validator;

import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.ValidationException;
import models.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;
import validations.groups.Create;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtilTest {
    private ValidatorUtil validatorUtil;

    @Before
    public void setup(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorUtil = new ValidatorUtil(validator);
    }

    @Test(expected = ValidationException.class)
    public void testJsonWithInvalidDataThrowException() throws Exception{
        ObjectNode msg = Json.newObject();
        msg.put("studentId", 123);
        msg.put("lastName", "");
        msg.put("firstName", "");

        validatorUtil.parseToObject(Student.class, msg, Create.class);

    }

    @Test
    public void testJsonWithValidDataParsedSuccessfully() throws Exception {
        ObjectNode msg = Json.newObject();
        msg.put("studentId", 123);
        msg.put("lastName", "Jorge");
        msg.put("firstName", "Toro");

        Student actual = validatorUtil.parseToObject(Student.class, msg, Create.class);

        Assert.assertNotNull(actual);
    }
}
