//package mfrf.micro_machinery.mixins;
//
//import mfrf.micro_machinery.utils.MultiblockStructureMaps;
//import net.minecraft.server.MinecraftServer;
//import org.spongepowered.asm.mixin.Mixin;
//
//@Mixin(MinecraftServer.class)
//public class MixinMinecraftServer {
//    public final MultiblockStructureMaps multiblockStructureMaps = new MultiblockStructureMaps();
//
////    @Shadow
////    @Final
////    private IReloadableResourceManager resourceManager;
////
////    @Inject(method = "<init>", at = @At("RETURN"))
////    public void onConstruct(File p_i50590_1_, Proxy p_i50590_2_, DataFixer dataFixerIn, Commands p_i50590_4_, YggdrasilAuthenticationService p_i50590_5_, MinecraftSessionService p_i50590_6_, GameProfileRepository p_i50590_7_, PlayerProfileCache p_i50590_8_, IChunkStatusListenerFactory p_i50590_9_, String p_i50590_10_, CallbackInfo ci) {
////        this.resourceManager.addReloadListener(multiblockStructureMaps);
////    }
//    //todo change to event
//}
