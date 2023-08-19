package indigo

class Game() {

    lateinit var deck: Deck
    lateinit var table: Table
    lateinit var player: Player
    lateinit var computer: Computer

    fun startGame() {
        printWelcome()
        var playerPlayingFirst = false
        var gameRunning = true
        while(true) {
            println("Play first?")
            val input = readln()
            if(input == "yes") {
                playerPlayingFirst = true
                break
            } else if (input == "no") {
                playerPlayingFirst =  false
                break
            } else if (input == "exit"){
                gameRunning = false
                playerPlayingFirst = false
                println("Game Over")
                break
            } else {
                continue
            }
        }

        deck = Deck()
        table = Table(deck)
        player = Player(deck, table, this)
        computer = Computer(deck, table, this)

        if(gameRunning){

            if (playerPlayingFirst) {
                player.getCardsInHand()
                if (!player.play()) {
                    gameRunning = false
                    println("Game Over")
                }
            }

            while (gameRunning) {
                if (deck.deck.isEmpty() && computer.hand.size == 0 && player.hand.size == 0) {
                    println("Game Over")
                    break
                }
                computer.play()
                if (deck.deck.isEmpty() && computer.hand.size == 0 && player.hand.size == 0) {
                    println("Game Over")
                    break
                }
                player.getCardsInHand()
                if (!player.play()) {
                    println("Game Over")
                    break
                }
                if (deck.deck.isEmpty() && computer.hand.size == 0 && player.hand.size == 0) {
                    println("Game Over")
                    break
                }
                println(deck.deck.size)
            }
            printScore()
        }
    }

    fun printWelcome() {
        println("Indigo Card Game")
    }

    fun printScore() {
        val scoreString = "Score: Player ${player.scoreCount} - Computer ${computer.scoreCount}"
        val cardScoreString = "Cards: Player ${player.cardCount} - Computer ${computer.cardCount}"

        println(scoreString)
        println(cardScoreString)
    }


}
