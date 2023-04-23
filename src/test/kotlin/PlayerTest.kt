import fiftheditionsrd.abilityscores.AbilityScores
import fiftheditionsrd.abilityscores.Health
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import player.Data
import player.Player
import player.PlayerSerializer
import kotlin.test.Test
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

class PlayerTest {

    @Test
    fun test(){
        val format = Json { prettyPrint = true }
        val player = Player()
        val serializers = mapOf(Pair("health", Health.serializer()), Pair("ability_scores", AbilityScores.serializer()))
        player.data.add(Health(10))
        player.data.add(AbilityScores(str = 10, dex = 10, con = 10, int = 10, wis = 10, cha = 10))
        println(format.encodeToString(PlayerSerializer(serializers),player))

        val player2 = Player()
        player2.data.add(AbilityScores(str = 10, dex = 10, con = 10, int = 10, wis = 10, cha = 10))
        player2.data.add(Health(10))
        println(format.encodeToString(PlayerSerializer(serializers),player2))

        val player3 = Player()
        player3.data.add(AbilityScores(str = 10, dex = 10, con = 10, int = 10, wis = 10, cha = 10))
        println(format.encodeToString(PlayerSerializer(serializers),player3))

        val player4 = Player()
        player4.data.add(Health(10))
        println(format.encodeToString(PlayerSerializer(serializers),player4))

        val temp = format.encodeToJsonElement(PlayerSerializer(serializers), player)

        val player5 = format.decodeFromString(PlayerSerializer(serializers),format.encodeToString(PlayerSerializer(serializers),player))
        println(player5)
    }
}