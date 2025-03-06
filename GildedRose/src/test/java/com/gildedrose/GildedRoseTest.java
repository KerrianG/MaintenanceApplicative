package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }


    @Test
    void testRegularItemQualityDecreases() {
        Item[] items = { new Item("Regular Item", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);
    }

    @Test
    void testQualityDegradesTwiceAfterSellInPassed() {
        Item[] items = { new Item("Regular Item", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    void testQualityNeverNegative() {
        Item[] items = { new Item("Regular Item", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void testAgedBrieIncreasesInQuality() {
        Item[] items = { new Item("Aged Brie", 10, 25) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(26, items[0].quality);
    }

    @Test
    void testAgedBrieQualityNeverAbove50() {
        Item[] items = { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    void testSulfurasNeverDecreases() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    void testBackstagePassesIncreaseInQuality() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, items[0].sellIn);
        assertEquals(21, items[0].quality);
    }

    @Test
    void testBackstagePassesIncreaseBy2When10DaysOrLess() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    void testBackstagePassesIncreaseBy3When5DaysOrLess() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality);
    }

    @Test
    void testBackstagePassesDropToZeroAfterConcert() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void testConjuredItemsDegradeTwiceAsFast() {
        Item[] items = { new Item("Conjured Mana Cake", 3, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, items[0].sellIn);
        assertEquals(4, items[0].quality);
    }

    @Test
    void testConjuredItemsDegradeFourTimesAfterSellIn() {
        Item[] items = { new Item("Conjured Mana Cake", 0, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(2, items[0].quality);
    }

    @Test
    void testConjuredAgedBrieIncreasesInQuality() {
        Item[] items = { new Item("Conjured Aged Brie", 10, 25) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(27, items[0].quality);
    }

    @Test
    void testConjuredAgedBrieQualityNeverAbove50() {
        Item[] items = { new Item("Conjured Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    void testConjuredSulfurasNeverDecreases() {
        Item[] items = { new Item("Conjured Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    void testConjuredBackstagePassesIncreaseBy4When10DaysOrLess() {
        Item[] items = { new Item("Conjured Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(24, items[0].quality);
    }

    @Test
    void testConjuredBackstagePassesIncreaseBy6When5DaysOrLess() {
        Item[] items = { new Item("Conjured Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(26, items[0].quality);
    }

    @Test
    void testConjuredBackstagePassesDropToZeroAfterConcert() {
        Item[] items = { new Item("Conjured Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }
}
