package com.yourcompany.web.commands.bom;

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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenerateBom extends BomCommand {

    @Override
    protected String withBomExecute(HttpServletRequest request, HttpServletResponse response) {
        String carportId = request.getParameter("carportid");
        String preOrderId = request.getParameter("preorderid");

        int newCarportId = 0;
        int newPreOrderId = 0;
        HttpSession session = request.getSession();

        try {
            newCarportId = Integer.parseInt(carportId);
            newPreOrderId = Integer.parseInt(preOrderId);
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

        List<String> materialPriceList = new ArrayList<>();
        double cost = 0;
        for(BomItem b : bom.getBomItemList()){
            materialPriceList.add(thousandSeperator(b.getPrice()));
            cost = cost + b.getPrice();
        }

        String costString = thousandSeperator(cost);


        int twentyPercentOfCost = (int) (cost * 0.2);
        int startPrice = (int) (cost + twentyPercentOfCost);

        session.setAttribute("carportpicture", CarportTopView.carportTopView(carport.getWidth(), carport.getLength(), shed.getWidth(), shed.getLength()));
        session.setAttribute("carportbom", bom);
        session.setAttribute("materialprices", materialPriceList);
        session.setAttribute("cost", costString);
        session.setAttribute("startprice", startPrice);
        session.setAttribute( "preorderid", newPreOrderId);
        session.setAttribute("realcost", cost);

        return "redirect:listbompage";
    }

    private String thousandSeperator(double cost) {

        if(cost < 1000) {
            return "" + cost;
        }

        String costString = "" + cost;
        int length = costString.length() - 2;
        int seperator = length % 3;
        StringBuilder sb = new StringBuilder();
        sb.append(costString, 0, seperator);

        while(seperator < length) {
            if(seperator > 0) {
                sb.append(".");
            }
            sb.append(costString, seperator, Math.min(seperator + 3, length));

            int interval = length - seperator;
            if (length - seperator >= 3) {
                seperator += 3;
            } else {
                seperator += interval;
            }
        }
        sb.append(costString.substring(length));

        return sb.toString();
    }
}
