package com.gildedrose;

public abstract class AbstractItem {
    protected final Item wrappedItem;
    protected String name;
    protected int sellIn;
    protected int quality;

    public AbstractItem(Item item) {
        this.wrappedItem = item;
        this.name = item.name;
        this.sellIn = item.sellIn;
        this.quality = item.quality;
    }

    public abstract void updateQuality();

    protected void increaseQuality() {
        if (wrappedItem.quality < 50) {
            wrappedItem.quality++;
        }
    }

    protected void decreaseQuality() {
        if (wrappedItem.quality > 0) {
            wrappedItem.quality--;
        }
    }

    protected void decreaseSellIn() {
        wrappedItem.sellIn--;
    }

    protected Item getItem() {
        return wrappedItem;
    }

    protected void increaseSellIn() {
        wrappedItem.sellIn++;
    }
}
