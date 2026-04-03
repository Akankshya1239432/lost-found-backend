package com.model.service;

import org.springframework.stereotype.Service;
import com.model.entity.ItemEntity;
import com.model.entity.ItemStatus;
import com.model.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 🔹 GET ALL ITEMS
    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    // 🔹 ADD ITEM
    public ItemEntity addItem(ItemEntity item) {
        item.setStatus(ItemStatus.OPEN);
        return itemRepository.save(item);
    }

    // 🔹 SEARCH BY NAME
    public List<ItemEntity> searchItems(String name) {
        return itemRepository.findByItemNameContainingIgnoreCase(name);
    }

    // 🔹 FILTER BY TYPE
    public List<ItemEntity> filterByType(String type) {
        return itemRepository.findByTypeIgnoreCase(type);
    }

    // 🔹 FILTER BY LOCATION
    public List<ItemEntity> filterByLocation(String location) {
        return itemRepository.findByLocationContainingIgnoreCase(location);
    }

    // 🔹 FILTER BY CATEGORY
    public List<ItemEntity> filterByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    // 🔹 UPDATE STATUS
    public ItemEntity updateStatus(Long id, ItemStatus status) {
        ItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setStatus(status);
        return itemRepository.save(item);
    }

    // 🔥 CLAIM ITEM (ADMIN ONLY)
    public ItemEntity claimItem(Long id, String name, String role) {

        // 🔐 SAFE ADMIN CHECK
        if (role == null || !"ADMIN".equalsIgnoreCase(role)) {
            throw new RuntimeException("Access denied: Only admin can claim");
        }

        ItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (item.getStatus() == ItemStatus.CLAIMED) {
            throw new RuntimeException("Item already claimed");
        }

        item.setClaimedBy(name);
        item.setStatus(ItemStatus.CLAIMED);

        // 🔥 LOST → FOUND
        if ("LOST".equalsIgnoreCase(item.getType())) {
            item.setType("FOUND");
        }

        return itemRepository.save(item);
    }

    // 🔥 DELETE ITEM (ADMIN ONLY)
    public void deleteItem(Long id, String role) {

        // 🔐 SAFE ADMIN CHECK
        if (role == null || !"ADMIN".equalsIgnoreCase(role)) {
            throw new RuntimeException("Access denied: Only admin can delete");
        }

        itemRepository.deleteById(id);
    }
}