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
    private val description: String // Room description
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
        PUROLAND,
        SHAMELESSOTHERREFRENCES
    }

    init {
        setEnemies()
        description = setDescription()
    }

    private fun setEnemies() {
        enemySet.add(LivingEntityFactory.buildRandomDude())
    }

    private fun setDescription(): String {
        val enemyInfo = if (enemySet.isEmpty()) {
            "Oof looks like you're alone :((("
        } else {
            listOf(
                "Bad Guy???????",
                "oh shit theres %s bad guy(s?).".format(enemySet.size),
                "Theres %s bad men. Consider skrt skrting and or calling your mom :/".format(enemySet.size),
                "Oh look a pal!!!!!!!!!! waiiitttt maybe not.",
                "its an enemy.",
                "uh oh spaghettio issa bad bih!",
                "you're gonna have a bad time?",
                "Enemy Encounter!",
                "Jinkies!! it a bad man!!!!",
                "OH theres a dude :/"
            ).random()
        }

        val biomeInfo = Biome.getDescription(biome)
        return "%s %s".format(enemyInfo, biomeInfo)
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
        return this.description
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

        internal fun getDescription(biome: BiomeType): String {
            return when (biome) {
                Room.BiomeType.FLOWERY -> listOf(
                    "gg bois u got da worst description in the game. Theres a buncha dumbass dandelions up in dis club. " +
                            "Also hope you don't have allergies. I guess theres cool grass idk use your imagination bub.",
                    "Wow!!!1!1!!! so pretty!!! Those bad gu definitely won't kill you!!",
                    "Wow look at all that natural lighting!!! It's so pleasant! you can feel the sun on your skin",
                    "The world is a beautiful place and you're no longer afraid to die."
                ).random()
                Room.BiomeType.SUNNY -> listOf(
                    "hot. humid. congratz ur sweating now :/",
                    "You know when the weather is like 70 and the rain is barely misting down on you? That's this room its v comfy"
                ).random()
                Room.BiomeType.DESSERT -> listOf(
                    "Fruit salad, Yummy Yummy",
                    "This room is shaped like a milkshake, and you have reason to believe that it brings all the boys to the yard.",
                    "You know that one part in shark boy and lava girl with the sea of milk and cookies? This that",
                    "The walls are made of jello",
                    "The floor is lava!!!!! oh wait that's chocolate syrup.",
                    "You can barely walk around! There's little popcorn kettles everywhere",
                    "It looks like Willy Wonka threw up in here."
                ).random()
                Room.BiomeType.HALLOWEENTOWN -> listOf(
                    "it looks spooky. Probably has a couple skeletons in it's closet",
                    "This is halloween! This is Halloween(town(room))",
                    "Big Bone Energy."
                ).random()
                Room.BiomeType.STSPATRICKSDAYTOWN -> listOf(
                    "Green. Smells like beer and bad decisions"
                ).random()
                Room.BiomeType.EASTERTOWN -> listOf(
                    "Oh cool theres easter eggs!!! It smells like chocolate!",
                    "Theres a giant rabbit portrait. Weird flex, but ok."
                ).random()
                Room.BiomeType.PUROLAND -> listOf(
                    "It's so cute!!!!!!!!!! Literally dead!!! Theres Kuromi posters and my little twin star plushies!!!!! Actual best room",
                    "it's pink i guess? It smells like marshmallows",
                    "It's a cute cafe! but it doesn't really look like anyones working here"
                ).random()
                Room.BiomeType.SHAMELESSOTHERREFRENCES -> listOf(
                    "You hear a weirdly high pitched song. You're very uncomfy",
                    "Oh sick it's a purple room! You feel a weird presence tho",
                    "oh this room is filled with weird yellow rat plushies. You kinda love them",
                    "The walls are made of rocks! Theres a poster with a dude you would 100% smash.",
                    "Oh on one wall theres a line, and then on the other theres the same line and a shorter one" +
                            "Then theres two lines of the same height. Then on the last wall theres the same line and" +
                            "another lying on it's side.",
                    "Its a small room(after all), its a small room(after all) ITS A SMALL ROOM (AFTER ALL), issa" +
                            "small room(after all)"

                ).random()
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
