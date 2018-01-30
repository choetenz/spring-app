package com.tenzin.app.exhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.tenzin.app.data.ErrorResponse;

@Provider
public class GenericErrProvider implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		final ErrorResponse entity = new ErrorResponse();
		entity.setErrorCode("CUSTERR-110");
		entity.setErrorDesc(exception.getMessage());
		return Response.serverError().entity(entity).build();
	}

}
