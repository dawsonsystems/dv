package uk.co.devooght.stock;

import java.math.BigDecimal;
import java.util.Set;

public class ProductDTO implements grails.plugins.dto.DTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal costPrice;
    private String name;
    private String productCode;
    private Set<SkuDTO> skus;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getCostPrice() { return costPrice; }
    public void setCostPrice(BigDecimal costPrice) { this.costPrice = costPrice; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public Set<SkuDTO> getSkus() { return skus; }
    public void setSkus(Set<SkuDTO> skus) { this.skus = skus; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductDTO[");
        sb.append("\n\tid: " + this.id);
        sb.append("\n\tcostPrice: " + this.costPrice);
        sb.append("\n\tname: " + this.name);
        sb.append("\n\tproductCode: " + this.productCode);
        sb.append("\n\tskus: " + this.skus);
        sb.append("]");
        return sb.toString();
    }
}
