package com.yourcompany.web.commands;

import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {

        /**
         * Called from the ICommand if the there was a target call with an incorrect value.
         */

        String message = "Unknown command!";
        request.setAttribute("error", message);
        return "errorpage";
    }
}
