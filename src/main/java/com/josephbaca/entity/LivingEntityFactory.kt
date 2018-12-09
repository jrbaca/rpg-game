package com.josephbaca.entity

class LivingEntityFactory {
    enum class LivingEntityType {
        JOHNTHESKELETON, ZOMBIE, SKELETON
    }

    companion object {
        private fun buildLivingEntity(livingEntityType: LivingEntityType): LivingEntity {
            return when (livingEntityType) {
                LivingEntityFactory.LivingEntityType.JOHNTHESKELETON ->
                    Humanoid("john the skeleton", 7, "he once was a boy :(", 10)
                LivingEntityFactory.LivingEntityType.ZOMBIE ->
                    Humanoid("Zombie", 3, "he uglyyyyyyyyyyyyyyyyy", 2)
                LivingEntityFactory.LivingEntityType.SKELETON ->
                    Humanoid("Skeleton", 2, "idk a non john skeleton", 2)
            }
        }

        fun buildRandomDude(): LivingEntity {
            return buildLivingEntity(LivingEntityType.values().random())
        }
    }
}