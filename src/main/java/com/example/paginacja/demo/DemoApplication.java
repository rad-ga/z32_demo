package com.example.paginacja.demo;

import com.example.paginacja.demo.model.Order;
import com.example.paginacja.demo.model.OrderItem;
import com.example.paginacja.demo.repository.OrderItemRepository;
import com.example.paginacja.demo.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadSampleData(
			OrderRepository orderRepository,
			OrderItemRepository orderItemRepository
	) {
		return args -> {
			// First order with two items
			Order order1 = new Order("Alice");
			OrderItem item1a = new OrderItem("Widget", 3);
			OrderItem item1b = new OrderItem("Gadget", 5);
			order1.addItem(item1a);
			order1.addItem(item1b);
			orderRepository.save(order1);

			// Second order with one item
			Order order2 = new Order("Bob");
			OrderItem item2a = new OrderItem("Thingamajig", 2);
			order2.addItem(item2a);
			orderRepository.save(order2);

			// Directly create an item for an existing order
			Order order3 = new Order("Carol");
			orderRepository.save(order3);
			OrderItem item3 = new OrderItem("Doohickey", 10);
			item3.setOrder(order3);
			orderItemRepository.save(item3);
		};
	}
}
