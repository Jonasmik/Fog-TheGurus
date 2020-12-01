package com.yourcompany.web.commands;

import com.yourcompany.web.ICommand;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.slf4j.LoggerFactory.getLogger;

public class UnknownCommand extends ICommand {

    private static final Logger log = getLogger(UnknownCommand.class);

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {

        /**
         * Called from the ICommand if the there was a target call with an incorrect value.
         */

        log.info("Unknown command was thrown");

        String message = "Unknown command!";
        request.setAttribute("error", message);
        return "errorpage";
    }
}
