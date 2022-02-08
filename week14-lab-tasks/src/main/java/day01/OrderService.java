package day01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class OrderService {

    private final List<Order> orders = new ArrayList<>();


    public void saveOrder(Order order) {
        orders.add(order);
    }

    public List<Order> findOrdersByStatus(String status) {
        return orders.stream().
                filter(o -> o.getStatus().equals(status))
                .toList();
    }

    public long countOrdersByStatus(String status) {
        return orders.stream().
                filter(o -> o.getStatus().equals(status))
                .count();
    }

    public List<Product> findProductsOverPrice(int amount) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getPrice() > amount)
                .distinct()
                .toList();
    }

    public String orderToString(Order o) {
        return o.getStatus() + " " + o.getOrderDate().toString();
    }

    public List<Order> getOrdersBetweenDates(LocalDate start, LocalDate end) {
        return orders.stream()
                .filter(o -> o.getOrderDate().isAfter(start))
                .filter(o -> o.getOrderDate().isBefore(end))
                .toList();
    }


    public List<Order> sortOrdersByStatusAndOrderDate() {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate))
                .sorted(Comparator.comparing(Order::getStatus))
                .toList();
    }

    public boolean isOrderWithLessProductThan(int number) {
        return orders.stream()
                .mapToInt(o -> o.getProducts().size())
                .anyMatch(i -> i < number);
    }


    public Order getOrderWithMaxNumberOfProducts() {
        return orders.stream()
                .max(Comparator.comparing(o -> o.getProducts().size()))
                .orElseThrow(() -> new IllegalStateException("Empty List"));
    }

    public List<Order> getOrdersWithProductInCategory(String category) {
        return orders.stream()
                .filter(o -> o.getProducts().
                        stream().anyMatch(p -> p.getCategory().equals(category)))
                .toList();
    }
}