package it.unimi.di.vec20.pactexample.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoDataExceptionMapper implements ExceptionMapper<NoDataException> {
  @Override
  public Response toResponse(NoDataException exception) {
    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
