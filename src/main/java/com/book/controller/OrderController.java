package com.book.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.model.MyBookList;
import com.book.model.Order;
import com.book.model.User;
import com.book.repository.MyBookListRepository;
import com.book.repository.OrderRepository;
import com.book.repository.UserRepository;
import com.book.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MyBookListRepository myBookListRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired 
    private OrderService service;
    
    // Show form for creating a new order
    @GetMapping("/createOrder")
    public String createOrderForm(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users); // Thymeleaf will use ${users}
        model.addAttribute("order", new Order()); // empty order object for form binding
        return "createOrder";
    }

    // Get books for selected user via AJAX
    @GetMapping("/getBooks")
    @ResponseBody
    public List<MyBookList> getUserBooks(@RequestParam("userId") int userId) {
        return myBookListRepo.findByUserId(userId); // Get books by userId
    }
    @PostMapping("/createOrder")
    public String saveOrder(@ModelAttribute("order") Order order,
                            @RequestParam("userId") int userId,
                            @RequestParam("bookIds") List<Integer> bookIds,
                            @RequestParam("shippingOption") String shippingOption) {
        
        User user = userRepo.findById(userId).orElseThrow();
        List<MyBookList> books = myBookListRepo.findAllById(bookIds);

        // Book total
        double bookTotal = books.stream().mapToDouble(MyBookList::getPrice).sum();

        // Shipping cost logic
        double shippingCost = 0;
        if ("Express".equalsIgnoreCase(shippingOption)) {
            shippingCost = 15;
        } else if ("Standard".equalsIgnoreCase(shippingOption)) {
            shippingCost = 5;
        }

        // Final total = book total + shipping
        double finalTotal = bookTotal + shippingCost;

        // Set order data
        order.setUser(user);
        order.setBooks(books);
        order.setShippingOption(shippingOption);
        order.setTotalPrice(finalTotal); // ✅ Now includes shipping
        order.setOrderStatus("Pending");
        order.setOrderDate(new Date());

        orderRepo.save(order);
        return "redirect:/orders";
    }


    // Show a list of all orders
    @GetMapping("/orders")
    public String viewOrders(Model model) {
        List<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);
        return "orderList";  // The template name to render the list of orders
    }

    // Delete an order by id
    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") int orderId) {
        service.deleteOrder(orderId);
        return "redirect:/orders";
    }

    // Edit an existing order - Fetch existing order and show it in the form
    @GetMapping("/createOrder/{id}")
    public String editOrder(@PathVariable("id") int orderId, Model model) {
        // Fetch the existing order by ID
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));

        // Fetch all users to populate the user dropdown
        List<User> users = userRepo.findAll();

        // Add the order and users to the model for the form
        model.addAttribute("order", order);
        model.addAttribute("users", users);

        // Return the form template for editing the order
        return "createOrder"; 
    }
    
    @PostMapping("/orders/updateStatus")
    public String updateOrderStatus(@RequestParam("orderId") Integer orderId,
                                    @RequestParam("status") String status) {
        service.updateOrderStatus(orderId, status);
        return "redirect:/orders";  // refresh the table after updating
    }
}
