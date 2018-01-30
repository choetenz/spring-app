package com.tenzin.app.exhandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenzin.app.data.ErrorResponse;
import com.tenzin.app.exception.InvalidDataException;

@Provider
public class InvalidDataProvider implements ExceptionMapper<InvalidDataException> {

	private final static Logger logger = LoggerFactory.getLogger(InvalidDataProvider.class.getName());
	
	@Override
	public Response toResponse(InvalidDataException exception) {
		logger.error("Invalid Data", exception);
		final ErrorResponse entity = new ErrorResponse();
		entity.setErrorCode("CUSTERR-100");
		entity.setErrorDesc("Invalid data to create or update customer");
		return Response.status(510).type(MediaType.APPLICATION_JSON).entity(entity).build();
	}

}
