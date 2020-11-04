package khrushchev.ilya.Calculator;


import com.sun.istack.internal.NotNull;

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
            String brackets = "\\((\\-?\\d+(\\.\\d+)?)([\\+|\\-|\\*|\\/]{1})(\\-?\\d+(\\.\\d+)?)\\)";
            String multAndDivision = "([\\+|\\-]?\\d+(\\.\\d+)?)(\\*|\\/){1}(\\-?\\d+(\\.\\d+)?)";
            String addAndSub = "([\\+|\\-]?\\d+(\\.\\d+)?)(\\+|\\-){1}(\\d+(\\.\\d+)?)";
            bracketsPattern = Pattern.compile(brackets);
            multAndDivisionPattern = Pattern.compile(multAndDivision);
            addAndSubsPattern = Pattern.compile(addAndSub);
        }

        Float parse(String str){
            String resultString = str;
            Matcher bracketsMatcher = bracketsPattern.matcher(resultString);
            while (bracketsMatcher.find()) {
                resultString = resultString.replace(bracketsMatcher.group(0),
                        simplifyExpression(bracketsMatcher));
                bracketsMatcher = bracketsPattern.matcher(resultString);
            }
            Matcher multiplyAndDivisionMatcher = multAndDivisionPattern.matcher(resultString);
            while (multiplyAndDivisionMatcher.find()){
                Double result = parseDouble(simplifyExpression(multiplyAndDivisionMatcher));
                resultString = resultString.replace(multiplyAndDivisionMatcher.group(0),
                        result>0? "+"+result.toString():result.toString());
                multiplyAndDivisionMatcher = multAndDivisionPattern.matcher(resultString);
            }
            Matcher addictionAndSubstractMatcher = addAndSubsPattern.matcher(resultString);
            while (addictionAndSubstractMatcher.find()){
                resultString = resultString.replace(addictionAndSubstractMatcher.group(0),
                        simplifyExpression(addictionAndSubstractMatcher));
                addictionAndSubstractMatcher = addAndSubsPattern.matcher(resultString);
            }
            return Float.parseFloat(resultString);
        }

        private String simplifyExpression(@NotNull Matcher matcher){
            double x1 = parseDouble(matcher.group(1)); // Возвращает левый (первый) операнд
            String operation = matcher.group(3); // Возвращает операцию
            double x2 = parseDouble(matcher.group(4)); // Возвращает правый (второй) операнд
            Double result = dictionary.get(operation).apply(x1,x2);
            return result.toString();
        }
    }

    private Parser parser;
    private Map<String, BinaryOperator<Double>> dictionary = new HashMap<String,BinaryOperator<Double>>();

    public Calculator(){
        dictionary.put("+", (x1, x2) -> x1+x2);
        dictionary.put("-", (x1, x2) -> x1-x2);
        dictionary.put("*", (x1, x2) -> x1*x2);
        dictionary.put("/", (x1, x2) -> x1/x2);
        parser = new Parser();
    }

    public String calculate(String expression){
        Float result = parser.parse(expression);
        return result.toString();
    }
}
