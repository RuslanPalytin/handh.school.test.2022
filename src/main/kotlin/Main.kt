import java.lang.Exception
import kotlin.concurrent.timerTask
import kotlin.random.Random

const val MIN_VALUE = 1
const val MAX_VALUE = 20

fun main(args: Array<String>) {

    println("\n=== Данные для Игрока ===")

    var health = setHealth()
    var damage = setDamage(health)

    val player = Player(
        attack = Random.nextInt(MIN_VALUE, MAX_VALUE),
        protection = Random.nextInt(MIN_VALUE, MAX_VALUE),
        health = health,
        damage = damage
    )

    println("\n=== Данные для Монстра ===")

    health = setHealth()
    damage = setDamage(health)

    val monster = Monster(
        attack = Random.nextInt(MIN_VALUE, MAX_VALUE),
        protection = Random.nextInt(MIN_VALUE, MAX_VALUE),
        health = health,
        damage = damage
    )

    player.getInfo()
    monster.getInfo()

    var round = 1

    println("========== START GAME ==========")

    while (player.checkHeal && monster.checkHeal){
        println("\t\tМенюшка: ")
        println("1 - Пусть они гасят друг друга")
        println("2 - Хочу узнать, как чувствует себя Игрок")
        println("3 - Хочу узнать, как чувствует себя Монстр")
        println("0 - Всё, не хочу больше играть\n")
        print("> ")

        when(readln()){
            "1" -> {
                hit(
                    round = round++,
                    dealsCreature = player,
                    takesCreature = monster
                )
                if(monster.checkHeal) {
                    hit(
                        round = round++,
                        dealsCreature = monster,
                        takesCreature = player
                    )
                }
            }
            "2" -> {
                player.getInfo()
            }
            "3" -> {
                monster.getInfo()
            }
            "0" -> break

            else -> {
                println("Такого варината в менюшке нету(((")
            }
        }
    }

    player.getInfo()
    monster.getInfo()

    if(whoWon(player, monster) is Player){
        println("Выиграло существо - Игрок")
    } else if (whoWon(player, monster) == null) {
        println("Ничего себе, у них победила дружба!")
    } else {
        println("Выиграло существо - Монстр")
    }

    println("\n=============GAME OVER=============")
}

fun hit(round: Int, dealsCreature : Creature, takesCreature: Creature): Boolean {

    var checkHealth = true
    println("\nРаунд $round")

    if (dealsCreature is Player && takesCreature is Monster) {

        val modifier = dealsCreature.attackModifier(creature = takesCreature)
        val checkThrowCube = dealsCreature.throwCube(modifier = modifier)
        dealsCreature.hit(monster = takesCreature, checkThrowCube = checkThrowCube)
        checkHealth = takesCreature.checkHeal

    } else if (dealsCreature is Monster && takesCreature is Player) {

        val modifier = dealsCreature.attackModifier(creature = takesCreature)
        val checkThrowCube = dealsCreature.throwCube(modifier = modifier)
        dealsCreature.hit(player = takesCreature, checkThrowCube = checkThrowCube)
        checkHealth = takesCreature.checkHeal
    }

    return checkHealth
}

fun whoWon(player: Player, monster: Monster): Creature?{

    return if(player.healCount < monster.healCount){
        player
    } else if(player.healCount == monster.healCount){
        if(player.health > monster.health){
            player
        } else if (player.health == monster.health) {
            null
        } else {
            monster
        }
    } else {
        monster
    }
}

fun setHealth(): Int{

    var health: Int

    while (true) {
        try {
            print("Введите значение здоровья для Существа: ")
            health = readln().toInt()
            if (health <= 0) {
                println("Здоровье со старта должно быть больше 0, иначе Существо будет без здоровья... Повторите ввод ;)")
                continue
            }
            break
        } catch (_: Exception) {
            println("Вы ввели буковки, а нужны циферки, повторите ввод ;)")
        }
    }
    return health
}

fun setDamage(health: Int): Int{

    var damage: Int

    while (true) {
        try {
            print("Введите начальное значение атаки для Существа, оно должно быть меньше $health: ")
            damage = readln().toInt()
            if (damage >= health) {
                println("Нет, нет! Начальное значение должно быть меньше $health, повтори ввод ;)")
                continue
            }
            break
        } catch (_: Exception) {
            println("Вы ввели буковки, а нужны циферки, повторите ввод ;)")
        }
    }
    return damage
}