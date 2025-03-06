package com.gildedrose;

public class ConjuredItem extends AbstractItem{
    private final AbstractItem item;

    public ConjuredItem(AbstractItem item) {
        super(item.wrappedItem);
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if(!(item.name.equals("Conjured Sulfuras, Hand of Ragnaros"))) {;
            item.updateQuality();
            item.updateQuality();
            item.increaseSellIn();
        }
    }



}
