class Player : Creature(), IHit {
    override fun attackModifier(creature: Creature) = this.attack - (creature as Monster).protection + 1

    override fun getInfo() {
        println("\n---Игрок---")
        getCreatureInfo()
    }

    override fun hit(monster: Creature, modifier: Int) {
        println("Удар наносит Игрок")
        hitCreature(creature = monster, modifier = modifier)
    }
}