package com.yourcompany.web.commands.bom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListBomPage extends BomCommand {

    @Override
    protected String withBomExecute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //does the bom exist
        if (session.getAttribute("carportbom") == null) {
            request.setAttribute("error", "Du skal vælge en carport før du kan få lov til at se den");
            return "errorpage";
        }
        return "instructionspage";
    }
}
