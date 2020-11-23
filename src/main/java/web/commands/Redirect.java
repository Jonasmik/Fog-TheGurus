package web.commands;

import web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {

        String destination = request.getParameter("destination");

        switch (destination){

            case "index":
                break;
            default:
                request.setAttribute("error", "This site does not exist");
                destination = "errorpage";
                break;
        }

        return destination;
    }
}
