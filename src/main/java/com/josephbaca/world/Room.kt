package com.josephbaca.world

import com.josephbaca.entity.LivingEntity
import com.josephbaca.entity.LivingEntityFactory
import com.josephbaca.util.Context
import com.josephbaca.util.ContextManager


/**
 * A room that a player can be in. May have any number of enemySet, items, curses, doors, etc.
 */
class Room @JvmOverloads internal constructor(
    private val contextManager: ContextManager,
    x: Int,
    y: Int,
    private val world: World,
    override val icon: String = "R" // Icon of the room as it appears on maps
) : Context, Mappable {

    // About the room
    private val grid: CoordinateGrid<Tile> = CoordinateGrid(x, y) // Map of the room
    private val biome: BiomeType = BiomeType.values().random() // Type of room
    private val description: String = Biome.getDescription(biome) // Random description from chosen biome
    private val enemySet: MutableSet<LivingEntity> = mutableSetOf()

    // Commands that can be executed
    private val commands = hashMapOf(
        Pair("where", this::whereAt),
        Pair("up", this::moveUp),
        Pair("down", this::moveDown),
        Pair("left", this::moveLeft),
        Pair("right", this::moveRight),
        Pair("fight", this::fight)
    )

    enum class BiomeType {
        FLOWERY,
        SUNNY,
        DESSERT,
        HALLOWEENTOWN,
        STSPATRICKSDAYTOWN,
        EASTERTOWN,
        PUROLAND
    }

    init {
        setEnemies()
    }

    private fun setEnemies() {
        enemySet.add(LivingEntityFactory.buildRandomDude())
    }

    override fun toString(): String {
        return String.format("Room (%s)", icon)
    }

    /**
     * Returns a string displaying the contents of the room.
     */
    fun toDisplayString(): String {
        return grid.toDisplayString()
    }

    override fun runInput(input: String): String {
        return commands[input]?.invoke() ?: "Unknown command"
    }

    override fun whereAt(): String {
        return "Room: " + this.description
    }

    private fun moveUp(): String {
        world.movePlayerUp()
        return contextManager.currentContext.whereAt()
    }

    private fun moveDown(): String {
        world.movePlayerDown()
        return contextManager.currentContext.whereAt()
    }

    private fun moveRight(): String {
        world.movePlayerRight()
        return contextManager.currentContext.whereAt()
    }

    private fun moveLeft(): String {
        world.movePlayerLeft()
        return contextManager.currentContext.whereAt()
    }

    private fun fight(): String {
        return if (enemySet.isEmpty()) {
            "No enemySet to fight!"
        } else {
            val battle = Battle(contextManager.player, enemySet)
            contextManager.addContextLayer(battle)
            battle.info()
        }
    }

    /**
     * Type of room which determines it's contents ?
     */
    private object Biome {

        internal val floweryDescriptions = listOf(
            "gg bois u got da worst description in the game. Theres a buncha dumbass dandelions up in dis club. " +
                    "Also hope you don't have allergies. I guess theres cool grass idk use your imagination bub.",
            "Wow!!!1!1!!! so pretty!!! These roses definitely won't kill you!!"
        )
        internal val sunnyDescriptions = listOf(
            "hot. humid. congratz ur sweating now :/",
            "You know when the weather is like 70 and the rain is barely misting down on you? That's this room its v comfy"
        )
        internal val dessertDescriptions = listOf(
            "Fruit salad, Yummy Yummy",
            "This room is shaped like a milkshake, and you have reason to believe that it brings all the boys to the yard."
        )
        internal val halloweentownDescriptions = listOf(
            "it looks spooky. Probably has a couple skeletons in it's closet"
        )
        internal val stPatricksDayTownDescriptions = listOf(
            "Green. Smells like beer and bad decisions"
        )
        internal val easterTownDescriptions = listOf(
            "Oh cool theres easter eggs!!! It smells like chocolate!"
        )
        internal val puroLandDescriptions = listOf(
            "It's so cute!!!!!!!!!! Literally dead!!! Theres Kuromi posters and my little twin star plushies!!!!! Actual best room"
        )

        internal fun getDescription(biome: BiomeType): String {
            return when (biome) {
                Room.BiomeType.FLOWERY -> floweryDescriptions.random()
                Room.BiomeType.SUNNY -> sunnyDescriptions.random()
                Room.BiomeType.DESSERT -> dessertDescriptions.random()
                Room.BiomeType.HALLOWEENTOWN -> halloweentownDescriptions.random()
                Room.BiomeType.STSPATRICKSDAYTOWN -> stPatricksDayTownDescriptions.random()
                Room.BiomeType.EASTERTOWN -> easterTownDescriptions.random()
                Room.BiomeType.PUROLAND -> puroLandDescriptions.random()
            }
        }
    }

    private class Battle(val player: LivingEntity, val enemySet: Set<LivingEntity>) : Context {

        init {
            LOG.info("Creating a fight")
            LOG.info("Player has %sHP".format(player.health))
            LOG.info("Enemies have HP: %s".format(enemySet.map { e -> "%s: %sHP".format(e.name, e.health) }))
        }

        private val commands = hashMapOf(
            Pair("where", this::whereAt),
            Pair("fight", this::fight)
        )

        override fun runInput(input: String): String {
            return commands[input]?.invoke() ?: "Unknown command"
        }

        override fun whereAt(): String {
            return "In a battle"
        }

        fun info(): String {
            return "%s (%s/%sHP) vs %s".format(
                player.name,
                player.health,
                player.maxHealth,
                enemySet.map { e -> e.name })
        }

        fun fight(): String {
            // Player attacks random enemy
            val targetEnemy = enemySet.random()
            targetEnemy.health = targetEnemy.health - player.attackDamage

            // Enemies all attack player
//            for (enemy in enemySet) {
            enemySet.forEach { enemy ->
                val damage = enemy.attackDamage
                LOG.info("%s doing %s damage to %s".format(enemy, damage, player))
                player.health = player.health - damage
            }
            LOG.info("Player has %sHP".format(player.health))
            LOG.info("Enemies have HP: %s".format(enemySet.map { e -> "%s: %sHP".format(e.name, e.health) }))

            return info()
        }
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(Room::class.java)
    }
}
