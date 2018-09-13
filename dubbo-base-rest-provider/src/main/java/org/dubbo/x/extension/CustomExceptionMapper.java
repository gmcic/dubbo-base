package org.dubbo.x.extension;

import com.alibaba.dubbo.rpc.protocol.rest.RestConstraintViolation;
import com.alibaba.dubbo.rpc.protocol.rest.ViolationReport;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

import org.dubbo.x.dto.RestResult;
import org.dubbo.x.dto.RestResultEnum;
import org.dubbo.x.exception.ApiException;
import org.dubbo.x.exception.AuthorizationException;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author tom
 */
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

    public Response toResponse(Exception e) {
        if (e.getCause() instanceof AuthorizationException) {
            return handleAuthorizationException((AuthorizationException) e.getCause());
        }else if (e.getCause() instanceof ApiException) {
            return handleApiException((ApiException) e.getCause());
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error: " + e.getMessage()).type(ContentType.TEXT_PLAIN_UTF_8).build();
    }

    private Response handleAuthorizationException(AuthorizationException authorizationException) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(
                RestResult.REST_RESULT(RestResultEnum.UNAUTHORIZED, authorizationException.getMessage())).type(ContentType.APPLICATION_JSON_UTF_8).build();
    }

    protected Response handleApiException(ApiException apiException) {
        return Response.status(Response.Status.OK).entity(apiException.getMessage()).type(ContentType.APPLICATION_JSON_UTF_8).build();
    }

    protected Response handleConstraintViolationException(ConstraintViolationException cve) {
        ViolationReport report = new ViolationReport();
        for (ConstraintViolation cv : cve.getConstraintViolations()) {
            report.addConstraintViolation(new RestConstraintViolation(
                    cv.getPropertyPath().toString(),
                    cv.getMessage(),
                    cv.getInvalidValue() == null ? "null" : cv.getInvalidValue().toString()));
        }
        // TODO for now just do xml output
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(report).type(ContentType.APPLICATION_JSON_UTF_8).build();
    }
}
