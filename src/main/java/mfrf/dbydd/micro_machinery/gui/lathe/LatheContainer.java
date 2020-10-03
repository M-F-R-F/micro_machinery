package mfrf.dbydd.micro_machinery.gui.lathe;

import com.google.common.math.LinearTransformation;
import mfrf.dbydd.micro_machinery.blocks.machines.lathe.TileLathe;
import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_ContainerTypes;
import net.minecraft.client.renderer.Matrix3f;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import javax.annotation.Nonnull;

public class LatheContainer extends ContainerBase {
    private final TileLathe lathe;
    private IIntArray intArray;

    public LatheContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world, IIntArray intArray) {
        super(Registered_ContainerTypes.LATHE_CONTAINER.get(), id);
        this.intArray = intArray;
        trackIntArray(intArray);
        this.lathe = (TileLathe) world.getTileEntity(pos);
        ItemStackHandler itemHander = lathe.getItemHander();
        this.addSlot(new SlotItemHandler(itemHander, 0, 28, 54));
        this.addSlot(new SlotItemHandler(itemHander, 1, 132, 54) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        drawInventory(8, 100, playerInventory);
    }

    public TileLathe getLathe() {
        return lathe;
    }

    public IIntArray getIntArray() {
        return intArray;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return lathe.isUsableByPlayer(playerIn);
    }
}
