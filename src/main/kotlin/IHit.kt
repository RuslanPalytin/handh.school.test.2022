interface IHit {
    fun attackModifier(creature: Creature): Int

    fun getInfo()

    fun hit(creature: Creature, modifier: Int)
}