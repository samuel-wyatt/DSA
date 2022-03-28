import java.util.*;
public class EquationSolverTest {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the equation, with spaces between each character.");
        String equation = sc.nextLine();
        EquationSolver solver = new EquationSolver();
        double solvedEquation = solver.solve(equation);
        System.out.println("Solved: " + solvedEquation);
        sc.close();
    }
}
