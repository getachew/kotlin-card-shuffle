val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
val suits = listOf('♣', '♦', '♥', '♠')

fun newDeck(): MutableList<String> =
    ranks.flatMap { rank -> suits.map { suit -> "$rank$suit" } }.toMutableList()

fun MutableList<String>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun cardValue(card: String): Int {
    val rank = card.dropLast(1)
    val index = ranks.indexOf(rank)
    return if (index == 0) ranks.size else index
}

class Dealer {
    private val deck = newDeck()
    private val iterator: Iterator<String>

    init {
        shuffle()
        iterator = deck.iterator()
    }

    private fun shuffle() {
        for (i in deck.size - 1 downTo 1) {
            val j = (0..i).random()
            deck.swap(i, j)
        }
    }

    fun dealOneCard(): String = iterator.next()
    fun hasNext(): Boolean = iterator.hasNext()
}

interface CardGame {
    val key: String
    val name: String
    val description: String
    fun play()
}

class FlipGame(private val dealer: Dealer) : CardGame {
    override val key = "1"
    override val name = "Flip"
    override val description = "flip through the whole deck one card at a time"

    override fun play() {
        println("\nLet's play $name!\n")
        while (dealer.hasNext()) {
            print("Press enter to be dealt a card: ")
            readLine()
            println("${dealer.dealOneCard()}\n")
        }
        println("=== Deck exhausted. Game over! ===")
    }
}

class WarGame(private val dealer: Dealer) : CardGame {
    override val key = "2"
    override val name = "War"
    override val description = "battle the CPU, highest card wins each round"

    override fun play() {
        println("\nLet's play $name!\n")

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
                result > 0 -> {
                    println("  ✅ You win this round!\n")
                    playerScore++
                }
                result < 0 -> {
                    println("  ❌ CPU wins this round!\n")
                    computerScore++
                }
                else -> println("  🤝 Tie!\n")
            }
        }

        println("=== Final Score ===")
        println("You: $playerScore  |  CPU: $computerScore")
        println(
            when {
                playerScore > computerScore -> "🏆 You win the war!"
                playerScore < computerScore -> "💀 CPU wins the war!"
                else -> "🤝 It's a tie!"
            }
        )
    }
}

class HigherLowerGame(private val dealer: Dealer) : CardGame {
    override val key = "3"
    override val name = "Higher or Lower"
    override val description = "guess whether the next card will be higher or lower"

    override fun play() {
        println("\nLet's play $name!\n")

        if (!dealer.hasNext()) {
            println("Deck exhausted. Game over!")
            return
        }

        var currentCard = dealer.dealOneCard()
        var score = 0

        println("Starting card: $currentCard\n")

        while (dealer.hasNext()) {
            print("Will the next card be (h)igher or (l)ower? ")
            val guess = readLine()?.trim()?.lowercase()

            if (guess != "h" && guess != "l") {
                println("Invalid choice. Please enter 'h' or 'l'.\n")
                continue
            }

            val nextCard = dealer.dealOneCard()
            val currentValue = cardValue(currentCard)
            val nextValue = cardValue(nextCard)

            println("Current: $currentCard  ->  Next: $nextCard")

            when {
                nextValue == currentValue -> {
                    println("🤝 Tie rank — no point awarded.\n")
                }
                guess == "h" && nextValue > currentValue -> {
                    println("✅ Correct!\n")
                    score++
                }
                guess == "l" && nextValue < currentValue -> {
                    println("✅ Correct!\n")
                    score++
                }
                else -> {
                    println("❌ Wrong guess.\n")
                }
            }

            currentCard = nextCard
        }

        println("=== Final Score ===")
        println("You scored $score correct guess(es).")
    }
}

fun availableGames(dealer: Dealer): List<CardGame> = listOf(
    FlipGame(dealer),
    WarGame(dealer),
    HigherLowerGame(dealer)
)

fun main() {
    println("=== Card Games ===")

    val dealer = Dealer()
    val games = availableGames(dealer)

    games.forEach { game ->
        println("${game.key}) ${game.name.padEnd(16)} — ${game.description}")
    }

    print("\nWhich game would you like to play? (${games.joinToString(" or ") { it.key }}): ")
    val choice = readLine()?.trim()

    val selectedGame = games.find { it.key == choice } ?: games.first().also {
        println("Invalid choice — defaulting to ${it.name}.")
    }

    selectedGame.play()
}
