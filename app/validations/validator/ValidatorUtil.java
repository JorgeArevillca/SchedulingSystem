package validations.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import exceptions.ValidationException;
import play.libs.Json;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static utils.ApplicationConstants.VALIDATION_EXCEPTION;

@Singleton
public class ValidatorUtil {
    private final Validator validator;

    @Inject
    public ValidatorUtil (Validator validator) {
        this.validator = validator;
    }

    public <T> T parseToObject(Class<T> clazz, JsonNode json, Class<?>... groups) throws Exception{

        T object = Json.fromJson(json, clazz);

        Set<ConstraintViolation<T>> violations = this.validator.validate(object, groups);

        if(!violations.isEmpty()){
            //TODO improve the format to display the violation message
            throw new ValidationException(VALIDATION_EXCEPTION + ": " +violations.toString());
        }

        return object;
    }

}
