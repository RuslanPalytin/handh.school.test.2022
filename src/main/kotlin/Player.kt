class Player(attack: Int, protection: Int, health: Int, damage: Int) : Creature(attack, protection, health, damage), IHit {

    override fun attackModifier(creature: Creature) = this.attack - (creature as Monster).protection + 1

    override fun getInfo() {
        println("\n---Игрок---")
        getCreatureInfo()
    }

    override fun hit(monster: Creature, checkThrowCube: Boolean) {
        println("Удар наносит Игрок")

        if(checkThrowCube){
            hitCreature(creature = monster)
        } else {
            println("Удар не успешный!")
        }
    }

}