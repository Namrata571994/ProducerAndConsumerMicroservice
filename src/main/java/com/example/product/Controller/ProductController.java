package com.example.product.Controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import com.example.product.Entity.Product;
import com.example.product.Service.ProductService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.server.PayloadTooLargeException;

@RestController
@Validated
@RequestMapping(value = "/product")
public class ProductController {
//1KB=1024BYTE,1MB=1024KB
	private static final int MAX_PAYLOAD_SIZE = 1;
	@Autowired
	private	ProductService productservice;
	
	//With out using ResponceEntity
	//localhost:8081/products/price?price=50&name=watch,freg
	@GetMapping("/products/price")
	public List<Product> getProductsByPrice(@RequestParam @Validated int price){
		return productservice.getProductsGreaterthan(price);
		
	}

	@GetMapping("/getallproducts")
	public List<Product> getAllProducts(){
		return productservice.getAllProducts();
	}

	//@GetMapping(value = "/getproductbyid/{id}",produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@GetMapping(value = "/getproductbyid/{id}")
	public Product getProductById(@PathVariable  @Validated @Min(1) int id){
		return productservice.getProductById(id);
	}


	@PostMapping("/createOneproduct")
	public Product createoneProduct(@RequestBody @Validated Product product, HttpServletRequest request) {
		long contentLength = request.getContentLength();
		if(contentLength>MAX_PAYLOAD_SIZE){
			throw new PayloadTooLargeException(new Throwable("Payload too large testing"));
		}
	return productservice.createOneProduct(product);
	}

/*	@PostMapping("/createOneproduct")
	public ResponseEntity<Product> createoneProduct(@RequestBody @Validated Product product, HttpServletRequest request) {
		long contentLength=request.getContentLength();
		Product producterror=new Product();
		producterror.setName("\"Error: Payload is too large. Please reduce the size of the request.\"");
		//ErrorResponse errorResponse = new ErrorResponse("Payload is too large. Please reduce the size of the request.");
		if(contentLength>MAX_PAYLOAD_SIZE){
			return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(producterror);
		}
		Product product1=productservice.createOneProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product1);

	}*/


//	//http://localhost:8081/xyz   url
//	@RequestMapping(method = RequestMethod.POST, value = "/xyz")
//
//	@PostMapping(value = "/xyz" , produces = MediaType.ALL_VALUE	)
//	public ResponseEntity<String> createXyz(@RequestBody String s) {
//		return new ResponseEntity<>(s, HttpStatus.CREATED);
//
//
//	}
	
	@PostMapping("/createproduct")
	public ResponseEntity<List<Product>> createProduct1(@RequestBody List<Product> product) {

		List<Product> product2 = productservice.createProduct(product);
		return new ResponseEntity<>(product2,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/updateproduct")
	public List<Product>  updateProductDetails(@RequestBody @Validated @Min(2) List<Product> product)   {

		//List<Product> product2 = productservice.getProductById(id);
		return productservice.updateProduct(product);
		
		 
	}

	@PutMapping("/updateproduct/{id}")
	public Product updateProductDetails(@PathVariable @Validated @NotEmpty int id, @RequestBody Product product)  {

		//Product product2 = productservice.getProductById(id);


		return productservice.updateProduct(id, product);


	}
	
	 @RequestMapping(value = "/getalloweduserOperations", method = RequestMethod.OPTIONS)
	 public void getalloweduserOperations(@PathVariable @Validated int id,HttpServletResponse responce ) {
		 productservice.getalloweduserOperations(responce);
		 
		 
	 }
	 
	 @RequestMapping(value = "/getusermetadata/{id}", method = RequestMethod.HEAD)
	 public void wheatherUserExists(@PathVariable @Validated @NotEmpty int id,HttpServletResponse responce ) {
		 productservice.wheatherUserExists(id,responce);
	
	
	

}
}
