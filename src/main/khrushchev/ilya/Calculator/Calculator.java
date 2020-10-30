package khrushchev.ilya.Calculator;

import com.sun.deploy.cache.BaseLocalApplicationProperties;
import com.sun.org.glassfish.gmbal.Description;
import khrushchev.ilya.Factorial;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class Calculator {
    class Parser {
        Pattern bracketsPattern;


        Parser(){
            // TODO Починить регулярку чтобы она находила скобки с /
            String brackets = "\\((\\d(\\.\\d+)?)([\\+|\\-|\\*|\\/]{1})(\\d(\\.\\d+)?)\\)";
            bracketsPattern = Pattern.compile(brackets);
        }

        @Description("Method for parse string with ( ) and return Object[number1,operation,number2]")
        Object[] parse(String str){
            double x1=0.0, x2=0.0;
            String operation="";
            String resultString = str;
            Matcher bracketsMatcher = bracketsPattern.matcher(resultString);
            while (bracketsMatcher.find()) {
                x1 = parseDouble(bracketsMatcher.group(1)); // Возвращает левый (первый) операнд
                operation = bracketsMatcher.group(3); // Возвращает операцию
                x2 = parseDouble(bracketsMatcher.group(4)); // Возвращает правый (второй) операнд
                Double result = dictionary.get(operation).apply(x1,x2);
                resultString = resultString.replace(bracketsMatcher.group(0),result.toString());
                System.out.println(resultString);
            }
            // TODO Реализовать остальную часть парсера для простой строки resultString формата a+b-c*x/y
            // \(\d(\.\d+)?[\+|\-|\*|\/]{1}\d(\.\d+)?\)
            // 1-(2.21+1.42)+(3-1.5)-(1.5*2)+(43/21.254) = 1+3.63+1.5+3+...
//            return resultString;
            return new Object[]{x1,operation,x2};
        }
    }

    private Parser parser;
    private Map<String, BinaryOperator<Double>> dictionary = new HashMap<String,BinaryOperator<Double>>();

    Calculator(){
        dictionary.put("+", (x1, x2) -> x1+x2);
        dictionary.put("-", (x1, x2) -> x1-x2);
        dictionary.put("*", (x1, x2) -> x1*x2);
        dictionary.put("/", (x1, x2) -> x1/x2);
        parser = new Parser();
    }

    public String calculate(String expression){
        Double resultValue;
        Object[] params = parser.parse(expression);
        resultValue = dictionary.get(params[1].toString()).apply((double)params[0],(double)params[2]);
        return resultValue.toString();
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        String sr = "1-(2.21+1.42)+(3-1.5)-(1.5*2)";
        String value = calc.calculate(sr);
        System.out.println(value);
    }

}
