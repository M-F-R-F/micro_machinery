package mfrf.dbydd.micro_machinery.command;

import com.google.common.base.Charsets;
import com.google.gson.JsonObject;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mfrf.dbydd.micro_machinery.items.DebugTool;
import mfrf.dbydd.micro_machinery.utils.MathUtil;
import mfrf.dbydd.micro_machinery.utils.MultiblockStructureMaps;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ReadMultiBlockCommand implements Command<CommandSource> {
    public static ReadMultiBlockCommand Instance = new ReadMultiBlockCommand();

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayerEntity = context.getSource().asPlayer();
        ItemStack heldItem = serverPlayerEntity.getHeldItem(Hand.MAIN_HAND);
        if (!heldItem.isEmpty() && heldItem.getItem() instanceof DebugTool) {
            CompoundNBT clickedPos = heldItem.getChildTag("clickedPos");
            if (clickedPos != null) {
                if (clickedPos.contains("pos1") && clickedPos.contains("pos2") && clickedPos.contains("active_block")) {

                    CompoundNBT activeBlock = clickedPos.getCompound("active_block");
                    BlockPos pos1 = NBTUtil.readBlockPos(clickedPos.getCompound("pos1"));
                    BlockPos pos2 = NBTUtil.readBlockPos(clickedPos.getCompound("pos2"));
                    BlockPos center = NBTUtil.readBlockPos(activeBlock.getCompound("pos"));
                    Direction direction = Direction.byIndex(activeBlock.getInt("direction"));
                    ServerWorld world = context.getSource().getWorld();

//                    JsonObject jsonObject = MathUtil.getNormalizedBlockPosBox(pos1, pos2, world, direction,center).convertToJson();
                    String id = heldItem.getDisplayName().getString();
                    MultiblockStructureMaps.StructureMap structureMap = MultiblockStructureMaps.create(world, pos1, pos2, center);

                    File file = new File("test" + File.separator + id + ".json");
                    try {
//                        FileUtils.writeStringToFile(file, jsonObject.toString(), Charsets.UTF_8);
                        FileUtils.writeStringToFile(file, structureMap.toJson(id).toString(), Charsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        return 0;
    }
}
