package com.josephbaca.context

import com.josephbaca.entity.*
import com.josephbaca.item.Weapon
import com.josephbaca.item.Weapons
import com.josephbaca.parsing.ContextCommand
import com.josephbaca.world.Mappable
import com.josephbaca.world.Biome
import com.josephbaca.world.Biome.BiomeType


/**
 * A room that a player can be in. May have any number of enemySet, items, curses, doors, etc.
 */
class Room @JvmOverloads internal constructor(
    private val contextManager: ContextManager,
    override val icon: String = "R"
) : Context, Mappable {

    private val biomeType: BiomeType = generateBiomeType()
    private val enemySet: MutableSet<Entity> = generateEnemySet()

    private val biomeDescription: String = generateBiomeDescription()
    private val enemyDescription: String = generateEnemyDescription()

    override val contextCommands: Map<ContextCommand, (List<ContextCommand>) -> String?> = hashMapOf(
        Pair(RoomCommands.GO, { args -> go(args) }),
        Pair(RoomCommands.WHERE, { args -> currentContext() }),
        Pair(RoomCommands.UP, { args -> moveUp() }),
        Pair(RoomCommands.DOWN, { args -> moveDown() }),
        Pair(RoomCommands.LEFT, { args -> moveLeft() }),
        Pair(RoomCommands.RIGHT, { args -> moveRight() }),
        Pair(RoomCommands.FIGHT, { args -> fight() }),
        Pair(RoomCommands.INVENTORY, { args -> getInventoryString() }),
        Pair(RoomCommands.WHAT, { args -> getBiomeDescription() }),
        Pair(RoomCommands.WHO, { args -> getEnemyDescription() })
//        Pair("baby shark", { babyShark() })

        // Cheats
//        Pair("Remember, reality is an illusion, the universe is a hologram, buy gold,bye!", { lolhack() }),
//        Pair("4ce7fca0eee7bf957796eb64b684a5af", { asdf() }) // Yes or yes Korean lyric MD5
    )

    private fun generateBiomeType(): BiomeType {
        return BiomeType.values().random()
    }

    private fun generateEnemySet(): MutableSet<Entity> {
        return mutableSetOf(Entity.buildFromExisting(Humanoids.values().random()))
    }

    private fun generateBiomeDescription(): String {
        return Biome.getDescription(biomeType)
    }

    private fun generateEnemyDescription(): String {
        return if (enemySet.isEmpty()) {
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
    }

    override fun toString(): String {
        return String.format("Room (%s)", icon)
    }

    override fun currentContext(): String {
        return "in a room"
    }

    private fun go(args: List<ContextCommand>): String? {
        // Should have one arg
        val direction: ContextCommand? = args.singleOrNull()

        return when (direction) {
            RoomCommands.UP -> {
                contextManager.world.movePlayerUp()
                contextManager.currentContext.currentContext()
            }
            RoomCommands.DOWN -> {
                contextManager.world.movePlayerDown()
                contextManager.currentContext.currentContext()
            }
            RoomCommands.RIGHT -> {
                contextManager.world.movePlayerRight()
                contextManager.currentContext.currentContext()
            }
            RoomCommands.LEFT -> {
                contextManager.world.movePlayerLeft()
                contextManager.currentContext.currentContext()
            }
            else -> null
        }
    }

    @Deprecated("go")
    private fun moveUp(): String {
        contextManager.world.movePlayerUp()
        return contextManager.currentContext.currentContext()
    }

    @Deprecated("go")
    private fun moveDown(): String {
        contextManager.world.movePlayerDown()
        return contextManager.currentContext.currentContext()
    }

    @Deprecated("go")
    private fun moveRight(): String {
        contextManager.world.movePlayerRight()
        return contextManager.currentContext.currentContext()
    }

    @Deprecated("go")
    private fun moveLeft(): String {
        contextManager.world.movePlayerLeft()
        return contextManager.currentContext.currentContext()
    }

    private fun fight(): String {
        return if (enemySet.isEmpty()) {
            "No enemies to fight!"
        } else {
            val battle = Battle(contextManager.player, enemySet, contextManager)
            contextManager.addContextLayer(battle)
            battle.info()
        }
    }

    private fun getInventoryString(): String {
        return contextManager.player.inventory.toString()
    }

    private fun getEnemyDescription() = enemyDescription

    private fun getBiomeDescription() = biomeDescription

    private fun lolhack(): String {
        contextManager.player.inventory.addItem(Weapon.buildWeapon(Weapons.DEMONGALAXYMASTERSWORD))
        return "Oh shit you unlocked a sister secret"
    }

    private fun asdf(): String {
        contextManager.player.inventory.addItem(Weapon.buildWeapon(Weapons.TWICESWORD))
        return "Oh shit you unlocked a sister secret"
    }

    private fun babyShark(): String {
        return "doo doo doo doo doo doo\n" +
                "Baby shark, doo doo doo doo doo doo\n" +
                "Baby shark, doo doo doo doo doo doo\n" +
                "Baby shark!\n" +
                "Mommy shark, doo doo doo doo doo doo\n" +
                "Mommy shark, doo doo doo doo doo doo\n" +
                "Mommy shark, doo doo doo doo doo doo\n" +
                "Mommy shark!\n" +
                "Daddy shark, doo doo doo doo doo doo\n" +
                "Daddy shark, doo doo doo doo doo doo\n" +
                "Daddy shark, doo doo doo doo doo doo\n" +
                "Daddy shark!\n" +
                "Grandma shark, doo doo doo doo doo doo\n" +
                "Grandma shark, doo doo doo doo doo doo\n" +
                "Grandma shark, doo doo doo doo doo doo\n" +
                "Grandma shark!\n" +
                "Grandpa shark, doo doo doo doo doo doo\n" +
                "Grandpa shark, doo doo doo doo doo doo\n" +
                "Grandpa shark, doo doo doo doo doo doo\n" +
                "Grandpa shark!\n" +
                "Let’s go hunt, doo doo doo doo doo doo\n" +
                "Let’s go hunt, doo doo doo doo doo doo\n" +
                "Let’s go hunt, doo doo doo doo doo doo\n" +
                "Let’s go hunt!\n" +
                "Run away,…"
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)
    }
}
