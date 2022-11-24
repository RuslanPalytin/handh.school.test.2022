fun main(args: Array<String>) {

    val player = Player()
    val monster = Monster()

    player.getInfo()
    monster.getInfo()

    var round = 1
    println("\n=============Заруба=============")

    while(player.checkHeal && monster.checkHeal){
        println("\nРаунд: ${round++}")
        if(round % 2 == 0){
            hit(
                dealsCreature = player,
                takesCreature = monster
            )
            monster.getInfo()
        } else{
            hit(
                dealsCreature = monster,
                takesCreature = player
            )
            player.getInfo()
        }
    }

    println("\n=============GAME OVER=============")

    if(!player.checkHeal){
       println("Выиграло сущетсво - Игрок")
    } else {
        println("Выиграл существо - Монстр")
    }
}

fun hit(dealsCreature : Creature, takesCreature: Creature){
    if(dealsCreature is Player && takesCreature is Monster){
        val attackModifier =  dealsCreature.attackModifier(takesCreature)
        dealsCreature.hit(takesCreature, attackModifier)
    } else if(dealsCreature is Monster && takesCreature is Player){
        val attackModifier =  dealsCreature.attackModifier(takesCreature)
        dealsCreature.hit(takesCreature, attackModifier)
    }
}