package com.example.library.controller;

import com.example.library.entity.Barber;
import com.example.library.entity.HairType;
import com.example.library.entity.Order;
import com.example.library.service.BarberService;
import com.example.library.service.HairTypeService;
import com.example.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class BarberController {
    @Autowired
    private BarberService barberService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HairTypeService hairTypeService;
    HttpSession session;

    @RequestMapping("/barber")
    public String index(Model model) {
        return "barber/index";
    }

    @RequestMapping("/barberLogin")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, Map<String, Object> map, HttpServletRequest request) {
        Barber barber = barberService.findBarberByEmail(email);
        if (barber == null) {
            map.put("msg", "账户不存在");
            return "barber/index";
        } else {
            if (barber.getPassword().equals(password)) {
                request.getSession().setAttribute("barberId", barber.getId());
                return "redirect:/barberHello/id=" + barber.getId();
            } else {
                map.put("msg", "密码错误");
                return "barber/index";
            }
        }
    }

    @GetMapping("/barberHello/id={id}")
    public String hello(@PathVariable int id, Model model) {
        List<Order> pOrders = orderService.findPOrderByBarberId(id);
        List<Order> aOrders = orderService.findAOrderByBarberId(id);
        List<Order> cOrders = orderService.findCOrderByBarberId(id);
        List<HairType> selected = hairTypeService.findHairTypebyBarberid(id);
        List<HairType> unSelected = hairTypeService.findByShopidForBarber(id);
        model.addAttribute("pOrders", pOrders);
        model.addAttribute("aOrders", aOrders);
        model.addAttribute("cOrders", cOrders);
        model.addAttribute("barberId", id);
        model.addAttribute("selected", selected);
        model.addAttribute("unSelected", unSelected);
        return "barber/hello";
    }

    @RequestMapping("/barberAbility")
    public String ability(Model model,HttpServletRequest request) {

        if (request.getSession().getAttribute("barberId") == null) {
            return "barber/index";
        }
        int id=(int )request.getSession().getAttribute("barberId");
        List<Order> pOrders = orderService.findPOrderByBarberId(id);
        List<Order> aOrders = orderService.findAOrderByBarberId(id);
        List<Order> cOrders = orderService.findCOrderByBarberId(id);

        List<HairType> selected = hairTypeService.findHairTypebyBarberid(id);
        List<HairType> unSelected = hairTypeService.findByShopidForBarber(id);
        model.addAttribute("pOrders", pOrders);
        model.addAttribute("aOrders", aOrders);
        model.addAttribute("cOrders", cOrders);
        model.addAttribute("barberId", id);
        model.addAttribute("selected", selected);
        model.addAttribute("unSelected", unSelected);
        return "barber/ability";
    }

    @RequestMapping("/barberAccept/orderid={orderid}")
    public String accept(@PathVariable int orderid, HttpServletRequest request) {
        if (request.getSession().getAttribute("barberId") == null) {
            return "redirect:/barber";
        }
        orderService.updateOrder(orderid, 3);
        return "redirect:/barberHello/id=" + request.getSession().getAttribute("barberId").toString();
    }

    @RequestMapping("/barberRefuse/orderid={orderid}")
    public String refuse(@PathVariable int orderid, HttpServletRequest request) {

        if (request.getSession().getAttribute("barberId") == null) {
            return "index";
        }
        orderService.updateOrder(orderid, 1);
        return "redirect:/barberHello/id=" + request.getSession().getAttribute("barberId").toString();
    }

    //添加技能
    @RequestMapping("/barberAddAbility")
    public String addAbility(@RequestParam("unselectedid") int unselectedid, HttpServletRequest request) {

        if (request.getSession().getAttribute("barberId") == null) {
            return "barber/index";
        }
        int barberId=(int)request.getSession().getAttribute("barberId");
        hairTypeService.addBarberHairtype(barberId,unselectedid);
        return "redirect:/barberAbility";
    }


    //删除技能
    @RequestMapping("/barberDeleteAbility")
    public String deleteAbility(@RequestParam("selectedid") int selectedid, HttpServletRequest request) {

        if (request.getSession().getAttribute("barberId") == null) {
            return "barber/index";
        }
        hairTypeService.deleteBarberHairtype(selectedid);
        return "redirect:/barberAbility";
    }

    //注销
    @RequestMapping("/barberLogout")
    public String exit(HttpServletRequest request) {
        request.getSession().invalidate();
        return "barber/index";
    }


}