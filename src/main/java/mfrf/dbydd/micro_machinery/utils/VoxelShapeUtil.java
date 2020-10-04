package mfrf.dbydd.micro_machinery.utils;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.List;

/**
 * Require org.apache.commons.math3.
 * Of course you can use other libs,just need a bit of change.
 */
public class VoxelShapeUtil {
    /**
     * Affine transformation = Linear transformation + transformation
     */
    private static final RealMatrix MATRIX_ROT_WEST = new Array2DRowRealMatrix(new double[][]{
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {-1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix MATRIX_ROT_SOUTH = new Array2DRowRealMatrix(new double[][]{
            {-1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix MATRIX_ROT_EAST = new Array2DRowRealMatrix(new double[][]{
            {0, 0, -1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix MATRIX_ROT_NORTH = new Array2DRowRealMatrix(new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix OFFSET_MATRIX = new Array2DRowRealMatrix(new double[][]{
            {0, 0, 0, 8},
            {0, 0, 0, 8},
            {0, 0, 0, 8},
            {0, 0, 0, 0}
    });

    /**
     * @param matrix An Affine-transformation matrix, the 3x3 sub matrix at upper left means linear transformation.
     *               The first three elements in the fourth column means offset,and the final elements should always be 1.
     * @param shape  the VoxelShape need to transform.
     * @return the VoxelShape that been transformed.
     */
    public static VoxelShape transformation(RealMatrix matrix, VoxelShape shape) {
//        AxisAlignedBB boundingBox = shape.getBoundingBox();
        List<AxisAlignedBB> axisAlignedBBS = shape.toBoundingBoxList();
//        ArrayList<VoxelShape> shapes = new ArrayList<>();
        RealMatrix realMatrix = matrix.add(OFFSET_MATRIX);
        VoxelShape returnValue = VoxelShapes.empty();
        for (AxisAlignedBB boundingBox : axisAlignedBBS) {
            RealVector beginPoint = new ArrayRealVector(new double[]{(boundingBox.minX * 16) - 8, boundingBox.minY * 16 - 8, (boundingBox.minZ * 16) - 8, 1});
            RealVector lastPoint = new ArrayRealVector(new double[]{(boundingBox.maxX * 16) - 8, boundingBox.maxY * 16 - 8, (boundingBox.maxZ * 16) - 8, 1});

            RealVector beginPointTransitioned = realMatrix.operate(beginPoint);
            RealVector lastPointTransitioned = realMatrix.operate(lastPoint);

//            shapes.add(Block.makeCuboidShape(beginPointTransitioned.getEntry(0), beginPointTransitioned.getEntry(1), beginPointTransitioned.getEntry(2), lastPointTransitioned.getEntry(0), lastPointTransitioned.getEntry(1), lastPointTransitioned.getEntry(2)));
            returnValue = VoxelShapes.or(returnValue, Block.makeCuboidShape(beginPointTransitioned.getEntry(0), beginPointTransitioned.getEntry(1), beginPointTransitioned.getEntry(2), lastPointTransitioned.getEntry(0), lastPointTransitioned.getEntry(1), lastPointTransitioned.getEntry(2)));
        }
//        return shapes.size() == 1 ? shapes.get(0) : VoxelShapes.or(shapes.get(0), shapes.subList(1, shapes.size() - 1).toArray(new VoxelShape[shapes.size() - 1]));
        return returnValue;
    }

    /**
     * @param shape     VoxelShape that need to rotate.
     * @param direction The direction of rotate. North is default direction(did nothing).
     * @return VoxelShape that has rotated.
     */
    public static VoxelShape rotateDirection(VoxelShape shape, Direction direction) {
        if (direction != Direction.UP && direction != Direction.DOWN) {
            switch (direction) {
                case EAST:
                    return transformation(MATRIX_ROT_EAST, shape);
                case WEST:
                    return transformation(MATRIX_ROT_WEST, shape);
                case SOUTH:
                    return transformation(MATRIX_ROT_SOUTH, shape);
                default:
                    return transformation(MATRIX_ROT_NORTH, shape);
            }
        }
        return shape;
    }
}
