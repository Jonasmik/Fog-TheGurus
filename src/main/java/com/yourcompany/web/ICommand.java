
package com.yourcompany.web;

import com.yourcompany.api.*;

import com.yourcompany.api.facades.CarportFacade;
import com.yourcompany.api.facades.UserFacade;
import com.yourcompany.web.commands.*;
import com.yourcompany.web.commands.user.AuthorizeUser;
import com.yourcompany.web.commands.user.CreateUser;
import com.yourcompany.web.commands.user.LogoutUser;

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
        commands.put("authorizeuser", new AuthorizeUser());
        commands.put("createuser", new CreateUser());
        commands.put("flatroofpreorder", new FlatRoofPreOrder());
        commands.put("logoutuser", new LogoutUser());


    }

    static ICommand from(HttpServletRequest request) {
        String targetName = request.getParameter("target");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand());   // unknowncommand er default.
    }

    //Creates the com.yourcompany.api, with this you will be able to just call com.yourcompany.api.get().YOURMETHOD()
    protected static final Fog api;

    static {
        api = createFog();
    }

    private static Fog createFog() {
        return new Fog(UserFacade.getInstance(), CarportFacade.getInstance());
    }

    //used by every command, and called by the invoker.
    protected abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
