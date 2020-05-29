// a Rank of a card is modeled as a string eg: "A" (Ace), "10" (10), "J" (Jack)
val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

// Suits of a card are modeled as a list of card suit characters
val suits = listOf('♣', '♦', '♥', '♠')

// creates a new deck mapping each rank to the list of suits and
// then flattening the list of [[rank suits],[rank suits]...] into one long list
// Note that this function is like opening pack of a brand new deck of cards
// and cards are sorted by rank suit - shuffle before playing
fun newDeck() = ranks.map { rank -> suits.map { suit -> "$rank$suit"}}.flatten() as MutableList

// an extension function
fun MutableList<String>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

// Dealer has a deck of cards, knows how to shuffle and deal a single card
class Dealer {
    private val deck = newDeck()
    private val iterator = deck.iterator()
    init {
        shuffle()
    }

    // fischer-yates shuffle algorithm implemented from wikipedia
    private fun shuffle(){
        ((deck.size-1) downTo 1).forEach {
            val j = (0..it).random()
            deck.swap(it, j)
        }
    }

    fun dealOneCard() = iterator.next()
    fun hasNext() = iterator.hasNext()
}

// A game takes a dealer and implements a loops in turns its the play function
class Game(private val dealer: Dealer) {
    fun play() {
        println("\nLet's play...\n")

        turnLoop@ while (dealer.hasNext()) {
            print("press enter to be dealt a card:")
            readLine()
            print("${dealer.dealOneCard()}\n\n")
        }
    }
}

// main gets a dealer starts a game
fun main() {
    val dealer = Dealer()
    val game = Game(dealer)

    game.play()
}