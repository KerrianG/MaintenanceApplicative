package com.gildedrose;

class BackstagePasses extends AbstractItem{

    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        decreaseSellIn();
        increaseQuality();
        if (wrappedItem.sellIn <= 10) {
            increaseQuality();
        }
        if (wrappedItem.sellIn <= 5) {
            increaseQuality();
        }
        if (wrappedItem.sellIn < 0) {
            wrappedItem.quality = 0;
        }
    }
}

