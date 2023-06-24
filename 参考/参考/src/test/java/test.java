import net.minecraft.block.Block;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class test {
    private static final RealMatrix MATRIX_ROT_SOUTH = new Array2DRowRealMatrix(new double[][]{
            {-1, 0, 0, 16},
            {0, 1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 1}
    });

    public void rotate() {
        AxisAlignedBB boundingBox = VoxelShapes.create(0, 0, 0, 16, 4, 15).getBoundingBox();
        RealVector beginPoint = new ArrayRealVector(new double[]{boundingBox.minX, boundingBox.minY, boundingBox.minZ, 1});
        RealVector lastPoint = new ArrayRealVector(new double[]{boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ, 1});
        RealVector beginPointTransitioned = MATRIX_ROT_SOUTH.operate(beginPoint);
        RealVector lastPointTransitioned = MATRIX_ROT_SOUTH.operate(lastPoint);
        VoxelShape returnShape = VoxelShapes.create(beginPointTransitioned.getEntry(0)/16D, beginPointTransitioned.getEntry(1)/16D, beginPoint.getEntry(2)/16D, lastPointTransitioned.getEntry(0)/16D, lastPointTransitioned.getEntry(1)/16D, lastPoint.getEntry(2)/16D);
        int i = 1 + 1;
        i = 2;
    }

}
