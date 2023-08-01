package mfrf.micro_machinery.registry_lists.worldgen;

import com.mojang.serialization.Codec;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.worldgen.VeinPiece;
import mfrf.micro_machinery.worldgen.VeinStructure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class MMStructureThings {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE_DEFERRED_REGISTER = DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE.key(), MicroMachinery.MODID);
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_DEFERRED_REGISTER = DeferredRegister.create(BuiltInRegistries.STRUCTURE_PIECE.key(), MicroMachinery.MODID);
    public static final RegistryObject<StructureType<VeinStructure>> VEIN_FEATURE = STRUCTURE_TYPE_DEFERRED_REGISTER.register("vein_feature", () -> explicitStructureTypeTyping(VeinStructure.CODEC));
    public static final RegistryObject<StructurePieceType> VEIN_PIECE = registerPieceType("vein_piece", VeinPiece::new);

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }

    private static RegistryObject<StructurePieceType> registerPieceType(String name, StructurePieceType structurePieceType) {
        return STRUCTURE_PIECE_DEFERRED_REGISTER.register(name.toLowerCase(Locale.ROOT), () -> structurePieceType);
    }
}
