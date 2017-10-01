import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

public class Calc {

    int n_ = 10;
    Float[] s_ = new Float[n_];
    int i_ = -1;
    HashMap<String, Float> vars = new HashMap<String, Float>();

    private boolean isEmpty() {
        if (i_ >= 0)
            return false;
        System.out.println("Stack is empty!");
        return true;
    }

    private boolean noData() {
        if (i_ >= 1)
            return false;
        System.out.println("Too few operands in stack!");
        return true;
    }

    public void DEFINE(String var, Float value) {
        vars.put(var, value);
    }

    public void PUSH(String var) {
        Float value = vars.get(var);
        if (i_ == n_)
            s_ = Arrays.copyOf(s_, s_.length * 2);
        s_[++i_] = value;
    }

    public void PUSH_VALUE(Float value) {
        if (i_ == n_)
            s_ = Arrays.copyOf(s_, s_.length * 2);
        s_[++i_] = value;
    }

    public Float POP() {
        if (!isEmpty())
            return s_[i_--];
        else
            return null;
    }
    public void PRINT(){
        System.out.println(s_[i_]);
    }
    public void SHOW() {
        if (!isEmpty())
            for (int i = i_; i >= 0; i--)
                System.out.println(s_[i]);
    }
    public void SQRT() {
        if (!isEmpty())
            PUSH_VALUE((float) Math.sqrt(POP()));
    }

    public void PLUS() {
        if (!noData())
            PUSH_VALUE(POP()+POP());
    }
    public void MINUS() {
        if (!noData())
            PUSH_VALUE(-POP()+POP());
    }
    public void MULTIPLY() {
        if (!noData())
            PUSH_VALUE(POP()*POP());
    }
    public void DIVIDE() {
        if (!noData())
            PUSH_VALUE(1/POP()*POP());
    }

    public static void main(String[] args) {
        Calc obj = new Calc();
        obj.stack(args);
    }

    public void stack(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = "";
        boolean isComment=false;
        while (a != "0") {
            if (!isComment)
                a = sc.next();
            else
                isComment = false;
            switch (a) {
                case "DEFINE":
                    DEFINE(sc.next(), sc.nextFloat());
                    break;
                case "PUSH":
                    if (sc.hasNextFloat())
                        PUSH_VALUE(sc.nextFloat());
                    else
                        PUSH(sc.next());
                    break;
                case "POP":
                    POP();
                    break;
                case "PRINT":
                    PRINT();
                    break;
                case "SHOW":
                    SHOW();
                    break;
                case "SQRT":
                    SQRT();
                    break;
                case "+":
                    PLUS();
                    break;
                case "-":
                    MINUS();
                    break;
                case "*":
                    MULTIPLY();
                    break;
                case "/":
                    DIVIDE();
                    break;
                case "#":
                    sc.nextLine();
                    isComment=true;
            }
        }
    }
}

