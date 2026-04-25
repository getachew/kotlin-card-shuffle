val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
val suits = listOf('♣', '♦', '♥', '♠')

fun newDeck() = ranks.map { rank -> suits.map { suit -> "$rank$suit" } }.flatten() as MutableList<String>

fun MutableList<String>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun cardValue(card: String): Int {
    val rank = card.dropLast(1)
    return ranks.indexOf(rank).let { if (it == 0) ranks.size else it }
}

class Dealer {
    private val deck = newDeck()
    private val iterator = deck.iterator()
    init { shuffle() }

    private fun shuffle() {
        ((deck.size - 1) downTo 1).forEach {
            val j = (0..it).random()
            deck.swap(it, j)
        }
    }

    fun dealOneCard() = iterator.next()
    fun hasNext() = iterator.hasNext()
}

class Game(private val dealer: Dealer) {
    fun play() {
        println("\nLet's play Flip!\n")
        while (dealer.hasNext()) {
            print("Press enter to be dealt a card: ")
            readLine()
            println("${dealer.dealOneCard()}\n")
        }
        println("=== Deck exhausted. Game over! ===")
    }
}

class War(private val dealer: Dealer) {
    fun play() {
        println("\nLet's play War!\n")

        val player = mutableListOf<String>()
        val computer = mutableListOf<String>()
        while (dealer.hasNext()) {
            player.add(dealer.dealOneCard())
            if (dealer.hasNext()) computer.add(dealer.dealOneCard())
        }

        var playerScore = 0
        var computerScore = 0

        while (player.isNotEmpty() && computer.isNotEmpty()) {
            print("Press enter to battle: ")
            readLine()

            val playerCard = player.removeFirst()
            val computerCard = computer.removeFirst()
            val result = cardValue(playerCard).compareTo(cardValue(computerCard))

            println("  You: $playerCard  vs  CPU: $computerCard")
            when {
                result > 0 -> { println("  ✅ You win this round!\n");  playerScore++ }
                result < 0 -> { println("  ❌ CPU wins this round!\n"); computerScore++ }
                else       -> { println("  🤝 Tie!\n") }
            }
        }

        println("=== Final Score ===")
        println("You: $playerScore  |  CPU: $computerScore")
        println(when {
            playerScore > computerScore -> "🏆 You win the war!"
            playerScore < computerScore -> "💀 CPU wins the war!"
            else                        -> "🤝 It's a tie!"
        })
    }
}

fun main() {
    println("=== Card Games ===")
    println("1) Flip — flip through the whole deck one card at a time")
    println("2) War  — battle the CPU, highest card wins each round")
    print("\nWhich game would you like to play? (1 or 2): ")

    val dealer = Dealer()
    when (readLine()?.trim()) {
        "1"  -> Game(dealer).play()
        "2"  -> War(dealer).play()
        else -> { println("Invalid choice — defaulting to Flip."); Game(dealer).play() }
    }
}
