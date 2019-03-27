package org.itstep.controller;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface Command {

    ResponseEntity execute(String requestBody) throws Exception;
}
