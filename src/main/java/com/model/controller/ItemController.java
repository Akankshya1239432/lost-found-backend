package com.model.controller;

import org.springframework.web.bind.annotation.*;
import com.model.entity.ItemEntity;
import com.model.entity.ItemStatus;
import com.model.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 🔹 GET ALL ITEMS
    @GetMapping
    public List<ItemEntity> getAllItems() {
        return itemService.getAllItems();
    }

    // 🔹 ADD ITEM
    @PostMapping
    public ItemEntity addItem(@RequestBody ItemEntity item) {
        return itemService.addItem(item);
    }

    // 🔹 SEARCH BY NAME
    @GetMapping("/search")
    public List<ItemEntity> searchItems(@RequestParam String name) {
        return itemService.searchItems(name);
    }

    // 🔹 FILTER BY TYPE
    @GetMapping("/type/{type}")
    public List<ItemEntity> filterByType(@PathVariable String type) {
        return itemService.filterByType(type);
    }

    // 🔹 FILTER BY LOCATION
    @GetMapping("/location/{location}")
    public List<ItemEntity> filterByLocation(@PathVariable String location) {
        return itemService.filterByLocation(location);
    }

    // 🔹 FILTER BY CATEGORY
    @GetMapping("/category/{category}")
    public List<ItemEntity> filterByCategory(@PathVariable String category) {
        return itemService.filterByCategory(category);
    }

    // 🔹 UPDATE STATUS
    @PutMapping("/status/{id}")
    public ItemEntity updateStatus(@PathVariable Long id,
                                  @RequestParam ItemStatus status) {
        return itemService.updateStatus(id, status);
    }

    // 🔥 CLAIM ITEM (ADMIN ONLY)
    @PutMapping("/claim/{id}")
    public ItemEntity claimItem(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam String role) {
        return itemService.claimItem(id, name, role);
    }

    // 🔥 DELETE ITEM (ADMIN ONLY)
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id,
                            @RequestParam String role) {
        itemService.deleteItem(id, role);
        return "Item deleted successfully";
    }
}