package com.zendesk.ticketviewer.exception.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.zendesk.ticketviewer.enums.ErrorCodeEnum;
import com.zendesk.ticketviewer.exception.DataNotFoundException;
import com.zendesk.ticketviewer.exception.InvalidCredentialsException;
import com.zendesk.ticketviewer.exception.ZendeskAPIException;

/**
 * Class is used as exception handler for RestTemplate to manage the exceptions from Zendesk API
 * @author manasi
 */
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler  {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR || 
				response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		
		if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			
			if (response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
				throw new ZendeskAPIException(ErrorCodeEnum.TV005,HttpStatus.SERVICE_UNAVAILABLE);
			}
			throw new ZendeskAPIException(ErrorCodeEnum.TV006,response.getStatusCode());
		} 
		else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {

			if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new DataNotFoundException(ErrorCodeEnum.TV003,HttpStatus.NOT_FOUND);
			}
			else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				throw new InvalidCredentialsException(ErrorCodeEnum.TV004,HttpStatus.UNAUTHORIZED);
			}
			throw new ZendeskAPIException(ErrorCodeEnum.TV006,response.getStatusCode());
		}
	}
}
