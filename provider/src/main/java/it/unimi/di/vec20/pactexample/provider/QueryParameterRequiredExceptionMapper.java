package it.unimi.di.vec20.pactexample.provider;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class QueryParameterRequiredExceptionMapper
    implements ExceptionMapper<QueryParameterRequiredException> {
  @Override
  public Response toResponse(QueryParameterRequiredException exception) {
    return Response.status(Response.Status.BAD_REQUEST)
        .type(MediaType.APPLICATION_JSON_TYPE)
        .entity("{\"error\": \"" + exception.getMessage() + "\"}")
        .build();
  }
}
