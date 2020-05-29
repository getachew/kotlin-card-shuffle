import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

fun testNewDeck() {
    val deck = newDeck()
    assertEquals(52, deck.size)
}

fun testShuffle() {
    val d1 = Dealer()
    val d2 = Dealer()
    assertNotEquals(d1, d2)
}

fun test52Deals() {
    val d1 = Dealer()
    repeat(52) {
        d1.dealOneCard()
    }
}

fun test53Deals() {
    val d1 = Dealer()
    repeat(52) {
        d1.dealOneCard()
    }

    try {
        d1.dealOneCard()
    }catch (e: NoSuchElementException) {
    }
}

fun main() {
    testNewDeck()
    testShuffle()
    test52Deals()
    test53Deals()   // exception is caught to pass test
}