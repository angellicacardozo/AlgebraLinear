package net.ramonsilva.tests;

import net.ramonsilva.Matrix;
import net.ramonsilva.solver.Cholesky;
import net.ramonsilva.solver.GaussSiedel;
import net.ramonsilva.solver.MatrixSolver;
import net.ramonsilva.solver.MultiGrid;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ramonsilva on 27/11/16.
 */
public class MultiGridSolverTest {


    private static final double EPSILON = 1e-10;

    @Test
    public void testMultiGridSolver(){
        double[][] data = {
                { 2, -1, 0, 0, 0, 0, 0, 0, 0},
                { -1, 2, -1, 0, 0, 0, 0, 0, 0},
                { 0, -1, 2, -1, 0, 0, 0, 0, 0},
                { 0, 0, -1, 2, -1, 0, 0, 0, 0},
                { 0, 0, 0, -1, 2, -1, 0, 0, 0},
                { 0, 0, 0, 0, -1, 2, -1, 0, 0},
                { 0, 0, 0, 0, 0, -1, 2, -1, 0},
                { 0, 0, 0, 0, 0, 0,-1, 2, -1},
                { 0, 0, 0, 0, 0, 0, 0, -1, 2}
        };

        double[] indepentendTerms = {2, 1, 5, 2, 1, 5, 2, 1, 5};
        Matrix m = new Matrix(data, indepentendTerms);

        MatrixSolver solver = new MultiGrid();
        double[] solution = solver.solve(m);

        assertEquals(0.5, solution[0], EPSILON);
        assertEquals(0.125, solution[1], EPSILON);
        assertEquals(1.75, solution[2], EPSILON);
        assertEquals(1.03125, solution[3], EPSILON);
        assertEquals(0.0625, solution[4], EPSILON);
        assertEquals(2.0703125, solution[5], EPSILON);
        assertEquals(1.765625, solution[6], EPSILON);
        assertEquals(1.3828125, solution[7], EPSILON);
        assertEquals(3.421875, solution[8], EPSILON);
    }

}
