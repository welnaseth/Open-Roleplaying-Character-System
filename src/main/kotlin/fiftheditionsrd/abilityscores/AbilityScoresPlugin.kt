package fiftheditionsrd.abilityscores

import player.Player
import component.Component

class AbilityScoresPlugin : Component(name = "Fifth Edition SRD Ability Scores") {
    override fun intializePlayer(player: Player) {
        player.data.add(AbilityScores(str = 10, dex = 10, con = 10, int = 10, wis = 10, cha = 10))
    }
}