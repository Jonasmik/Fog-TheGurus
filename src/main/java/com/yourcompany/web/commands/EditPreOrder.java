package com.yourcompany.web.commands;

import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditPreOrder extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String carportWidth = request.getParameter("carportwidth");
        String carportLength = request.getParameter("carportlength");
        String carportRoof = request.getParameter("carportroof");
        String carportRoofAngle = request.getParameter("carportroofangle");
        String shedWidth = request.getParameter("shedwidth");
        String shedLength = request.getParameter("shedlength");
        String carportId = request.getParameter("carportid");
        String shedId = request.getParameter("shedid");
        return null;
    }
}
