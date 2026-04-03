package com.model.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String description;
    private String location;
    private String type;
    private String category;
    private String contact;

    private String claimedBy;   // ✅ NEW FIELD

    private LocalDate reportDate;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    public ItemEntity() {
        this.status = ItemStatus.OPEN;
        this.reportDate = LocalDate.now();
    }

    public Long getId() { return id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getClaimedBy() { return claimedBy; }
    public void setClaimedBy(String claimedBy) { this.claimedBy = claimedBy; }

    public LocalDate getReportDate() { return reportDate; }
    public void setReportDate(LocalDate reportDate) { this.reportDate = reportDate; }

    public ItemStatus getStatus() { return status; }
    public void setStatus(ItemStatus status) { this.status = status; }
}