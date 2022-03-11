package e4;


import java.util.ArrayList;

import static e4.Calculator.operations.*;

public class Calculator {

    int op_count;
    ArrayList<op> opList = new ArrayList<>();

    public class op{

        //A class operation is created to include a operator (float) and a sign(enum operations)

        operations operation;
        float operator;

        /*CONSTRUCTORS*/
        public op(operations operation, float operator) {
            this.operation = operation;
            this.operator = operator;
        }
        public op(float operator) {
            this.operator = operator;
        }

        /*GETTERS*/
        public operations getOperation() {
            return operation;
        }
        public float getOperator() {
            return operator;
        }

        public boolean isError(){//checks if the operation to be executed will return an error
            return (this.operation == DIV && this.operator == 0);
        }
    }

    public enum operations {
        SUM, SUB, DIV, MUL;

        public float SolveOP(float op1, float op2){

            return switch (this) {
                case SUM -> op1 + op2;
                case SUB -> op1 - op2;
                case DIV -> op1 / op2;
                case MUL -> op1 * op2;
            };

        }
        @Override
        public String toString() {
            return switch (this) {
                case SUM -> "+";
                case SUB -> "-";
                case DIV -> "/";
                case MUL -> "*";
            };
        }
    }


    public Calculator() {
        /*
         * Public constructor of the calculator .
         */
        op_count=0;

    }

    public void cleanOperations() {

        /*
         * Clean the internal state of the calculator
         */
        op_count = 0;
        if (this.opList!=null) opList.clear();

    }

    public void addOperation(String operation, float... values) {
        /*
          Add a new operation to the internal state of the calculator .
          It is worth mentioning that the calculator behaves in an accumulative way ,
          thus only first operation has two operands .
          The rest of computations work with the accumulated value and only an extra
          new operand . Second input value must be ignored if the operation does not
          correspond to the first one.
          @param operation operation to add , as string , "+" , " -" , "*" , "/".
         * @param values Operands of the new operation ( one or two operands ).
         * Uses the varargs feature .
         * https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html
         * @throws IllegalArgumentException If the operation does not exist .
         */

        operations sign = switch (operation) {
            case "+" -> SUM;
            case "-" -> SUB;
            case "*" -> MUL;
            case "/" -> DIV;
            default -> throw new IllegalArgumentException();
        };

        op item;
        if (op_count == 0) {

            /* If for the first operation only one value is passed as an argument,
             *throws an IllegalArgumentException
             */
            if (values.length<2)
                throw new IllegalArgumentException();

            op firstItem = new op(values[0]);
            opList.add(firstItem);

            item= new op(sign,values[1]);

        } else
            item = new op(sign, values[0]);

        opList.add(item);
        op_count++;

    }

    public float executeOperations() {

        /*
         * Execute the set of operations of the internal state of the calculator .
         * Once execution is finished , internal state ( operands and operations )
         * is restored ( EVEN if exception occurs ).
         * This calculator works with " Batches " of operations .
         * @return result of the execution
         * @throws ArithmeticException If the operation returns an invalid value
         * ( division by zero )
         */
        float result = 0;



        for (int i=0;i<=op_count-1;i++){
            if (i==0 && !opList.isEmpty()){

                /*For the first operations it opers the two first operands with the first operator(which is stored on the second element of the arrayList)*/
                if (opList.get(1).isError()) {
                    cleanOperations();
                    throw new ArithmeticException();
                } else
                    result=opList.get(1).getOperation().SolveOP(opList.get(0).getOperator(),opList.get(1).getOperator());

            } else{

                if (opList.get(i+1).isError()) {
                    cleanOperations();
                    throw new ArithmeticException();
                } else
                    result=opList.get(i+1).getOperation().SolveOP(result,opList.get(i+1).getOperator());
            }
        }
        cleanOperations();

        return result;
    }

    @Override
    public String toString() {
        /*
         * Current internal state of calculator is printed
         * FORMAT :
         * "[{+/ -/"/"/*}] value1_value2 [{+/ -/"/"/*}] value1 [{+/ -/"/"/*}] value1 {...}"
         * EXAMPLES : JUnit tests
         * @return String of the internal state of the calculator
         */
        StringBuilder text= new StringBuilder("[STATE:");

        for (int i=0;i<op_count;i++){
            if (i==0 && !opList.isEmpty()){
                text.append("[").append(opList.get(1).getOperation().toString()).append("]").append(opList.get(0).getOperator()).append("_").append(opList.get(1).getOperator());
            } else
                text.append("[").append(opList.get(i + 1).getOperation().toString()).append("]").append(opList.get(i + 1).getOperator());
        }

        return text+"]";
    }
}