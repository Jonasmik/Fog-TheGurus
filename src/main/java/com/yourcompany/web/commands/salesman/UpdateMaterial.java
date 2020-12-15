package com.yourcompany.web.commands.salesman;

import com.yourcompany.exceptions.bom.NoSuchMaterialExist;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateMaterial extends SalesmanCommand {

    @Override
    protected String withSalesmanExecute(HttpServletRequest request, HttpServletResponse response) {
        String materialId = request.getParameter("materialid");
        String price = request.getParameter("newprice");
        int newMaterialId = 0;
        double newPrice = 0;

        try {
            newMaterialId = Integer.parseInt(materialId);
            newPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            api.getMaterialPriceFacade().updateMaterial(newMaterialId, newPrice);
        } catch (NoSuchMaterialExist noSuchMaterialExist) {
            request.setAttribute("error", "Dine inputs var ikke korrekte");
            return "errorpage";
        }

        return "redirect:listsalesmanpage";
    }
}
