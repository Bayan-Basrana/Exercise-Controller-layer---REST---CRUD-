package com.example.bank.Controller;

import com.example.bank.ApiRespone.ApiResponse;
import com.example.bank.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/bank")
public class BankController {
    ArrayList<Customers> customers=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customers> getCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customers customer){
        customers.add(customer);
        return new ApiResponse("customer added successfully");
    }
@PutMapping("/put/{id}")
    public ApiResponse updateCustomer( @PathVariable int id,    @RequestBody Customers customer){
        for(Customers c:customers){
            if(c.getId()==id){
                c.setUserName(customer.getUserName());
                c.setBalance(customer.getBalance());
            }
        }return new ApiResponse("customer updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteCustomer(@PathVariable int id) {
        customers.removeIf(customer -> customer.getId() == id);
        return new ApiResponse("customer deleted successfully");
    }

    @PutMapping("/withdraw/{id}")
public ApiResponse withdraw (@PathVariable int id, @RequestBody double amount){
        for(Customers customers1:customers){
            if(customers1.getId()==id){
                if (customers1.getBalance()>=amount){
                    customers1.setBalance(customers1.getBalance() - amount);
                    return new ApiResponse("customer withdrawal successfully");
            }else{
                return new ApiResponse("Insufficient balance");
        }}}
    return new ApiResponse("customer not found");
}

@PutMapping("/deposit/{id}")
public ApiResponse deposit(@PathVariable int id,  @RequestBody double amount){
        for(Customers customers1:customers){
            if(customers1.getId()==id){
                customers1.setBalance(customers1.getBalance()+amount);
                return new ApiResponse("customer deposit successfully");
            }
        }
        return new ApiResponse(" customer not found");

}


}
