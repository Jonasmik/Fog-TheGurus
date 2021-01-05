package com.yourcompany.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class Invoker extends HttpServlet {

    //This is the invoker of the ICommand, it calls the ICommand to execute a command and then forwards to the receiver

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            ICommand action = ICommand.from(request);
            String view = action.execute(request, response);

            if(view.startsWith("redirect:")) {
                response.sendRedirect("Main?target=" + view.substring(9));
            } else if (view.equals("index")) {
                request.getRequestDispatcher(view + ".jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
            }
        } catch (UnsupportedEncodingException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/errorpage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Command pattern and servlet created by Kasper and modified by De Resterende";
    }

}
