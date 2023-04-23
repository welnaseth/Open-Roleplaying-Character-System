package player

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.elementDescriptors
import kotlinx.serialization.encoding.*

@Serializable
data class Player(val data : MutableList<Data> = mutableListOf())

class PlayerSerializer(private val serialDescriptors: Map<String, KSerializer<out Data>>): KSerializer<Player> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("Player"){
            serialDescriptors.forEach {
                element(elementName = it.key, descriptor = it.value.descriptor, isOptional = true)
            }
        }

    override fun deserialize(decoder: Decoder): Player {
        val dataList = mutableListOf<Data>()
        decoder.decodeStructure(descriptor){
            while (true) {
                val index = decodeElementIndex(descriptor)
                if(index == CompositeDecoder.DECODE_DONE){
                    break;
                }
                dataList.add(decodeSerializableElement(descriptor, index, serialDescriptors.values.elementAt(index)))
            }
        }
        return Player(dataList)
    }

    @OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Player) {
        encoder.encodeStructure(descriptor){
            value.data.forEach{
                encodeSerializableElement(
                    descriptor,
                    descriptor.elementDescriptors.indexOf(it::class.serializer().descriptor),
                    it::class.serializer() as KSerializer<Data>,
                    it
                )
            }
        }
    }

}