public class EquationSolverTest {
    public static void main(String args[]) {
        EquationSolver s = new EquationSolver();
        //Simple equation
        System.out.println(s.solve("6 + 2"));//8
        System.out.println(s.solve("6 - 2"));//4
        System.out.println(s.solve("6 * 2"));//12
        System.out.println(s.solve("6 / 2"));//3

        //Addition first
        System.out.println(s.solve("6 + 2 + 2"));//10 
        System.out.println(s.solve("6 + 2 - 2"));//6
        System.out.println(s.solve("6 + 2 * 2"));//10
        System.out.println(s.solve("6 + 2 / 2"));//7

        //Subtraction first.
        System.out.println(s.solve("6 - 2 + 2"));//6
        System.out.println(s.solve("6 - 2 - 2"));//2
        System.out.println(s.solve("6 - 2 * 2"));//2
        System.out.println(s.solve("6 - 2 / 2"));//5

        //Multiplication first.
        System.out.println(s.solve("6 * 2 + 2"));//14
        System.out.println(s.solve("6 * 2 - 2"));//10
        System.out.println(s.solve("6 * 2 * 2"));//24
        System.out.println(s.solve("6 * 2 / 2"));//6

        //Division first.
        System.out.println(s.solve("6 / 2 + 2"));//5
        System.out.println(s.solve("6 / 2 - 2"));//1
        System.out.println(s.solve("6 / 2 * 2"));//6
        System.out.println(s.solve("6 / 2 / 2"));//1.5

        System.out.println(s.solve("( 10.3 * ( 14 + 3.2 ) ) / ( 5 + 2 - 4 * 3 )"));
        System.out.println(s.solve("( ( 2 - 3 ) / 4 * ( 1 + 9 ) ) * 2"));
    }
}
