package com.gildedrose;

class AgedBrie extends AbstractItem {

    AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        decreaseSellIn();
        increaseQuality();
        if (wrappedItem.sellIn < 0) {
            increaseQuality();
        }
    }

    @Override
    public Item getItem() {
        return wrappedItem;
    }
}


