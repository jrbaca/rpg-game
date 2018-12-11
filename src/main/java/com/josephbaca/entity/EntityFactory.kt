package com.josephbaca.entity

class EntityFactory {
    enum class LivingEntityType {
        JOHNTHESKELETON, ZOMBIE, SKELETON
    }

    companion object {
        private fun buildLivingEntity(livingEntityType: LivingEntityType): Entity {
            return when (livingEntityType) {
                EntityFactory.LivingEntityType.JOHNTHESKELETON ->
                    Humanoid("john the skeleton", 7, "he once was a boy :(", 10)
                EntityFactory.LivingEntityType.ZOMBIE ->
                    Humanoid("Zombie", 3, "he uglyyyyyyyyyyyyyyyyy", 2)
                EntityFactory.LivingEntityType.SKELETON ->
                    Humanoid("Skeleton", 2, "idk a non john skeleton", 2)
            }
        }

        fun buildRandomDude(): Entity {
            return buildLivingEntity(LivingEntityType.values().random())
        }
    }
}