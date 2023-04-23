package component

import player.Player

abstract class Component(val name: String) {
    abstract fun intializePlayer(player: Player)
}