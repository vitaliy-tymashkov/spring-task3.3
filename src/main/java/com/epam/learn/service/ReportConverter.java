package com.epam.learn.service;

import com.epam.learn.model.UserAccount;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReportConverter
        extends AbstractHttpMessageConverter<UserAccount> {

    public ReportConverter() {
        super(new MediaType("application", "pdf"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return UserAccount.class.isAssignableFrom(clazz);
    }

    @Override
    protected UserAccount readInternal(Class<? extends UserAccount> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        String requestBody = toString(inputMessage.getBody());
        int i = requestBody.indexOf("\n");
        if (i == -1) {
            throw new HttpMessageNotReadableException("No first line found");
        }

        String reportName = requestBody.substring(0, i).trim();
        String content = requestBody.substring(i).trim();

        return new UserAccount();
    }

    private String toString(InputStream body) {
        return body.toString();
    }


    @Override
    protected void writeInternal(UserAccount userAccount, HttpOutputMessage outputMessage)
            throws HttpMessageNotWritableException {
        try {
            OutputStream outputStream = outputMessage.getBody();
            String body = userAccount.toString();

            outputStream.write(body.getBytes());
            outputStream.close();
        } catch (Exception e) {
            //TODO Catch e
        }
    }
}
