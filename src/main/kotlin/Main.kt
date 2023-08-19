package indigo

fun main() {
    startGame()
}

fun startGame() {
    printWelcome()
    val playerPlayingFirst = playerFirst()

    val deck: Deck = Deck()
    val table: Table = Table(deck)
    val player: Player = Player(deck)
    val computer: Player = Player(deck)

    var gameRunning = true
    while(gameRunning) {
        println("Choose an action (reset, shuffle, get, exit):")
        val option = readln()
        when(option) {
            "reset" -> deck.resetDeck()
            "shuffle" -> deck.shuffleDeck()
            "get" -> deck.get(6)
            "exit" -> {
                gameRunning = false
                println("Bye")
            }
            else -> println("Wrong action.")
        }
    }
}

fun playerFirst(): Boolean {
    while(true) {
        println("Play first?")
        val input = readln()
        if(input == "yes") {
            return true
        } else if (input == "no") {
            return false
        } else {
            continue
        }
    }
}

fun printWelcome() {
    println("Indigo Card Game")
}

