package mfrf.micro_machinery.mixins;

import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.datafixers.DataFixer;
import mfrf.micro_machinery.utils.MultiblockStructureMaps;
import net.minecraft.command.Commands;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.world.chunk.listener.IChunkStatusListenerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.net.Proxy;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    public final MultiblockStructureMaps multiblockStructureMaps = new MultiblockStructureMaps();

    @Shadow
    @Final
    private IReloadableResourceManager resourceManager;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void onConstruct(File p_i50590_1_, Proxy p_i50590_2_, DataFixer dataFixerIn, Commands p_i50590_4_, YggdrasilAuthenticationService p_i50590_5_, MinecraftSessionService p_i50590_6_, GameProfileRepository p_i50590_7_, PlayerProfileCache p_i50590_8_, IChunkStatusListenerFactory p_i50590_9_, String p_i50590_10_, CallbackInfo ci) {
        this.resourceManager.addReloadListener(multiblockStructureMaps);
    }
}
