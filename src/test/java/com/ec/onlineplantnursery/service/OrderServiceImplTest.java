package com.ec.onlineplantnursery.service;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.ec.onlineplantnursery.customer.entity.Address;
import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.repository.ICustomerRepository;
import com.ec.onlineplantnursery.customer.service.ICustomerServiceImpl;
import com.ec.onlineplantnursery.dto.OrderDTO;
import com.ec.onlineplantnursery.exceptions.OrderIdNotFoundException;
import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.order.repository.IOrderRepository;
import com.ec.onlineplantnursery.order.service.IOrderServiceImpl;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.seed.entity.Seed;

@SpringBootTest
public class OrderServiceImplTest {
	
	IOrderRepository orderRepo;
	ICustomerRepository custRepo;

	private static IOrderServiceImpl orderService;
	private static ICustomerServiceImpl customerService;
	private static AutoCloseable ac;

	@BeforeEach
	public void doinit() {
		orderRepo = mock(IOrderRepository.class);
		custRepo = mock(ICustomerRepository.class);
		
		orderService = new IOrderServiceImpl(orderRepo);
		customerService = new ICustomerServiceImpl(custRepo);
		
		ac = MockitoAnnotations.openMocks(this);
		
	}
	@AfterEach
	public void doEnd() throws Exception{
		ac.close();
	}
	
	@Test
	void testSaveOrder() throws ResourceNotFoundException{
		List<Integer> planterIds = Arrays.asList(1, 2); 
		//Address address = new Address(20, "8-90", "Yusufguda", "Hyderabad", "Telangana", 500045);
		//Customer cust = new Customer(1, "Suhana", "suhana123@gmail.com", "suhana2268", "123456", address);
		//Seed seed = new Seed(1, "mango", "10 days", "twicw a day", "hard", "30 degrees", "dry", "Mango seed", 20 , 70.0, 2);
		//Plant plant = new Plant(1, 4,35, "Rose","2 weeks", "No", "Medium", "25 degrees", "short", "Rose plant", "half", 50.00);
		//Planter p1 = new Planter(1, 2.3f, 4, 3, 1, "round", 46, 25, null, seed, 2);
		//Planter p2 = new Planter(1, 5.3f, 2, 2, 1, "square", 20, 30, plant, null, 1);
		//List<Planter> pList = Arrays.asList(p1, p2);
		
		
		
		
		orderService.setCustService(customerService);
		Customer cust = mock(Customer.class);
		List<Planter> pList = mock(List.class);
		LocalDate date = LocalDate.now();
		//Order input = new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList);
		//OrderDTO savedOrder = new OrderDTO(101, 20.00, pList, cust.getCustomerName(), cust.getAddress());
		//Optional<Order> saved = Optional.of(new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList));
		Order input = mock(Order.class);
		Order saved = mock(Order.class);
		
		when(orderRepo.save(input)).thenReturn(saved);
		orderService.addOrder(input);
		verify(orderRepo).save(saved);
	
	}
	
	@Test
	void testGetOrderById() throws OrderIdNotFoundException {
		int input = 101;
		Order order = mock(Order.class);
		Optional<Order> optional = Optional.of(order);
		
		
		when(orderRepo.findById(input)).thenReturn(optional);
		orderService.viewOrder(input);
		verify(orderRepo).findById(input);
	}
	
	@Test
	@DisplayName("Test-Get All Orders, Args:- No Args to pass")
	void testGetAllOrders() {
		List<Order> orderList = mock(List.class);
		when(orderRepo.findAll()).thenReturn(orderList);
		orderService.viewAllOrders();
		verify(orderRepo).findAll();
	}
	
	
	/**@Test
	@Disabled
	void testUpdateOrder() throws ResourceNotFoundException {
		List<Integer> planterIds = Arrays.asList(1, 2); 
		Address address = new Address(20, "8-90", "Yusufguda", "Hyderabad", "Telangana", 500045);
		Customer cust = new Customer(1, "Suhana", "suhana123@gmail.com", "suhana2268", "123456", address);
		Seed seed = new Seed(1, "mango", "10 days", "twicw a day", "hard", "30 degrees", "dry", "Mango seed", 20 , 70.0, 2);
		Plant plant = new Plant(1, 4,35, "Rose","2 weeks", "No", "Medium", "25 degrees", "short", "Rose plant", "half", 50.00);
		Planter p1 = new Planter(1, 2.3f, 4, 3, 1, "round", 46, 25, null, seed, 2);
		Planter p2 = new Planter(1, 5.3f, 2, 2, 1, "square", 20, 30, plant, null, 1);
		List<Planter> pList = Arrays.asList(p1, p2);
		LocalDate date = LocalDate.now();
		Order input = new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList);
		Order savedOrder = new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList);
		
		when(orderRepo.save(input)).thenReturn(savedOrder);
		orderService.updateOrder(input);
		verify(orderRepo).save(input);
		
	}**/
	
	public OrderServiceImplTest() {
		// TODO Auto-generated constructor stub
	}

}
