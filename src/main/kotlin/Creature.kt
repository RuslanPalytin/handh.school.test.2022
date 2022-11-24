import kotlin.random.Random

open class Creature {

    constructor() {
        attack = try {
            print("Введите значение атаки: ")
            readln().toInt()
        } catch (e: Exception) {
            showError(e.message)
            MIN_VALUE
        }

        protection = try {
            print("Введите значение защиты: ")
            readln().toInt()
        } catch (e: Exception) {
            showError(e.message)
            MIN_VALUE
        }

        N = try {
            print("Введите значение здоровья: ")
            readln().toInt()
        } catch (e: Exception) {
            showError(e.message)
            MIN_VALUE_HEALTH
        }

        M = try {
            print("Введите начальное значение урона: ")
            readln().toInt()
        } catch (e: Exception) {
            showError(e.message)
            MIN_VALUE_HEALTH
        }
    }

    companion object {
        private const val MIN_VALUE = 1
        private const val MAX_VALUE = 20
        private const val MIN_VALUE_HEALTH = 1
        private const val MIN_VALUE_DAMAGE = MIN_VALUE_HEALTH - 1
        private const val MIN_VALUE_CUBE = 1
        private const val MAX_VALUE_CUBE = 6
    }

    var attack: Int = MIN_VALUE
        set(value) {
            if (value in MIN_VALUE..MAX_VALUE) field = value
            else {
                try {
                    println("Введено/задано неккоректное значение атаки, повторите ввод!")
                    print("Атака: ")
                    field = readln().toInt()
                    attack = field
                } catch (e: Exception) {
                    showError(e.message)
                    field = MIN_VALUE
                }
            }
        }

    var protection: Int = MIN_VALUE
        set(value) {
            if (value in MIN_VALUE..MAX_VALUE) field = value
            else {
                try {
                    println("Введено/задано неккоректное значение защиты, повторите ввод!")
                    print("Защита: ")
                    field = readln().toInt()
                    protection = field
                } catch (e: Exception) {
                    showError(e.message)
                    field = MIN_VALUE
                }
            }
        }

    var health: Int = MIN_VALUE_HEALTH

    var damage: Int = MIN_VALUE_DAMAGE

    var N: Int = MIN_VALUE_HEALTH
        set(value) {
            if (value >= MIN_VALUE_HEALTH) {
                field = value
                health = field
            }
            else {
                try {
                    println("Введено/задано неккоректное значение здоровья, повторите ввод!")
                    print("Здоровье: ")
                    field = readln().toInt()
                    N = field
                } catch (e: Exception) {
                    showError(e.message)
                    field = MIN_VALUE_HEALTH
                    health = field
                }
            }
        }

    var M: Int = MIN_VALUE_DAMAGE
        set(value) {
            if (value < N) {
                field = value
                damage = field
            }
            else {
                try {
                    println("Введено/задано неккоректное значение урона, повторите ввод!")
                    print("Урон: ")
                    field = readln().toInt()
                    M = field
                } catch (e: Exception) {
                    showError(e.message)
                    field = MIN_VALUE_DAMAGE
                    damage = field
                }
            }
        }

    var heal: Int = 0

    var checkHeal: Boolean = true

    protected fun getCreatureInfo(){
        println("Атака: ${this.attack}")
        println("Защита: ${this.protection}")
        println("Здоровье: ${this.health}")
        println("Урон: ${this.M} - ${this.N}")
        println("Количесвто использованных хилов: ${this.heal} из 3\n")
    }

    protected fun hitCreature(creature: Creature, modifier: Int){
        var i = 0
        do {
            val castCube = Random.nextInt(MIN_VALUE_CUBE, MAX_VALUE_CUBE)
            if (castCube >= 5) {
                val hit = Random.nextInt(this.M, this.N)
                creature.health -= hit
                println("Удар успешный!")
                checkHeal = checkHealth(creature)
                break
            }
            i++
        } while (i < modifier)

        if((i == modifier || i == 1) && checkHeal){
            println("Удар не успешный!")
        }
    }

    private fun checkHealth(creature: Creature): Boolean{
        if(creature.health < 0) {
            if(creature.heal >= 3){
                //checkHeal = false
                return false
            }
            print("У существа не осталось здоровья, захилить его? (Да / Нет)")
            when (readln()) {
                "Да" -> {
                    creature.heal++
                    creature.health = creature.N / 2
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