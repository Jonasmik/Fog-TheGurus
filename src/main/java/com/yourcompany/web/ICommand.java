
package com.yourcompany.web;

import com.yourcompany.api.*;

import com.yourcompany.api.facades.TemplateFacade;
import com.yourcompany.web.commands.Redirect;
import com.yourcompany.web.commands.UnknownCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public abstract class ICommand {

    /*
     * @author Kasper
     * Modified by: De Resterende
     */

    /**
     * Whenever you want to make a post or get from a .jsp site, give an input called (name=target).
     * The value of that target you should make a string, like the example with redirect.
     */

    private static HashMap<String, ICommand> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("redirect", new Redirect());
    }

    static ICommand from(HttpServletRequest request) {
        String targetName = request.getParameter("target");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand());   // unknowncommand er default.
    }

    //Creates the com.yourcompany.api, with this you will be able to just call com.yourcompany.api.get().YOURMETHOD()
    protected static final Template api;

    static {
        api = createTemplate();
    }

    private static Template createTemplate() {
        return new Template(TemplateFacade.getInstance());
    }

    //used by every command, and called by the invoker.
    protected abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
