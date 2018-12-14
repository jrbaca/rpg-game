package com.josephbaca.context

import com.josephbaca.entity.Entity
import com.josephbaca.entity.Humanoids
import com.josephbaca.item.Weapon
import com.josephbaca.item.Weapons
import com.josephbaca.parsing.NounToken
import com.josephbaca.parsing.VerbToken
import com.josephbaca.world.Biome
import com.josephbaca.world.Biome.BiomeType
import com.josephbaca.world.Mappable


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

    override val verbsToken = hashMapOf<VerbToken, (List<NounToken>) -> String?>(
        Pair(RoomVerbs.GO, { args -> go(args) }),
        Pair(RoomVerbs.WHERE, { args -> currentContext() }),
        Pair(RoomVerbs.FIGHT, { args -> fight() }),
        Pair(RoomVerbs.INVENTORY, { args -> getInventoryString() }),
        Pair(RoomVerbs.WHAT, { args -> getBiomeDescription() }),
        Pair(RoomVerbs.ENEMIES, { args -> getEnemyDescription() }),
        Pair(RoomVerbs.WHO, { args -> enemySet.toString() })
//        Pair("baby shark", { babyShark() })

        // Cheats
//        Pair("Remember, reality is an illusion, the universe is a hologram, buy gold,bye!", { lolhack() }),
//        Pair("4ce7fca0eee7bf957796eb64b684a5af", { asdf() }) // Yes or yes Korean lyric MD5
    )

    override val nounTokens: Set<NounToken> = setOf(
        RoomNouns.UP,
        RoomNouns.DOWN,
        RoomNouns.LEFT,
        RoomNouns.RIGHT
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
            "Oof looks like you're alone :(((."
        } else {
            listOf(
                "Bad Guy???????",
                "oh there are %s bad guy(s?).".format(enemySet.size),
                "There's %s bad men. Consider skrt skrting and or calling your mom :/.".format(enemySet.size),
                "Oh look a pal!!!!!!!!!! waiiitttt maybe not. Yeah no it's a bad man.",
                "It's an enemy.",
                "uh oh spaghettio issa bad bih!",
                "you're gonna have a bad time?",
                "Enemy Encounter!",
                "Jinkies!! it a bad man!!!!",
                "OH theres a dude :/."
            ).random()
        }
    }

    override fun toString(): String {
        return String.format("Room (%s)", icon)
    }

    override fun currentContext(): String {
        return "You're in a room."
    }

    private fun go(args: List<NounToken>): String? {
        val direction: NounToken? = args.singleOrNull()

        return when (direction) {
            RoomNouns.UP -> {
                contextManager.world.movePlayerUp()
                contextManager.currentContext.currentContext()
            }
            RoomNouns.DOWN -> {
                contextManager.world.movePlayerDown()
                contextManager.currentContext.currentContext()
            }
            RoomNouns.RIGHT -> {
                contextManager.world.movePlayerRight()
                contextManager.currentContext.currentContext()
            }
            RoomNouns.LEFT -> {
                contextManager.world.movePlayerLeft()
                contextManager.currentContext.currentContext()
            }
            else -> null
        }
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
        return "Oh you unlocked a sister secret."
    }

    private fun asdf(): String {
        contextManager.player.inventory.addItem(Weapon.buildWeapon(Weapons.TWICESWORD))
        return "Hey boy. Look I'm gonna make this real simple for you. You got two options, yes or yes."
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
