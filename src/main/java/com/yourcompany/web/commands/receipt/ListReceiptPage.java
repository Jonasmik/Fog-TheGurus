package com.yourcompany.web.commands.receipt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListReceiptPage extends ReceiptCommand {

    @Override
    protected String withReceiptCommand(HttpServletRequest request, HttpServletResponse response) {

        return "receiptpage";
    }
}
