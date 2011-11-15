package uk.co.devooght.stock;

import java.math.BigDecimal;

public class SkuDTO implements grails.plugins.dto.DTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal price;
    private ProductDTO product;
    private String stockCode;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public ProductDTO getProduct() { return product; }
    public void setProduct(ProductDTO product) { this.product = product; }
    public String getStockCode() { return stockCode; }
    public void setStockCode(String stockCode) { this.stockCode = stockCode; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SkuDTO[");
        sb.append("\n\tid: " + this.id);
        sb.append("\n\tprice: " + this.price);
        sb.append("\n\tproduct: " + this.product);
        sb.append("\n\tstockCode: " + this.stockCode);
        sb.append("]");
        return sb.toString();
    }
}
