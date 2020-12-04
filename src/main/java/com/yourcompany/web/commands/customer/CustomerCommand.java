package com.yourcompany.web.commands.customer;

import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CustomerCommand extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        if (!isUserRole(request.getSession(), "customer")) {
            request.setAttribute("error", "Du er ikke en kunde");
            return "errorpage";
        }
        return withCustomerExecute(request, response);
    }

    protected abstract String withCustomerExecute(HttpServletRequest request, HttpServletResponse response);
}
