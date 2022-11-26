class Monster(attack: Int, protection: Int, health: Int, damage: Int) : Creature(attack, protection, health, damage), IHit{

    override fun attackModifier(creature: Creature) = this.attack - (creature as Player).protection + 1

    override fun getInfo() {
        println("\n---Монстр---")
        getCreatureInfo()
    }

    override fun hit(player: Creature, checkThrowCube: Boolean) {
        println("Удар наносит Монстр")

        if(checkThrowCube){
            hitCreature(creature = player)
        } else {
            println("Удар не успешный!")
        }
    }
}