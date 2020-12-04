package com.yourcompany.web.commands.user;

import com.yourcompany.web.ICommand;
import com.yourcompany.web.svg.Svg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutUser extends ICommand {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        session.invalidate();

        return "index";
    }
}
