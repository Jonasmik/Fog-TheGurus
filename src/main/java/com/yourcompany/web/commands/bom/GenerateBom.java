package com.yourcompany.web.commands.bom;

import com.yourcompany.api.facades.MaterialPriceFacade;
import com.yourcompany.api.facades.MaterialPriceFacade.BomFacade;
import com.yourcompany.domain.bom.Bom;
import com.yourcompany.domain.bom.Bom.BomItem;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.material.MaterialRepository;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.exceptions.bom.NoSuchMaterialExist;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.infrastructure.listbased.ListMaterialRepository;
import com.yourcompany.web.svg.svgcalculations.CarportTopView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenerateBom extends BomCommand {

    @Override
    protected String withBomExecute(HttpServletRequest request, HttpServletResponse response) {
        String carportId = request.getParameter("carportid");
        int newCarportId = 0;
        HttpSession session = request.getSession();

        try {
            newCarportId = Integer.parseInt(carportId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Carport carport;
        Shed shed;

        try {
            carport = api.getCarportFacade().findById(newCarportId);
            shed = api.getShedFacade().findByCarportId(newCarportId);
        } catch (NoSuchCarportExists | NoSuchShedExists noSuchCarportExists) {
            request.setAttribute("error", "Din carport eksisterer ikke");
            return "errorpage";
        }

        MaterialRepository repo = new ListMaterialRepository();
        Bom bom = null;
        try {
            bom = Bom.createList(repo, carport, shed);
        } catch (
            NoSuchMaterialExist unsatisfiableCarport) {
            request.setAttribute("error", "Din stykliste blev ikke lavet");
            return "errorpage";
        }

        double cost = 0;
        for(BomItem b : bom.getBomItemList()){
            cost = cost + b.getPrice();
        }

        int twentyPercentOfCost = (int) (cost * 0.2);
        int startPrice = (int) (cost + twentyPercentOfCost);

        session.setAttribute("carportpicture", CarportTopView.carportTopView(carport.getWidth(), carport.getLength(), shed.getWidth(), shed.getLength()));
        session.setAttribute("carportbom", bom);
        session.setAttribute("cost", cost);
        session.setAttribute("startprice", startPrice);

        return "redirect:listbompage";
    }
}
