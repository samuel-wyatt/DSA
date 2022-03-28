public class EquationSolver {
    public double solve(String equation){
        return evaluatePostfix(parseInfixToPostfix(equation));
    }

    private ShufflingQueue parseInfixToPostfix(String equation) {
        //Create a string array containing the equation.
        String[] tokens = equation.split(" ");
       
        //Create a stack of the length of the string array
        DSAStack stack = new DSAStack(tokens.length);
        
        //Create a shuffling queue of the length of the string array.
        ShufflingQueue queue = new ShufflingQueue(tokens.length);
        
        //For loop iterates through each value of the equation.
        for (int i = 0; i < tokens.length; i++) {

            //Create a string using the value at the element i.
            String x = tokens[i];

            //Checks if x is equal to (, which will open a subequation.
            if (x.equals("(")) {
                //Pushes the bracket onto the stack.
                stack.push(x);
            
            //Checks if x is a operator.
            } else if (precedenceOf(x) > 0) {
                if (!(stack.isEmpty())) { 
                    while (precedenceOf((String)stack.top()) >= precedenceOf(x)) {
                        queue.enqueue(stack.pop());
                    }
                }
                //Enqueue's the value on the top of the stack, while the precedence of the operator on the stack is greater than or equal to the precedence of x.
                stack.push(x);

            //Checks if x is a closing bracket.
            } else if (x.equals(")")) {
                
                //Enqueues the values on the stack until the first bracket is reached.
                while (!stack.top().equals("(")) {
                    queue.enqueue(stack.pop());
                }
                //Removes the first bracket but does not enqueue it.
                stack.pop();
            } else {
                //At this point x is a double, so it is added to the queue.
                queue.enqueue(Double.parseDouble(x));
            }
        //Enqueues the operators on the stack until it is empty
        } while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }
        return queue;
    }

    private double evaluatePostfix(ShufflingQueue postfixQueue) {
        //Creates a stack of the length of the queue.
        DSAStack operandStack = new DSAStack(postfixQueue.getCount());

        //Loops while postfixQueue has data.
        while (!postfixQueue.isEmpty()) {
            //Creates a string variable containing the value on the queue.
            String x = (String)postfixQueue.dequeue();
            System.out.println(postfixQueue.getCount());
            //Checks if x is an operator.
            if (precedenceOf(x) > 0) {
                //Creates two double variables from the last two variables on the stack.
                double op2 = Double.parseDouble((String)operandStack.pop());
                double op1 = Double.parseDouble((String)operandStack.pop());
                
                //Calls the executeOperation function, which returns the result depending on the operator.
                //Adds this value to the stack.
                operandStack.push(executeOperation(x, op1, op2));
                operandStack.display();
            } else {
                operandStack.push(Double.parseDouble(x));
            }
            operandStack.display();
            postfixQueue.display();
        }
        System.out.println("Outside");
        operandStack.display();
        return (double)operandStack.pop();
    }
    
    private int precedenceOf(String theOp) {
        if (theOp.equals("+") || theOp.equals("-")) {
            return 1;
        } else if (theOp.equals("*") || theOp.equals("/")) {
            return 2;
        }
        return -1;
    }
    private double executeOperation(String op, double op1, double op2) {
        double answer = 0;
        switch(op) {
            case "+":   answer = op1 + op2;
            break;
            case "-":   answer = op1 - op2;
            break;
            case "*":   answer = op1 * op2;
            break;
            case "/":   answer = op1 / op2;
            break;
        }
        return answer;
    }
}
