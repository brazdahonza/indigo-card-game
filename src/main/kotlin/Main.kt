fun main(args: Array<String>) {
    startGame()
}

fun startGame() {
    val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    val suits = listOf("♦", "♥", "♠", "♣")
    val cards = mutableListOf<String>()
    var gameRunning: Boolean = true

   while(gameRunning) {
       println("Choose an action (reset, shuffle, get, exit):")
       val option = readln()
       when(option) {
           "reset" -> resetDeck()
           "shuffle" -> shuffleDeck()
           "get" -> get()
           "exit" -> {
               gameRunning = false
               println("Bye")
           }
       }
   }
}

fun get() {
    TODO("Not yet implemented")
}

fun shuffleDeck() {
    TODO("Not yet implemented")
}

fun resetDeck() {
    TODO("Not yet implemented")
}
