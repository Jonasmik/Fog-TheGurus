package com.yourcompany.web.commands.payment;

import com.yourcompany.web.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class PaymentCommand extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        if (!isUserRole(request.getSession(), "customer")) {
            request.setAttribute("error", "Du er ikke en kunde");
            return "errorpage";
        }
        if (request.getSession().getAttribute("preorderid") == null) {
            request.setAttribute("error", "Du har ikke valgt noget tilbud at acceptere");
            return "errorpage";
        }
        return withPaymentExecute(request, response);
    }

    protected abstract String withPaymentExecute(HttpServletRequest request, HttpServletResponse response);

}
