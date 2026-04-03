package com.model.dto;

public class ItemStatsDTO {

    private long totalItems;
    private long lostItems;
    private long foundItems;
    private long resolvedItems;

    public ItemStatsDTO(long totalItems, long lostItems,
                        long foundItems, long resolvedItems) {
        this.totalItems = totalItems;
        this.lostItems = lostItems;
        this.foundItems = foundItems;
        this.resolvedItems = resolvedItems;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public long getLostItems() {
        return lostItems;
    }

    public long getFoundItems() {
        return foundItems;
    }

    public long getResolvedItems() {
        return resolvedItems;
    }
}

