package com.josephbaca.world

object Biome {

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

    internal fun getDescription(biome: BiomeType): String {
        return when (biome) {
            BiomeType.FLOWERY -> listOf(
                "gg bois u got da worst description in the game. Theres a buncha dumbass dandelions up in dis club. " +
                        "Also hope you don't have allergies. I guess theres cool grass idk use your imagination bub.",
                "Wow!!!1!1!!! so pretty!!! Those bad gu definitely won't kill you!!",
                "Wow look at all that natural lighting!!! It's so pleasant! you can feel the sun on your skin",
                "The world is a beautiful place and you're no longer afraid to die."
            ).random()
            BiomeType.SUNNY -> listOf(
                "hot. humid. congratz ur sweating now :/",
                "You know when the weather is like 70 and the rain is barely misting down on you? That's this room its v comfy"
            ).random()
            BiomeType.DESSERT -> listOf(
                "Fruit salad, Yummy Yummy",
                "This room is shaped like a milkshake, and you have reason to believe that it brings all the boys to the yard.",
                "You know that one part in shark boy and lava girl with the sea of milk and cookies? This that",
                "The walls are made of jello",
                "The floor is lava!!!!! oh wait that's chocolate syrup.",
                "You can barely walk around! There's little popcorn kettles everywhere",
                "It looks like Willy Wonka threw up in here."
            ).random()
            BiomeType.HALLOWEENTOWN -> listOf(
                "it looks spooky. Probably has a couple skeletons in it's closet",
                "This is halloween! This is Halloween(town(room))",
                "Big Bone Energy."
            ).random()
            BiomeType.STSPATRICKSDAYTOWN -> listOf(
                "Green. Smells like beer and bad decisions"
            ).random()
            BiomeType.EASTERTOWN -> listOf(
                "Oh cool theres easter eggs!!! It smells like chocolate!",
                "Theres a giant rabbit portrait. Weird flex, but ok."
            ).random()
            BiomeType.PUROLAND -> listOf(
                "It's so cute!!!!!!!!!! Literally dead!!! Theres Kuromi posters and my little twin star plushies!!!!! Actual best room",
                "it's pink i guess? It smells like marshmallows",
                "It's a cute cafe! but it doesn't really look like anyones working here"
            ).random()
            BiomeType.SHAMELESSOTHERREFRENCES -> listOf(
                "You hear a weirdly high pitched song. You're very uncomfy.",
                "Oh sick it's a purple room! You feel a weird presence tho.",
                "oh this room is filled with weird yellow rat plushies. You kinda love them.",
                "The walls are made of rocks! Theres a poster with a dude you would 100% smash(it's Brock by the way).",
                "Oh on one wall theres a line, and then on the other theres the same line and a shorter one." +
                        " Then theres two lines of the same height. Then on the last wall theres the same line and" +
                        " another lying on it's side.",
                "Its a small room(after all), its a small room(after all) ITS A SMALL ROOM (AFTER ALL), issa" +
                        "small room(after all)."

            ).random()
        }
    }
}