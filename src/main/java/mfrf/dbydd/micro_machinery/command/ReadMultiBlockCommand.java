package mfrf.dbydd.micro_machinery.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mfrf.dbydd.micro_machinery.items.DebugTool;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.Hand;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

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
                    JsonObject jsonObject = DebugTool.readMultiBlock(NBTUtil.readBlockPos(clickedPos.getCompound("pos1")), NBTUtil.readBlockPos(clickedPos.getCompound("pos2")), NBTUtil.readBlockPos(clickedPos.getCompound("active_block")), context.getSource().getWorld());
//                    LogManager.getLogger().info(jsonObject.toString());

                            File file = new File("test" + File.separator + "inst.json");
                            try {
                                FileUtils.writeStringToFile(file, jsonObject.toString(), Charset.defaultCharset());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                }
            }
        return 0;
    }
}
