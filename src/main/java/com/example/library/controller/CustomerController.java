package com.example.library.controller;

import com.example.library.entity.HairType;
import com.example.library.entity.Customer;
import com.example.library.entity.Order;
import com.example.library.service.HairTypeService;
import com.example.library.service.CustomerService;
import com.example.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HairTypeService hairTypeService;


    @RequestMapping("/user")
    public String index(Model model) {
        model.addAttribute("user",new Customer());
        return "user/login";
    }

    @RequestMapping("/userRegister")
    public String register(Model model) {
        model.addAttribute("user",new Customer());
        return "user/register";
    }

    @RequestMapping("/userDoRegister")
    public String doregister(@ModelAttribute Customer user, Model model) {
        userService.addUser(user);
        return "user/login";
    }

    @PostMapping("/userLogin")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, Map<String,Object> map, HttpServletRequest request) {
        Customer user=userService.findUserByEmail(email);

        if(user==null){
            map.put("msg","账户不存在");
            return "user/login";
        }else{
            if(user.getPassword().equals(password)){
                request.getSession().setAttribute("userId",user.getId());
                return "redirect:/userHello";
            }
            else{
                map.put("msg","密码错误");
                return "user/login";
            }
        }
    }

    @RequestMapping("/userHello")
    public String hello(Model model,HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null)
            return "user/login";
        int id=(int)request.getSession().getAttribute("userId");
        List<HairType> hairTypes= hairTypeService.findAllHairtype();
        model.addAttribute("hairTypes",hairTypes);
        return "user/index";
    }

    @RequestMapping("/userOrder")
    public String getOrder(Model model,HttpServletRequest request) {
        if(request.getSession().getAttribute("userId")==null)
            return "user/login";
        int id=(int)request.getSession().getAttribute("userId");
        List<Order> orderList=orderService.findOrderByUserId(id);
        List<Order> corderList=orderService.findCOrderByUserId(id);
        model.addAttribute("orderList",orderList);
        model.addAttribute("corderList",corderList);
        return "user/order";
    }

    @RequestMapping("/userVip")
    public String getVip(Model model,HttpServletRequest request) {
        if(request.getSession().getAttribute("userId")==null)
            return "user/login";
        int id=(int)request.getSession().getAttribute("userId");

        return "user/vip";
    }


    @RequestMapping("/userAddOrder")
    public String addOrder(@RequestParam("hairtypeId")int hairtypeId, @RequestParam("time") Date time, Model model, HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null)
            return "user/login";
        int id=(int)request.getSession().getAttribute("userId");
        Order order=new Order();
        order.setUserid(id);
        order.setTime(time);
        order.setBarberhairtypeid(hairtypeId);
        orderService.addOrder(id,hairtypeId,time);
        return "redirect:/userHello";
    }

    @RequestMapping("/userCancelOrder")
    public String cancelOrder(@RequestParam("orderId")int orderId, Model model, HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null)
            return "user/login";
        int id=(int)request.getSession().getAttribute("userId");
        orderService.updateOrder(orderId,4);

        return "redirect:/userOrder";
    }

    @RequestMapping("/userFinishOrder")
    public String finishOrder(@RequestParam("orderId")int orderId,Model model, HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null)
            return "user/login";
        int id=(int)request.getSession().getAttribute("userId");

        orderService.updateOrder(orderId,5);
        return "redirect:/userOrder";
    }

    @RequestMapping("/userChangeTelephone")
    @ResponseBody
    public String changeTelephone(@RequestParam("telephone") String telephone,
                                  Model model,HttpServletRequest request){

        if(request.getSession().getAttribute("userId")==null)
            return "user/login";
        int id=(int)request.getSession().getAttribute("userId");
        Customer user=userService.findUserById(id);
        user.setTelephone(telephone);
        userService.updateUser(user);
        return "success";
    }

    @RequestMapping("/userChangeEmail")
    @ResponseBody
    public String changeEmail(@RequestParam("input_email") String email,Model model,HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null)
            return "noSession";
        int id=(int)request.getSession().getAttribute("userId");
        Customer user=userService.findUserById(id);
        user.setEmail(email);
        userService.updateUser(user);
        return "success";
    }

    @RequestMapping("/userChangePassword")
    @ResponseBody
    public String changePassword(@RequestParam("input_password1") String password1,
                                 @RequestParam("input_password2") String password2,
                                 @RequestParam("input_password3") String password3,
                                 Model model,HttpServletRequest request){

      if(request.getSession().getAttribute("userId")==null)
            return "noSession";
        int id=(int)request.getSession().getAttribute("userId");
        Customer user=userService.findUserById(id);
        if(!user.getPassword().equals(password1))
            return "error";
        if(!password2.equals(password3))
            return "error1";
        user.setPassword(password2);
        userService.updateUser(user);
        return "success";
    }


    @RequestMapping("/userLogout")
    public String exit(HttpServletRequest request){
        request.getSession().invalidate();
        return "user/login";
    }
}