class Monster : Creature(), IHit{
    override fun attackModifier(creature: Creature) = this.attack - (creature as Player).protection + 1

    override fun getInfo() {
        println("\n---Монстр---")
        getCreatureInfo()
    }

    override fun hit(player: Creature, modifier: Int) {
        println("Удар наносит Монстр")
        hitCreature(creature = player, modifier = modifier)
    }
}