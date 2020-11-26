package com.yourcompany.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlatRoofPreOrder extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String length = request.getParameter("length");
        String width = request.getParameter("width");
        String roof = request.getParameter("roof");
        String shed =  request.getParameter("shed");


        return null;

    }
}
