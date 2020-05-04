package com.apparelshop.dataparsingadidasservice.dto;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.util.List;

@RedisHash("offer")
public class Offer {

    @Id
    private int articleId;
    private String offerId;
    private BigDecimal price;
    private BigDecimal salesPrice;
    private String color;
    private Boolean orderable;
    private String productUrl;
    private String productImages;

    public Offer() {
    }

    public Offer(int articleId, BigDecimal price, BigDecimal salesPrice, String offerId, String color, Boolean orderable, String productUrl, String productImages) {
        this.articleId = articleId;
        this.price = price;
        this.salesPrice = salesPrice;
        this.offerId = offerId;
        this.color = color;
        this.orderable = orderable;
        this.productUrl = productUrl;
        this.productImages = productImages;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getOrderable() {
        return orderable;
    }

    public void setOrderable(Boolean orderable) {
        this.orderable = orderable;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {

        StringBuilder stringBuilder = new StringBuilder();

        for (String productImage : productImages)
        {
            stringBuilder.append(productImage).append("|");
        }

        String s = stringBuilder.toString();
        s = s.substring(0, s.length() - 1);

        this.productImages = s;
    }
}
