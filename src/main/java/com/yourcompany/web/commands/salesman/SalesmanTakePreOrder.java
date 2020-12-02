package com.yourcompany.web.commands.salesman;

import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalesmanTakePreOrder extends SalesmanCommand {
    @Override
    protected String withSalesmanExecute(HttpServletRequest request, HttpServletResponse response) {
       String preorderid = request.getParameter("preorderid");
       return "redirect:listpreorders";
    }
}
