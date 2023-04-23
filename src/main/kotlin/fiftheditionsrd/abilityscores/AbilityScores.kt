package fiftheditionsrd.abilityscores

import kotlinx.serialization.Serializable
import player.Data

@Serializable
data class AbilityScores(val str: Int, val dex: Int, val con: Int, val int: Int, val wis: Int, val cha: Int): Data
