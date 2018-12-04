package com.josephbaca.world

import com.josephbaca.util.Context
import com.josephbaca.util.ContextManager

import java.util.Random

/**
 * A room that a player can be in. May have any number of enemies, items, curses, doors, etc.
 */
class Room @JvmOverloads internal constructor(
    private val contextManager: ContextManager,
    x: Int,
    y: Int,
    private val world: World,
    private val icon: String = "R" // Icon of the room as it appears on maps
) : Context, Mappable {
    private val description: String
    private val grid: CoordinateGrid<Room> // Map of the room
    private val biome: BiomeType // Type of room

    private val commands = hashMapOf(
        Pair("where", this::whereAt),
        Pair("up", this::moveUp),
        Pair("down", this::moveDown),
        Pair("left", this::moveLeft),
        Pair("right", this::moveRight)
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
        val r = Random()
        this.grid = CoordinateGrid(x, y)
        this.biome = BiomeType.values()[r.nextInt(BiomeType.values().size)]
        this.description = Biome.getDescription(this.biome)
    }

    override fun toString(): String {
        return String.format("Room (%s)", icon)
    }

    override fun getIcon(): String {
        return icon
    }

    /**
     * Returns a string displaying the contents of the room.
     */
    fun toDisplayString(): String {
        throw RuntimeException("Not yet implemented.")
    }

    override fun runInput(input: String): String {
        return commands[input]?.invoke() ?: "Unknown command"
    }

    override fun whereAt(): String {
        return "Room:" + this.description
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

    /**
     * Type of room which determines it's contents ?
     */
    private object Biome {

        internal val floweryDescriptions = listOf(
            "gg bois u got da worst description in the game. Theres a" +
                    "buncha dumbass dandelions up in dis club. Also hope you don't have allergies. I guess theres cool grass idk" +
                    "use your imagination bub.", "Wow!!!1!1!!! so pretty!!! These roses definitely won't kill you!!"
        )
        internal val sunnyDescriptions = listOf(
            "hot. humid. congratz ur sweating now :/",
            ("You know when the" + "weather is like 70 and the rain is barely misting down on you? That's this room its v comfy")
        )
        internal val dessertDescriptions = listOf(
            "Fruit salad, Yummy Yummy",
            ("This room is shaped like a" + "milkshake, and you have reason to believe that it brings all the boys to the yard.")
        )
        internal val halloweentownDescriptions =
            listOf(("it looks spooky. Probably has a couple skeletons" + "in it's closet"))
        internal val stPatricksDayTownDescriptions = listOf("Green. Smells like beer and bad decisions")
        internal val easterTownDescriptions = listOf("Oh cool theres easter eggs!!! It smells like chocolate!")
        internal val puroLandDescriptions =
            listOf(("It's so cute!!!!!!!!!! Literally dead!!! Theres Kuromi" + "posters and my little twin star plushies!!!!! Actual best room"))

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

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(Room::class.java)
    }
}
