package com.yourcompany.web.commands.salesman;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.material.MaterialPrice;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.domain.order.Order;
import com.yourcompany.exceptions.bom.NoSuchMaterialExist;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.exceptions.order.NoSuchOrderExists;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.web.dtos.OrderDTO;
import com.yourcompany.web.dtos.PreOrderDTO;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ListSalesmanPage extends SalesmanCommand {

    @Override
    protected String withSalesmanExecute(HttpServletRequest request, HttpServletResponse response) {

        User user = getUser(request.getSession());
        String fail = "error";
        String error = "errorpage";

        //UnusedPreOrders START
        List<PreOrder> unusedPreOrders = new ArrayList<>();
        try {
            unusedPreOrders = api.getPreOrderFacade().findAllUnused();
        } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
            noSuchPreOrderExists.printStackTrace();
        }

        if (unusedPreOrders != null) {
            List<PreOrderDTO> unusedPreOrderDTO = new ArrayList<>();
            for (PreOrder p : unusedPreOrders) {
                try {
                    Customer customer = api.getCustomerFacade().findById(p.getCustomerId());
                    Carport carport = api.getCarportFacade().findById(p.getCarportId());
                    Shed shed = api.getShedFacade().findByCarportId(carport.getId());
                    unusedPreOrderDTO.add(new PreOrderDTO(p, customer, carport, shed));
                } catch (NoSuchCustomerExists | NoSuchCarportExists | NoSuchShedExists noSuchCustomerExists) {
                    request.setAttribute(fail, "Noget gik galt med generæringen af forespøgelserne");
                    return error;
                }
            }
            request.setAttribute("unusedpreorders", unusedPreOrderDTO);
        }
        //UnusedPreOrders END

        //Salesmans active PreOrders START
        List<Salesman> salesmanList;
        try {
            salesmanList = api.getSalesmanFacade().findAllByUserId(user.getId());
        } catch (NoSuchSalesmanExists noSuchSalesmanExists) {
            request.setAttribute(fail, "Der gik noget galt med at finde din salesman konto");
            return error;
        }

        if (salesmanList != null) {
            List<PreOrder> activePreOrders = new ArrayList<>();
            List<PreOrder> soldPreOrders;
            List<OrderDTO> soldOrders = new ArrayList<>();
            for (Salesman s : salesmanList) {
                try {
                    PreOrder preOrder = api.getPreOrderFacade().findBySalesmanId(s.getId());
                    if (preOrder != null){
                        activePreOrders.add(preOrder);
                    }
                    soldPreOrders = api.getPreOrderFacade().findPaidPreOrdersBySalesmanId(s.getId());
                    if(!soldPreOrders.isEmpty()) {
                        for(PreOrder p: soldPreOrders) {
                            Order order = api.getOrderFacade().findOrderByCustomerId(p.getCustomerId());
                            Customer customer = api.getCustomerFacade().findById(p.getCustomerId());
                            User user1 = api.getUserFacade().findUserById(customer.getUserid());
                            Carport carport = api.getCarportFacade().findById(p.getCarportId());
                            Shed shed = api.getShedFacade().findByCarportId(carport.getId());
                            Offer offer = api.getOfferFacade().findByPreOrderId(p.getId());
                            soldOrders.add(new OrderDTO(order, p, offer, customer, user1, carport, shed));
                        }
                    }
                } catch (NoSuchPreOrderExists | NoSuchOrderExists | NoSuchCustomerExists | UserValidationError | NoSuchCarportExists | NoSuchShedExists | NoSuchOfferExists noSuchPreOrderExists) {
                    request.setAttribute(fail, "Der gik noget galt med at finde dine informationer");
                    return error;
                }
            }
            List<PreOrderDTO> activePreOrderDTO = new ArrayList<>();
            for (PreOrder p : activePreOrders) {
                try {
                    Carport carport = api.getCarportFacade().findById(p.getCarportId());
                    Shed shed = api.getShedFacade().findByCarportId(carport.getId());
                    Customer customer = api.getCustomerFacade().findById(p.getCustomerId());
                    activePreOrderDTO.add(new PreOrderDTO(p, customer, carport, shed));
                } catch (NoSuchCarportExists | NoSuchCustomerExists | NoSuchShedExists noSuchCarportExists) {
                    request.setAttribute(fail, "Kunne ikke finde carportene fra dine forespørgelser");
                    return error;
                }
            }

            request.setAttribute("paidorders", soldOrders);
            request.setAttribute("activepreorder", activePreOrderDTO);
        }

        List<MaterialPrice> materialPriceList = null;
        try {
            materialPriceList = api.getMaterialPriceFacade().findAll();
        } catch (NoSuchMaterialExist noSuchMaterialExist) {
            request.setAttribute(fail, "Du har ikke nogle materialer :)");
            return error;
        }

        request.setAttribute("materials", materialPriceList);

        //Salesmans active PreOrders END
        return "adminpage";
    }

}
