package com.yourcompany.web.commands.bom;

import com.yourcompany.web.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class BomCommand extends ICommand {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        if (getUser(request.getSession()) == null) {
            request.setAttribute("error", "Du skal være logget ind for at se en stykliste");
            return "errorpage";
        }
        return withBomExecute(request, response);
    }

    protected abstract String withBomExecute(HttpServletRequest request, HttpServletResponse response);
}
