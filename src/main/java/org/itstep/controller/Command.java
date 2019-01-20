package org.itstep.controller;

import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {

    void execute(String args, HttpServletResponse resp);
}
