package com.example.library.controller;

import com.example.library.entity.*;

import com.example.library.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private BarberService barberService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HairTypeService hairTypeService;
    @Autowired
    private VipService vipService;

    @RequestMapping("/mlogin")
    public String mlogin(){
        return "manager/mlogin";
    }

    @RequestMapping("/BarberList")
    public String listBarber(Model model,HttpServletRequest request) {
        if(request.getSession().getAttribute("shopId")==null){
            return "manager/mlogin";
        }
        int shopId = (int)request.getSession().getAttribute("shopId");
        List<Barber> barbers = barberService.findAllByShopId(shopId);
        List<Order>  allOrders=orderService.findAllOrder(shopId);
        List<Order>  pOrders=orderService.findPOrdersByManagerId(shopId);
        List<HairType> haircuts= hairTypeService.findAllH();
        List<Vip> vips=vipService.findAllByShopId(shopId);
        model.addAttribute("haircuts", haircuts);
        model.addAttribute("allOrders", allOrders);
        model.addAttribute("pOrders", pOrders);
        model.addAttribute("barbers", barbers);
        model.addAttribute("barber1", new Barber());
        model.addAttribute("vips",vips);
        return "manager/mhello";
    }

    @RequestMapping("/managerLogin")
    public String ManagerLogin(@RequestParam("id") int id, @RequestParam("password") String password,
                               Map<String,Object> map, HttpServletRequest request){
        Manager manager = managerService.managerlogin(id,password);
        if(manager!=null) {
            request.getSession().setAttribute("shopId",id);
            return "redirect:/BarberList";
        }
        else {
            map.put("msg","账号或密码出错");
            return "manager/mlogin";
        }
    }

    @RequestMapping("/update")
    public String updateBarber(@ModelAttribute Barber barber){
        barberService.updateBarber(barber);
        return "redirect:/BarberList";
    }

    @GetMapping("/deleteBarber/id={id}")
    public String deleteBarber(@PathVariable int id,Model model,HttpServletRequest request) {
        barberService.deleteBerber(id);

        int shopId = (int)request.getSession().getAttribute("shopId");
        List<Barber> barbers = barberService.findAllByShopId(shopId);
        List<Order>  allOrders=orderService.findAllOrder(shopId);
        List<Order>  pOrders=orderService.findPOrdersByManagerId(shopId);
        List<HairType> haircuts= hairTypeService.findAllH();
        model.addAttribute("haircuts", haircuts);
        model.addAttribute("allOrders", allOrders);
        model.addAttribute("pOrders", pOrders);
        model.addAttribute("barbers", barbers);
        model.addAttribute("barber1", new Barber());

        return "manager/barber_all::barberlist";
    }

    @GetMapping("/agree/id={id}")
    public String agree(@PathVariable int id) {
        orderService.updateOrder(id,2);
        return "redirect:/BarberList";
    }

    @GetMapping("/disagree/id={id}")
    public String disagree(@PathVariable int id) {
        orderService.updateOrder(id,3);
        return "redirect:/BarberList";
    }
    @RequestMapping("/addBarber")
    public String addBarber(@RequestParam("input_email") String email, @RequestParam("input_telephone") String telephone,
                            Model model,HttpServletRequest request){
        int shopId=(int)request.getSession().getAttribute("shopId");
        barberService.addBarber(email,telephone,shopId);

        return  "redirect:/BarberList";
    }

    @RequestMapping("/addHairtype")
    public String addHair(@RequestParam("input_name") String input_name, @RequestParam("input_price") double input_price,
                          Model model,HttpServletRequest request){
        int shopId=(int)request.getSession().getAttribute("shopId");
        System.out.println("caonima");
        hairTypeService.addHaircut(input_name,input_price,shopId);
        return  "redirect:/BarberList";
    }

    @RequestMapping("/deletehair/id={id}")
    public String deleteHaircut(@PathVariable int id,Model model,HttpServletRequest request) {
        hairTypeService.deleteHaircut(id);

        int shopId = (int)request.getSession().getAttribute("shopId");
        List<Barber> barbers = barberService.findAllByShopId(shopId);
        List<Order>  allOrders=orderService.findAllOrder(shopId);
        List<Order>  pOrders=orderService.findPOrdersByManagerId(shopId);
        List<HairType> haircuts= hairTypeService.findAllH();
        model.addAttribute("haircuts", haircuts);
        model.addAttribute("allOrders", allOrders);
        model.addAttribute("pOrders", pOrders);
        model.addAttribute("barbers", barbers);
        model.addAttribute("barber1", new Barber());
        return "manager/haircut_all::fruitlist";
    }

    @RequestMapping("/updatehair")
    public String updateHaircut(@ModelAttribute HairType haircut){
        hairTypeService.updateHaircut(haircut);
        return "redirect:/BarberList";
    }
}
