package com.ec.onlineplantnursery.web;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.service.ICustomerServiceImpl;
import com.ec.onlineplantnursery.dto.OrderDTO;
import com.ec.onlineplantnursery.exceptions.OrderIdNotFoundException;
import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.order.repository.IOrderRepository;
import com.ec.onlineplantnursery.order.service.IOrderServiceImpl;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.IPlantRepository;
import com.ec.onlineplantnursery.plant.service.IPlantServiceImpl;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.service.IPlanterServiceImpl;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.service.ISeedServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/api/order")
@Api(value = "Online Nursery Application",description = "Customer can order planters")
public class OrderRestController {
	Logger log = org.slf4j.LoggerFactory.getLogger(OrderRestController.class);
	
	@Autowired
	private IOrderServiceImpl ordService;
	
	@Autowired
	private ICustomerServiceImpl custService;
	
	@Autowired
	private ISeedServiceImpl seedService;
	
	@Autowired
	private IPlantServiceImpl plantService;
	
	@Autowired
	private IPlanterServiceImpl planterService;
	
	public OrderRestController() {
		System.out.println("Nursery Rest Controller Cunstructor");
	}
	
	/*Method Name:insertProduct
	 *Parameters:Order
	 *ReturnType:OrderDTO
	 *Author Name:Suhana
	 *Created Date: 23/05/2021 */
	@ApiOperation(value = "Order post mapping" , response = Order.class)
	@PostMapping("/order")
	public OrderDTO insertProduct(@RequestBody @Valid Order order) throws ResourceNotFoundException{
		log.info("inside insert orders");
		Optional<Order> optionalOrder = ordService.addOrder(order);
		Order order1 = optionalOrder.get();
		OrderDTO orderDto = ordService.displayOrderDetails(order1);
		return orderDto;
	}
	
	/*Method Name:viewAllOrders
	 *Parameters:No Parameters
	 *ReturnType:List<OrderDTO>
	 *Author Name:Suhana
	 *Created Date: 23/05/2021 */
	@ApiOperation(value = "Order Get mapping to fetch all orders" , response = List.class)
	@GetMapping("/orders")
	public List<OrderDTO> viewAllOrders() throws ResourceNotFoundException {
		log.info("Get all Orders");
		List<Order> orders = ordService.viewAllOrders();
		return ordService.displayAllOrders(orders);
	}

	/*Method Name:viewPlantersListByOrderId
	 *Parameters:id
	 *ReturnType:List<Planter>
	 *Author Name:Suhana
	 *Created Date: 23/05/2021 */
	@ApiOperation(value = "Order Get mapping to fetch list of planters by order id")
	@GetMapping("/order/planter/{id}")
	public List<Planter> viewPlantersListByOrderId(@PathVariable int id) throws ResourceNotFoundException {
		return ordService.viewPlanterByOrderId(id);
	}
	
	/*Method Name:updateByOrder
	 *Parameters:Order
	 *ReturnType:Order
	 *Author Name:Suhana
	 *Created Date: 23/05/2021 */
	@ApiOperation(value = "Order Put mapping to fetch and update order" , response = List.class)
	@PutMapping("/order/update")
	public Order updateByOrder(@RequestBody Order o)throws ResourceNotFoundException {
		log.info("Update order");
		return ordService.updateOrder(o);
	}
	
	/*Method Name:deleteOrder
	 *Parameters:oid
	 *ReturnType:Order
	 *Author Name:Suhana
	 *Created Date: 23/05/2021 */
	@ApiOperation(value = "Order Delete mapping to delete order" , response = Order.class)
	@DeleteMapping("/order/delete/{oid}")
	public Order deleteOrder(@PathVariable int oid) {
		log.info("Delete order");
		return ordService.deleteOrder(oid);
	}
	
	/*Method Name:viewOrderById
	 *Parameters:id
	 *ReturnType:OrderDTO
	 *Author Name:Suhana
	 *Created Date: 23/05/2021 */
	@GetMapping("/order/{id}")
	public OrderDTO viewOrderById(@PathVariable int id) throws OrderIdNotFoundException, ResourceNotFoundException  {
		Optional<Order> order = ordService.viewOrder(id);
		Order returnOrder = order.get();
		return ordService.displayOrderDetails(returnOrder);
		
	}
	

}
