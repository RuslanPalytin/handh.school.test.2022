import kotlin.math.ceil
import kotlin.random.Random

abstract class Creature {

    var attack: Int
    var protection: Int
    var health: Int
        set(value) {
            field = if(value >= 0){
                value
            } else{
                0
            }
        }

    var damage: Int
    var N: Int
    var M: Int

    var healCount: Int = 0
    var checkHeal: Boolean = true

    constructor(attack: Int, protection: Int, health: Int, damage: Int) {
        this.attack = attack
        this.protection = protection
        this.health = health
        this.damage = damage
        this.N = health
        this.M = damage
    }

    companion object {
        private const val MIN_VALUE_CUBE = 1
        private const val MAX_VALUE_CUBE = 6
    }

    fun throwCube(modifier: Int): Boolean{

        var countThrowCube = 0

        do{
            val throwCube = Random.nextInt(MIN_VALUE_CUBE, MAX_VALUE_CUBE)
            if(throwCube >= 5){
                return true
            }
            countThrowCube++
        } while (modifier > countThrowCube)

        return false
    }

    protected fun getCreatureInfo(){
        println("Атака: ${this.attack}")
        println("Защита: ${this.protection}")
        println("Здоровье: ${this.health}")
        println("Урон: ${this.M} - ${this.N}")
        println("Количесвто использованных хилов: ${this.healCount} из 3\n")
    }

    protected fun hitCreature(creature: Creature){
        val hit = Random.nextInt(this.M, this.N + 1)
        creature.health -= hit
        println("Удар успешный, сущетсво нанесло $hit урона оппоненту")
        creature.checkHeal = checkHealth(creature)
    }

    private fun checkHealth(creature: Creature): Boolean{
        if(creature.health == 0) {
            if(creature.healCount >= 3){
                return false
            }
            print("У существа не осталось здоровья, захилить его? (Да / Нет)")
            when (readln()) {
                "Да" -> {
                    creature.healCount++
                    creature.health = (ceil(creature.N / 2.0)).toInt()
                }

                "Нет" -> {
                    return false
                }

                else -> {
                    println("Введено неккоректное значение, существо сдохло!")
                    return false
                }
            }
        }
        return true
    }
}