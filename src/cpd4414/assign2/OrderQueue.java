/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 * Updated 2015 Mark Russell <mark.russell@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cpd4414.assign2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class OrderQueue {
List<Order> orderList = new ArrayList<>();

    Queue<Order> orderQueue = new ArrayDeque<>();

    public void add(Order order) throws Exception {
        if (order.getCustomerId().isEmpty() && order.getCustomerName().isEmpty()) {
            throw new Exception();
        }
        if (order.getListOfPurchases().isEmpty()) {
            throw new Exception();
        }
        orderQueue.add(order);
        order.setTimeReceived(new Date());
    }

   
    
    public Order next() {
        return orderQueue.peek();
    }

    void process(Order next) throws NoTimeReceivedException {
      if (next.equals(next())) {
//            boolean isOkay = true;
//            for (Purchase p : next.getListOfPurchases()) {
//                if (Inventory.getQuantityForId(p.getProductId()) < p.getQuantity())
//                    isOkay = false;
//            }
//            if (isOkay) {
            orderList.add(orderQueue.remove());
            next.setTimeProcessed(new Date());
//            }
        } else if (next.getTimeReceived() == null) {
            throw new NoTimeReceivedException();
        }
    }

        void fulfill(Order next) throws NoTimeReceivedException, NoTimeProcessedException {
        if (next.getTimeReceived() == null) {
            throw new NoTimeReceivedException();
        }
        if (next.getTimeProcessed() == null) {
            throw new NoTimeProcessedException();
        }
        if (orderList.contains(next)) {
            next.setTimeFulfilled(new Date());
        }
    }
    

    public class NoCustomerException extends Exception {

        public NoCustomerException() {
            super("No Customer Exits");
        }
    }

    public class NoPurchasesException extends Exception {

        public NoPurchasesException() {
            super("No Purchase is Done Yet");
        }
    }
    
     public class NoTimeReceivedException extends Exception {

        public NoTimeReceivedException() {
            super("No Time Received on this Order");
        }
    }

    public class NoTimeProcessedException extends Exception {

        public NoTimeProcessedException() {
            super("No Time Processed on this Order");
        }
    }
     
}
