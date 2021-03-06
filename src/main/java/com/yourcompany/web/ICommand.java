
package com.yourcompany.web;

import com.yourcompany.api.*;

import com.yourcompany.api.facades.*;
import com.yourcompany.api.factories.OrderFactory;
import com.yourcompany.domain.user.User;
import com.yourcompany.web.commands.*;
import com.yourcompany.web.commands.bom.GenerateBom;
import com.yourcompany.web.commands.bom.ListBomPage;
import com.yourcompany.web.commands.customer.EditInfoSettings;
import com.yourcompany.web.commands.customer.GenerateCarportPicture;
import com.yourcompany.web.commands.customer.ListCustomerPage;
import com.yourcompany.web.commands.order.AcceptOffer;
import com.yourcompany.web.commands.payment.GeneratePaymentPage;
import com.yourcompany.web.commands.order.CreatePreOrder;
import com.yourcompany.web.commands.order.EditPreOrder;
import com.yourcompany.web.commands.order.OfferControl;
import com.yourcompany.web.commands.payment.ListPaymentPage;
import com.yourcompany.web.commands.receipt.GenerateReceiptPage;
import com.yourcompany.web.commands.receipt.ListReceiptPage;
import com.yourcompany.web.commands.salesman.ListSalesmanPage;
import com.yourcompany.web.commands.salesman.SalesmanTakePreOrder;
import com.yourcompany.web.commands.salesman.UpdateMaterial;
import com.yourcompany.web.commands.user.AuthorizeUser;
import com.yourcompany.web.commands.user.CreateUser;
import com.yourcompany.web.commands.user.LogoutUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public abstract class ICommand {

    /*
     * @author Kasper
     * Modified by: De Resterende
     */

    /**
     * Whenever you want to make a post or get from a .jsp site, give an input called (name=target). The value of that target you
     * should make a string, like the example with redirect.
     */

    private static HashMap<String, ICommand> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("redirect", new Redirect());
        commands.put("authorizeuser", new AuthorizeUser());
        commands.put("createuser", new CreateUser());
        commands.put("preorder", new CreatePreOrder());
        commands.put("logoutuser", new LogoutUser());
        commands.put("takepreorder", new SalesmanTakePreOrder());
        commands.put("listsalesmanpage", new ListSalesmanPage());
        commands.put("listcustomerpage", new ListCustomerPage());
        commands.put("generatecarportpicture", new GenerateCarportPicture());
        commands.put("listbompage", new ListBomPage());
        commands.put("generatebom", new GenerateBom());
        commands.put("editpreorder", new EditPreOrder());
        commands.put("updatematerial", new UpdateMaterial());
        commands.put("offercontrol", new OfferControl());
        commands.put("generatepaymentpage", new GeneratePaymentPage());
        commands.put("listpaymentpage", new ListPaymentPage());
        commands.put("acceptoffer", new AcceptOffer());
        commands.put("listreceiptpage", new ListReceiptPage());
        commands.put("generatereceiptpage", new GenerateReceiptPage());
        commands.put("editinfosettings", new EditInfoSettings());

    }

    static ICommand from(HttpServletRequest request) {
        String targetName = request.getParameter("target");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand());   // unknowncommand er default.
    }

    //Creates the com.yourcompany.api, with this you will be able to just call com.yourcompany.api.get().YOURMETHOD()
    public static final Fog api;

    static {
        api = createFog();
    }

    private static Fog createFog() {
        return new Fog(UserFacade.getInstance(),
            CarportFacade.getInstance(),
            ShedFacade.getInstance(),
            CustomerFacade.getInstance(),
            PreOrderFacade.getInstance(),
            SalesmanFacade.getInstance(),
            MaterialPriceFacade.getInstance(),
            OfferFacade.getInstance(),
            OrderFacade.getInstance());
    }

    //used by every command, and called by the invoker.
    protected abstract String execute(HttpServletRequest request, HttpServletResponse response);

    protected User getUser(HttpSession httpSession) {
        return (User) httpSession.getAttribute("user");
    }

    protected boolean isUserRole(HttpSession httpSession, String role) {
        User user = getUser(httpSession);
        return user != null && user.getRole().equals(role);
    }


}
