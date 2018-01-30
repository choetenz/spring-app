package com.tenzin.app.exhandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.tenzin.app.data.ErrorResponse;
import com.tenzin.app.exception.NoRecordFoundException;

@Provider
public class NoRecordFoundProvider implements ExceptionMapper<NoRecordFoundException> {

	@Override
	public Response toResponse(NoRecordFoundException exception) {
		final ErrorResponse entity = new ErrorResponse();
		entity.setErrorCode("CUSTERR-101");
		entity.setErrorDesc(exception.getMessage());
		return Response.status(509).type(MediaType.APPLICATION_JSON).entity(entity).build();
	}

}
