package com.yourcompany.web.commands.salesman;

import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class SalesmanCommand extends ICommand {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        if (!isUserRole(request.getSession(), "salesman")) {
            request.setAttribute("error", "Du er ikke en salesman");
            return "errorpage";
        }
        return withSalesmanExecute(request, response);
    }

    protected abstract String withSalesmanExecute(HttpServletRequest request, HttpServletResponse response);
}
