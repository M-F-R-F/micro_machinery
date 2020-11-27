package mfrf.dbydd.micro_machinery.utils;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResource;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MultiBlockStructureMaps {

    public static ArrayList<String> NAMES = new ArrayList<>();
    private static HashMap<String, MathUtil.MultiBlockPosBox> STRUCTURE_MAPS = null;

    private static void ReadStructure() {
        STRUCTURE_MAPS = new HashMap<>();
        try {
            for (String name : NAMES) {
                IResource resource = Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(Micro_Machinery.NAME, "structures/" + name + ".json"));
                STRUCTURE_MAPS.put(name, MathUtil.MultiBlockPosBox.readJson(JSONUtils.fromJson(new InputStreamReader(resource.getInputStream()))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, MathUtil.MultiBlockPosBox> getStructureMaps() {
        if (STRUCTURE_MAPS == null) {
            ReadStructure();
        }
        return STRUCTURE_MAPS;
    }
}
