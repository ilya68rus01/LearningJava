package khrushchev.ilya.Calculator;


import com.sun.org.glassfish.gmbal.Description;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class Calculator {
    class Parser {
        Pattern bracketsPattern;
        Pattern multAndDivisionPattern;
        Pattern addAndSubsPattern;


        Parser(){
            // TODO Починить регулярку чтобы она находила скобки с /
            String brackets = "\\((\\-?\\d+(\\.\\d+)?)([\\+|\\-|\\*|\\/]{1})(\\-?\\d+(\\.\\d+)?)\\)";
            String multAndDivision = "([\\+|\\-]?\\d+(\\.\\d+)?)(\\*|\\/){1}(\\-?\\d+(\\.\\d+)?)";
            String addAndSub = "(\\-?\\d+(\\.\\d+)?)(\\+|\\-){1}(\\d+(\\.\\d+)?)";
            bracketsPattern = Pattern.compile(brackets);
            multAndDivisionPattern = Pattern.compile(multAndDivision);
            addAndSubsPattern = Pattern.compile(addAndSub);
        }

//        @Description("Method for parse string with ( ) and return Object[number1,operation,number2]")
        String parse(String str){
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
            Matcher mdMatcher = multAndDivisionPattern.matcher(resultString);
            while (mdMatcher.find()){
                x1 = parseDouble(mdMatcher.group(1));
                operation = mdMatcher.group(3);
                x2 = parseDouble(mdMatcher.group(4));
                Double result = dictionary.get(operation).apply(x1,x2);
                resultString = resultString.replace(mdMatcher.group(), result>0? "+"+result.toString():result.toString());
                mdMatcher = multAndDivisionPattern.matcher(resultString);
                System.out.println(resultString);
            }
            Matcher asMatcher = addAndSubsPattern.matcher(resultString);
            while (asMatcher.find()){
                x1 = parseDouble(asMatcher.group(1));
                operation = asMatcher.group(3);
                x2 = parseDouble(asMatcher.group(4));
                Double result = dictionary.get(operation).apply(x1,x2);
                resultString = resultString.replace(asMatcher.group(),result.toString());
                asMatcher = addAndSubsPattern.matcher(resultString);
                System.out.println(resultString);
            }
            // (\d(\.\d+)?)[\*|\/]{1}(\d(\.\d+)?)
            // \(\d(\.\d+)?[\+|\-|\*|\/]{1}\d(\.\d+)?\)
            // 1-(2.21+1.42)+(3-1.5)-(1.5*2)+(43/21.254) = 1+3.63+1.5+3+...
            return resultString;
//            return new Object[]{x1,operation,x2};
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
//        Object[] params = parser.parse(expression);
//        resultValue = dictionary.get(params[1].toString()).apply((double)params[0],(double)params[2]);
//        return resultValue.toString();
        return parser.parse(expression);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        String sr = "1-(2.21+1.42)*(-3-1.5)+9/(-1.5/2)+(3*2)";
        String value = calc.calculate(sr);
        System.out.println(value);
    }

}
