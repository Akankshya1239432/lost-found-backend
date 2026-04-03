package com.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.entity.ItemEntity;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    // 🔥 FIXED
    List<ItemEntity> findByTypeIgnoreCase(String type);

    List<ItemEntity> findByItemNameContainingIgnoreCase(String name);

    List<ItemEntity> findByLocationContainingIgnoreCase(String location);

    List<ItemEntity> findByCategory(String category);
}