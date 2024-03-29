package mfrf.micro_machinery.command;

import com.google.common.base.Charsets;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mfrf.micro_machinery.item.DebugTool;
import mfrf.micro_machinery.utils.MultiblockStructureMaps;
import mfrf.micro_machinery.utils.NBTUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ReadMultiBlockCommand implements Command<CommandSourceStack> {
    public static ReadMultiBlockCommand Instance = new ReadMultiBlockCommand();

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        if (source.getEntityOrException() instanceof ServerPlayer serverPlayer) {
            ItemStack heldItem = serverPlayer.getItemInHand(InteractionHand.MAIN_HAND);
            if (!heldItem.isEmpty() && heldItem.getItem() instanceof DebugTool) {
                CompoundTag clickedPos = heldItem.getTagElement("clickedPos");
                if (clickedPos != null) {
                    if (clickedPos.contains("pos1") && clickedPos.contains("pos2") && clickedPos.contains("active_block")) {

                        CompoundTag activeBlock = clickedPos.getCompound("active_block");
                        BlockPos pos1 = NBTUtil.readBlockPos(clickedPos.getCompound("pos1"));
                        BlockPos pos2 = NBTUtil.readBlockPos(clickedPos.getCompound("pos2"));
                        BlockPos center = NBTUtil.readBlockPos(activeBlock.getCompound("pos"));
                        Direction direction = Direction.from2DDataValue(activeBlock.getInt("direction"));
                        ServerLevel world = (ServerLevel) serverPlayer.level();
                        String id = heldItem.getDisplayName().getString();
                        File file = new File("test" + File.separator + id + ".json");


                        MultiblockStructureMaps.StructureMap structureMap = MultiblockStructureMaps.create(world, pos1, pos2, center, direction);
                        try {
                            FileUtils.writeStringToFile(file, structureMap.toJson(id).toString(), Charsets.UTF_8);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        return 0;
    }
}
