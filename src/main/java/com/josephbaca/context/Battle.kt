package com.josephbaca.context

import com.josephbaca.entity.Entity
import com.josephbaca.parsing.NounToken
import com.josephbaca.parsing.VerbToken

class Battle(
    private val player: Entity,
    private val enemySet: MutableSet<Entity>,
    private val contextManager: ContextManager
) : Context {

    init {
        LOG.info("Creating a fight")
        LOG.info("Player has %sHP".format(player.health))
        LOG.info("Enemies have HP: %s".format(enemySet.map { e -> "%s: %sHP".format(e.name, e.health) }))
    }

    override val verbsToken: Map<VerbToken, (List<NounToken>) -> String?> = hashMapOf(
        Pair(BattleCommands.WHERE, { args -> currentContext() }),
        Pair(BattleCommands.FIGHT, { args -> fight() })
    )

    override val nounTokens: Set<NounToken> = setOf()

    override fun currentContext(): String {
        return "In a battle"
    }

    fun info(): String {
        return "%s (%s/%sHP) vs %s".format(
            player.name,
            player.health,
            player.maxHealth,
            enemySet.map { e -> e.name })
    }

    /**
     * Carries out one round of fighting. Contains checking logic.
     */
    fun fight(): String {
        fightHelper()
        return if (enemySet.isEmpty()) {
            contextManager.removeContextLayer()
            "You won!"
        } else if (!player.isAlive) {
            listOf(
                "cy@ later alligator"
            ).random()
        } else {
            info()
        }
    }

    /**
     * Has players attack each other.
     */
    private fun fightHelper() {
        // Player attacks random enemy
        val targetEnemy = enemySet.random()
        targetEnemy.health = targetEnemy.health - player.attackDamage
        purgeEnemySet()

        // Enemies all attack player
        enemySet.forEach { enemy ->
            val damage = enemy.attackDamage
            LOG.info("%s doing %s damage to %s".format(enemy, damage, player))
            player.health = player.health - damage
        }
        LOG.info("Enemies have HP: %s".format(enemySet.map { e -> "%s: %sHP".format(e.name, e.health) }))
        purgeEnemySet()

        LOG.info("Player has %sHP".format(player.health))
        LOG.info("Enemies have HP: %s".format(enemySet.map { e -> "%s: %sHP".format(e.name, e.health) }))
    }

    /**
     * Removes [Entity] from the [enemySet] if dead.
     */
    private fun purgeEnemySet() {
        enemySet.removeIf { enemy -> !enemy.isAlive }
    }

    companion object {
        private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)
    }
}