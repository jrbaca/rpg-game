package com.josephbaca.context

import com.josephbaca.entity.Entity
import com.josephbaca.entity.Humanoids
import com.josephbaca.item.Weapon
import com.josephbaca.item.Weapons
import com.josephbaca.parsing.NounToken
import com.josephbaca.parsing.VerbToken
import com.josephbaca.rpggame.GameStateManager
import com.josephbaca.world.Biome
import com.josephbaca.world.Biome.BiomeType
import com.josephbaca.world.Mappable


/**
 * A room that a player can be in. May have any number of enemySet, items, curses, doors, etc.
 */
class Room @JvmOverloads internal constructor(
    override val gameStateManager: GameStateManager,
    override val icon: String = "R"
) : Context, Mappable {

    private val biomeType: BiomeType = generateBiomeType()
    private val enemySet: MutableSet<Entity> = generateEnemySet()

    private val biomeDescription: String = generateBiomeDescription()
    private val enemyDescription: String = generateEnemyDescription()

    override val localVerbTokens = hashMapOf<VerbToken, (List<NounToken>) -> String?>(
        Pair(RoomVerbs.GO, { args -> go(args) }),
        Pair(RoomVerbs.FIGHT, { args -> fight() }),
        Pair(RoomVerbs.INVENTORY, { args -> getInventoryString() }),
        Pair(RoomVerbs.WHAT, { args -> getBiomeDescription() }),
        Pair(RoomVerbs.ENEMIES, { args -> getEnemyDescription() }),
        Pair(RoomVerbs.WHO, { args -> enemySet.toString() })

        // Cheats
//        Pair("Remember, reality is an illusion, the universe is a hologram, buy gold,bye!", { lolhack() }),
//        Pair("4ce7fca0eee7bf957796eb64b684a5af", { asdf() }) // Yes or yes Korean lyric MD5
    )

    override val localNounTokens: Set<NounToken> = setOf(
        RoomNouns.FORWARD,
        RoomNouns.BACK,
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

    private fun go(args: List<NounToken>): String? {

        // Checks that only one arg was passed, casts it, and moves the player
        (args.singleOrNull() as? RoomNouns).also {
            return if (it != null) gameStateManager.world.movePlayer(it) else null
        }
    }

    private fun fight(): String {
        return if (enemySet.isEmpty()) {
            "No enemies to fight!"
        } else {
            val battle = Battle(gameStateManager.player, enemySet, gameStateManager)
            gameStateManager.addContextLayer(battle)
            battle.makeBattleIntroduction()
        }
    }

    private fun getInventoryString(): String {
        return gameStateManager.player.inventory.toString()
    }

    private fun getEnemyDescription() = enemyDescription

    private fun getBiomeDescription() = biomeDescription

    private fun dgmssecret(): String {
        gameStateManager.player.inventory.addItem(Weapon.buildWeapon(Weapons.DEMONGALAXYMASTERSWORD))
        return "Oh you unlocked a sister secret."
    }

    private fun twice(): String {
        gameStateManager.player.inventory.addItem(Weapon.buildWeapon(Weapons.TWICESWORD))
        return "Hey boy. Look I'm gonna make this real simple for you. You got two options, yes or yes."
    }
}
