package com.example.examm4.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class Contract {
    @Id
    @Pattern(regexp = "^MGD-[0-9]{4}$", message = "Mã giao dịch phải có định dạng MGD-XXXX")
    private String id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Không được để trống")
    private Customer customer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotBlank(message = "Chọn loại dịch vụ")
    private String typeService;
    @NotNull(message = "Không được để trống")
    @Min(value = 500000, message = "Đơn giá phải lớn hơn 500000 đ")
    private Integer price;
    @NotNull(message = "Không được để trống")
    @Min(value = 20, message = "Diện tích phải lớn hơn 20")
    private Integer area;

    public Contract() {
    }

    public Contract(String id, Customer customer, Date date, String typeService, Integer price, Integer area) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.typeService = typeService;
        this.price = price;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
}
