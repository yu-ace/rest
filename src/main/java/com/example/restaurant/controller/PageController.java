package com.example.restaurant.controller;

import com.example.restaurant.model.*;
import com.example.restaurant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    IDishesService dishesService;
    @Autowired
    IOrderItemService orderItemService;
    @Autowired
    IUserService userService;
    @Autowired
    ITableService tableService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IShoppingCartService shoppingCartService;
    @Autowired
    IStatisticsService statisticsService;


    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/userBoard",method = RequestMethod.GET)
    public String userBoard(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        model.addAttribute("user",user);
        return "userBoard";
    }

    @RequestMapping(path = "/dishesList",method = RequestMethod.GET)
    public String dishes(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<Dishes> dishesList = dishesService.getDishesList(of);
        model.addAttribute("dishesList",dishesList);
        return "dishes";
    }

    @RequestMapping(path = "/addDishesList",method = RequestMethod.GET)
    public String addDishesList(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        return "addDishesList";
    }

//    @RequestMapping(path = "/orderItemForUser",method = RequestMethod.GET)
//    public String orderItemForUser(Model model,HttpSession session){
//        User user = (User) session.getAttribute("user");
//        if(user == null){
//            model.addAttribute("error","你已退出系统，请重新登录");
//            return "login";
//        }
//        PageRequest of = PageRequest.of(0, 10);
//        Page<OrderItem> orderItem = orderItemService.getOrderItem(of);
//        model.addAttribute("orderItem",orderItem);
//        return "orderItemForUser";
//    }

    @RequestMapping(path = "/userList",method = RequestMethod.GET)
    public String userList(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        PageRequest of = PageRequest.of(0, 10);
        Page<User> userList = userService.getUserList(of);
        model.addAttribute("userList",userList);
        return "userList";
    }

    @RequestMapping(path = "/changeUserInformation",method = RequestMethod.GET)
    public String changeUserInformation(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        return "changeUserInformation";
    }

    @RequestMapping(path = "/newUser",method = RequestMethod.GET)
    public String newUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        return "newUser";
    }

    @RequestMapping(path = "/deleteUser",method = RequestMethod.GET)
    public String deleteUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        return "deleteUser";
    }

    @RequestMapping(path = "/tableList",method = RequestMethod.GET)
    public String tableList(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        PageRequest of = PageRequest.of(0, 10);
        Page<Table> tableList = tableService.getTableList(0,of);
        model.addAttribute("tableList",tableList);
        return "tableList";
    }

    @RequestMapping(path = "/table",method = RequestMethod.GET)
    public String table(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        return "table";
    }

    @RequestMapping(path = "/pay",method = RequestMethod.GET)
    public String bill(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        PageRequest of = PageRequest.of(0, 10);
        Page<Order> orderList = orderService.getOrderList(of);
        model.addAttribute("orderList",orderList);
        return "pay";
    }

    @RequestMapping(path = "/orderItemForUser",method = RequestMethod.GET)
    public String orderItemForUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        PageRequest of = PageRequest.of(0, 10);
        Page<Statistics> statisticsList = statisticsService.getStatisticsList(of);
        model.addAttribute("statisticsList",statisticsList);
        return "orderItemForUser";
    }

    @RequestMapping(path = "/addOrder",method = RequestMethod.GET)
    public String changeOrder(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        return "addOrder";
    }

    @RequestMapping(path = "/reduceOrder",method = RequestMethod.GET)
    public String reduceOrder(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        return "reduceOrder";
    }



    //厨师系统
    @RequestMapping(path = "/cookBoard",method = RequestMethod.GET)
    public String cookBoard(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        model.addAttribute("user",user);
        return "cookBoard";
    }

    @RequestMapping(path = "/startCooking",method = RequestMethod.GET)
    public String startCooking(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        return "startCooking";
    }

    @RequestMapping(path = "/rest",method = RequestMethod.GET)
    public String rest(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        return "rest";
    }






    //顾客端


    @RequestMapping(path = "/customer",method = RequestMethod.GET)
    public String customer(){
        return "customer";
    }

    @RequestMapping(path = "/customerIndex",method = RequestMethod.GET)
    public String dishesIndex(Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<Dishes> dishesList = dishesService.getDishesListByCategoryId(1,of);
        model.addAttribute("dishesList",dishesList);
        return "customerIndex";
    }

    @RequestMapping(path = "/lookingFor",method = RequestMethod.GET)
    public String lookingFor(Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<Dishes> dishesList = dishesService.getDishesListByCategoryId(1,of);
        model.addAttribute("dishesList",dishesList);
        return "lookingFor";
    }

    @RequestMapping(path = "/shoppingCart",method = RequestMethod.GET)
    public String shoppingCart(Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<ShoppingCart> shoppingItemPage = shoppingCartService.getShoppingItemPage(customer.getId(), 0, of);
        model.addAttribute("shoppingItem",shoppingItemPage);
        return "shoppingCart";
    }

    @RequestMapping(path = "/orderedMenu",method = RequestMethod.GET)
    public String orderedMenu(Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("tip","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<OrderItem> orderLists = orderItemService.getOrderItemByCustomerIdAndHistoryOrderItem(customer.getId(),of);
        model.addAttribute("orderList",orderLists);
        return "orderedMenu";
    }




}