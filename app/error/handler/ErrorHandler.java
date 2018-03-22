package error.handler;

import com.google.inject.Singleton;
import exceptions.ResourceNotFoundException;
import play.http.HttpErrorHandler;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static utils.ApplicationConstants.CLIENT_ERROR;
import static utils.ApplicationConstants.INTERNAL_SERVER_ERROR;
import static utils.ApplicationConstants.RESOURCE_NOT_FOUND;

@Singleton
public class ErrorHandler implements HttpErrorHandler {
    @Override
    public CompletionStage<Result> onClientError(Http.RequestHeader request, int statusCode, String message) {
        return CompletableFuture.completedFuture(Results.status(statusCode, CLIENT_ERROR));
    }

    @Override
    public CompletionStage<Result> onServerError(Http.RequestHeader request, Throwable exception) {
        Result result;

        if(exception instanceof ResourceNotFoundException) {
            result = Results.notFound(RESOURCE_NOT_FOUND +": "+ exception.getMessage());
        } else {
            result = Results.internalServerError(INTERNAL_SERVER_ERROR);
        }

        return CompletableFuture.completedFuture(result);
    }
}
