package org.interview.transfer.pecs;

import java.util.ArrayList;
import java.util.List;

public class CustomerService<T> {
    //method acts as Consumer - add
    public void consume(List<? super Customer> customers) {
//        List<? super CustomerPe> customers = new ArrayList<>();
        customers.add(new CustomerPe("pe","123"));
        customers.add(new CustomerPeSubAImpl("pe","123"));
        customers.add(new CustomerLe("1255466"));
    }

    //method acts as Consumer - add
    public void produce(List<? extends Customer> customers) {
//        List<? super CustomerPe> customers = new ArrayList<>();
//        customers.add(new CustomerPe("pe","123"));
//        customers.add(new CustomerPeSubAImpl("pe","123"));
//        customers.add(new CustomerLe("1255466"));
        customers.get(1);
        customers.forEach(c -> c.setId(1));
    }


    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
//        customerService.proceed(new CustomerPe());
    }
}
