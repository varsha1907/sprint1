package com.sprint1.evaluation.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="PRODUCT")
public class Product {

	
	  	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @ApiModelProperty(notes = "The database generated product ID")
	    @Column(name="ID")
	    private Integer id;
	  
	  
	 //   @Version
	    @ApiModelProperty(notes = "Colour of the product")
	    @Column(name="PRODUCT_COLOUR")
	    private String productcolour;
	    
	    @ApiModelProperty(notes = "The application-specific product ID")
	    @Column(name="PRODUCT_ID")
	    private String productId;
	    
	    @ApiModelProperty(notes = "The product description")
	    @Column(name="DESCRIPTION")
	    private String description;
	    
	    @ApiModelProperty(notes = "The image URL of the product")
	    @Column(name="IMAGE_URL")
	    private String imageUrl;
	    
	    @ApiModelProperty(notes = "The price of the product", required = true)
	    @Column(name="PRICE")
	    private BigDecimal price;

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	   

		public String getProductcolour() {
			return productcolour;
		}

		public void setProductcolour(String productcolour) {
			this.productcolour = productcolour;
		}

		public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getProductId() {
	        return productId;
	    }

	    public void setProductId(String productId) {
	        this.productId = productId;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	    public BigDecimal getPrice() {
	        return price;
	    }

	    public void setPrice(BigDecimal price) {
	        this.price = price;
	    }

}
