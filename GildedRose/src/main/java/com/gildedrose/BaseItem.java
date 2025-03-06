package com.gildedrose;

class BaseItem extends AbstractItem {

    public BaseItem(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        decreaseSellIn();
        decreaseQuality();
        if (wrappedItem.sellIn < 0) {
            decreaseQuality();
        }
    }
}

