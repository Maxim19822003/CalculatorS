package ru.fortune_dongeon.game.register_and_auth.api;

import ru.fortune_dongeon.game.register_and_auth.api.controller.CalculatorController;
import ru.fortune_dongeon.game.register_and_auth.api.model.CalculatorModel;
import ru.fortune_dongeon.game.register_and_auth.api.model.ComplexNumber;
import ru.fortune_dongeon.game.register_and_auth.api.model.operations.Addition;
import ru.fortune_dongeon.game.register_and_auth.api.model.operations.Division;
import ru.fortune_dongeon.game.register_and_auth.api.model.operations.Multiplication;
import ru.fortune_dongeon.game.register_and_auth.api.model.operations.Subtraction;
import ru.fortune_dongeon.game.register_and_auth.api.view.CalculatorView;
import ru.fortune_dongeon.game.register_and_auth.api.view.ConsoleViewFactory;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class InitApp {

    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    static {
        try(FileInputStream in = new FileInputStream("C:\\Users\\maxim\\OneDrive\\Рабочий стол\\OOPCalcComplexNumber-main\\src\\main\\resources\\_logging.properties")){
            LogManager.getLogManager().readConfiguration(in);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    void init() {
        LOGGER.info("START APP ");
        CalculatorModel model = new CalculatorModel();
        LOGGER.info("init model ");
        CalculatorController controller = new CalculatorController();
        LOGGER.info("init controller ");
        CalculatorView calculatorView = new CalculatorView(new ConsoleViewFactory());
        LOGGER.info("Init view");
        controller.registerOperation("+", new Addition());
        controller.registerOperation("-", new Subtraction());
        controller.registerOperation("*", new Multiplication());
        controller.registerOperation("/", new Division());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Enter the symbol corresponding to the expression from the list: \n").trimToSize();
        stringBuilder.append("+ Addition.\n - Subtraction.\n * Multiplication.\n / Division.\n Enter the symbol: ");
        System.out.println(stringBuilder);
        String operation = calculatorView.getInput();
        LOGGER.info("User enter parameter is " + operation + " in operation");
        System.out.println("Enter real number A: \n");
        double realComplexA = Double.parseDouble(calculatorView.getInput().trim());
        System.out.println("Enter imaginary number A: \n");
        double imaginaryComplexA = Double.parseDouble(calculatorView.getInput().trim());
        ComplexNumber a = new ComplexNumber(realComplexA,imaginaryComplexA);
        System.out.println("Enter real number B: \n");
        double realComplexB = Double.parseDouble(calculatorView.getInput().trim());
        System.out.println("Enter imaginary number B: \n");
        double imaginaryComplexB = Double.parseDouble(calculatorView.getInput().trim());
        ComplexNumber b = new ComplexNumber(realComplexB,imaginaryComplexB);
        ComplexNumber result = controller.performOperation(operation, a, b);
        model.setResult(result);
        calculatorView.displayResult(model.getResult());
    }
}
