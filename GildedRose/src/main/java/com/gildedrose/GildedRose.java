package com.gildedrose;

public class GildedRose {
    final AbstractItem[] items;

    public GildedRose(Item[] items) {
        this.items = new AbstractItem[items.length];
        for (int i = 0; i < items.length; i++) {
            this.items[i] = wrapItem(items[i]);
        }
    }

    public void updateQuality() {
        for (AbstractItem item : items){
            item.updateQuality();
        }
    }

    private static AbstractItem wrapItem(Item item) {
        AbstractItem wrappedItem;

        if (item.name.equals("Aged Brie")) {
            wrappedItem = new AgedBrie(item);
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            wrappedItem = new Sulfuras(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            wrappedItem = new BackstagePasses(item);
        } else {
            wrappedItem = new BaseItem(item);
        }

        if(item.name.startsWith("Conjured")){
            wrappedItem = new ConjuredItem(wrappedItem);
        }

        return wrappedItem;
    }

}
