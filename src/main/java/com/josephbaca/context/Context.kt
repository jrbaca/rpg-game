package com.josephbaca.context

import com.josephbaca.parsing.*

/**
 * Interface for any class that can be used in [ContextManager],  [Parser], etc.
 */
interface Context {

    val contextManager: ContextManager

    /**
     * Verb allTokens that are always accessible.
     */
    val globalVerbTokens: Map<VerbToken, (List<NounToken>) -> String?>
        get() = hashMapOf(
            Pair(GlobalVerbTokens.HELP, { args -> getTokenHelpStrings() }),
            Pair(GlobalVerbTokens.NEWGAME, { args -> contextManager.newGame() })
        )

    /**
     * Tokens that are mapped to functions. Returns null if given invalid arguments.
     */
    val localVerbTokens: Map<VerbToken, (List<NounToken>) -> String?>

    /**
     * Tokens that are mapped to concepts or nothing.
     */
    val localNounTokens: Set<NounToken>


    /**
     * Set of all tokens.
     */
    val allTokens: Set<Token>
        get() = localVerbTokens.keys.toSet().plus(localNounTokens).plus(globalVerbTokens.keys.toSet())

    /**
     * Combined map of local and global verb tokens.
     */
    val allVerbTokens: Map<VerbToken, (List<NounToken>) -> String?>
        get() = localVerbTokens.plus(globalVerbTokens)

    fun getTokenHelpStrings(): String {
        return allVerbTokens.keys.sortedBy { token -> token.helpUsage }
            .joinToString(separator = "\n") { token ->
                "%s: %s".format(token.helpUsage, token.helpString)
            }
    }

}
