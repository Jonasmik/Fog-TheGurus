
package web;

import api.Template;
import api.facades.TemplateFacade;
import web.commands.Redirect;
import web.commands.UnknownCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public abstract class ICommand {

    private static HashMap<String, ICommand> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("redirect", new Redirect());
    }

    static ICommand from(HttpServletRequest request ) {
        String targetName = request.getParameter( "target" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand() );   // unknowncommand er default.
    }

    protected static final Template api;

    static {
        api = createTemplate();
    }

    private static Template createTemplate(){
        return new Template(TemplateFacade.getInstance());
    }

    protected abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
