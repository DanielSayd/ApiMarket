package Platzi.Marke.web.controller;

import Platzi.Marke.domain.Product;
import Platzi.Marke.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code=200,message = "OK")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code=200,message = "OK"),
            @ApiResponse(code=400,message = "Product no found")
    })
    public ResponseEntity<Product> getProduct(
            @ApiParam(value = "The id of product",required = true,example = "7")
            @PathVariable("id") int productId
            ){
        return productService.getProduct(productId).map(product -> new ResponseEntity<>(product,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    public  ResponseEntity<List<Product>> getByCategory(@PathVariable("id") int categoryId){
        return productService.getByCategory(categoryId).map(product -> new ResponseEntity<>(product,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
        //Como un bulean
       return productService.delete(productId)?new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.NOT_FOUND);
    }


}
