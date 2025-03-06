package com.gildedrose;

class Sulfuras extends AbstractItem {

    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
    }

    @Override
    public Item getItem() {
        return wrappedItem;
    }
}


