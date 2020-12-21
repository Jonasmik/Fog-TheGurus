package com.yourcompany.web.commands.receipt;

import com.yourcompany.web.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ReceiptCommand extends ICommand {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        if (getUser(request.getSession()) == null) {
            request.setAttribute("error", "Du skal være logget ind for at se en kvitering");
            return "errorpage";
        }
        if (request.getSession().getAttribute("offerid") == null) {
            request.setAttribute("error", "Du har ikke valgt nogen kvitering af se på");
            return "errorpage";
        }
        return withReceiptCommand(request, response);
    }

    protected abstract String withReceiptCommand(HttpServletRequest request, HttpServletResponse response);
}
