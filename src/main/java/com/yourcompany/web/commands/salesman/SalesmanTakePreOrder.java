package com.yourcompany.web.commands.salesman;

import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalesmanTakePreOrder extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String preorderid = request.getParameter("preorderid");




        return "adminpage";
    }
}
