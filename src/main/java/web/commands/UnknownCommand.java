package web.commands;

import web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String message = "Unknown command!";
        request.setAttribute("error", message);
        return "errorpage";
    }
}
