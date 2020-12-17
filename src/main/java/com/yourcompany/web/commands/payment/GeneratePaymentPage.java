package com.yourcompany.web.commands.payment;

import com.yourcompany.web.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GeneratePaymentPage extends PaymentCommand {

    @Override
    protected String withPaymentExecute(HttpServletRequest request, HttpServletResponse response) {
        String offerId = request.getParameter("offerid");
        String preOrderId = request.getParameter("preorderid");
        HttpSession session = request.getSession();

        session.setAttribute("offerid", offerId);
        session.setAttribute("preorderid", preOrderId);

        return "redirect:listpaymentpage";
    }
}
