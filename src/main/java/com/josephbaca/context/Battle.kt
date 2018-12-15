package com.josephbaca.context

import com.josephbaca.entity.Entity
import com.josephbaca.parsing.NounToken
import com.josephbaca.parsing.VerbToken

class Battle(
    private val player: Entity,
    private val enemySet: MutableSet<Entity>,
    override val contextManager: ContextManager
) : Context {

    init {
        LOG.info("Creating a fight")
        LOG.info("Player has %sHP".format(player.health))
        LOG.info("Enemies have HP: %s".format(enemySet.map { e -> "%s: %sHP".format(e.name, e.health) }))
    }

    override val localVerbTokens = hashMapOf<VerbToken, (List<NounToken>) -> String?>(
        Pair(BattleCommands.ATTACK, { args -> simulateOneRoundOfFighting() })
    )

    override val localNounTokens: Set<NounToken> = setOf()

    fun makeBattleIntroduction(): String {
        return "%s (%s/%sHP) vs %s".format(
            player.name,
            player.health,
            player.maxHealth,
            enemySet.joinToString(" and ") { e -> e.name }
        )
    }

    private fun simulateOneRoundOfFighting(): String {
        haveEntitiesAttackEachOther()
        return makeResultString()
    }

    private fun haveEntitiesAttackEachOther() {
        // Player attacks random enemy
        val targetEnemy = enemySet.random()

        haveEntityAttackEntity(player, targetEnemy)
        removeDeadEnemiesFromEnemySet()

        enemySet.forEach { haveEntityAttackEntity(it, player) }

        LOG.info("Player has %sHP".format(player.health))
        LOG.info("Enemies have HP: %s".format(enemySet.map { e -> "%s: %sHP".format(e.name, e.health) }))
    }

    private fun makeResultString(): String {
        return if (enemySet.isEmpty()) {
            contextManager.removeContextLayer()
            getWinString()
        } else if (!player.isAlive) {
            getDeathString()
        } else {
            return makeBattleIntroduction() // TODO make this better
        }
    }

    private fun haveEntityAttackEntity(attacker: Entity, target: Entity) {
        LOG.info("%s doing %s damage to %s".format(attacker, attacker.attackDamage, target))
        target.health -= attacker.attackDamage
    }

    private fun removeDeadEnemiesFromEnemySet() {
        enemySet.removeIf { enemy -> !enemy.isAlive }
    }


    private companion object {
        private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)

        private val deathStrings = listOf(
            "you died :(",
            "cy@ later alligator"
        )
        private val winStrings = listOf(
            "You won!",
            "You did it!"
        )

        private fun getDeathString(): String {
            return deathStrings.random()
        }

        private fun getWinString(): String {
            return winStrings.random()
        }
    }
}