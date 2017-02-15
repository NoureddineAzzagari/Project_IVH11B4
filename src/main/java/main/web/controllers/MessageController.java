package main.web.controllers;

import main.Domain.Message;
import main.Domain.Order;
import main.Domain.Product;
import main.Domain.ProductCatalog;
import main.dataAcces.IOrderRepository;
import main.dataAcces.IProductCatalogRepository;
import main.dataAcces.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class MessageController {

	@Autowired
	private final MessageRepository messageRepository;
	@Autowired
	private final IOrderRepository orderRepository;
	@Autowired
	private final IProductCatalogRepository productCatalogRepository;

	public MessageController(MessageRepository messageRepository,
							 IOrderRepository orderRepository,
							 IProductCatalogRepository productCatalogRepository) {
		this.messageRepository = messageRepository;
		this.orderRepository = orderRepository;
		this.productCatalogRepository = productCatalogRepository;
	}

	private void createProductCatalogAndProducts() {

		// build product catalog and two products

		ProductCatalog productCatalog = new ProductCatalog();

		// right productCatalog: without id; left productCatalog: with id
		// (needed because of auto increment)
		productCatalog = productCatalogRepository.save(productCatalog);
		Product prod1 = new Product("schroefje", 2);
		Product prod2 = new Product("moertje", 1);

		// build add two products
		productCatalog.add(prod1);
		productCatalog.add(prod2);
	}

	private void createOrder() {

		// get the productCatalog
		ProductCatalog productCatalog = productCatalogRepository.findOne(1L);

		// "find" a product in the catalog and add it to the order
		Product prod = productCatalog.find(1L);

		// make a copy of the product (the copy has no id yet)
		// why a copy is made?
		Product prodCopy = new Product(prod);

		Order order = new Order();
		order = orderRepository.save(order);
		order.add(prodCopy);
	}

	@Transactional
	@GetMapping
	public ModelAndView list() {

		createProductCatalogAndProducts();

		Iterable<Message> messages = messageRepository.findAll();
		return new ModelAndView("messages/list", "messages", messages);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Message message) {
		return new ModelAndView("messages/view", "message", message);
	}

	@Transactional
	@GetMapping(params = "form")
	public String createForm(@ModelAttribute Message message) {

		createOrder();

		return "messages/form";
	}

	@PostMapping
	public ModelAndView create(@Valid Message message, BindingResult result,
							   RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("messages/form", "formErrors", result.getAllErrors());
		}
		message = this.messageRepository.save(message);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
		return new ModelAndView("redirect:/{message.id}", "message.id", message.getId());
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		this.messageRepository.delete(id);
		Iterable<Message> messages = this.messageRepository.findAll();
		return new ModelAndView("messages/list", "messages", messages);
	}

	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Message message) {
		return new ModelAndView("messages/form", "message", message);
	}

}
