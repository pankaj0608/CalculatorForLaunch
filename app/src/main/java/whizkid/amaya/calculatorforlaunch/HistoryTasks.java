package whizkid.amaya.calculatorforlaunch;

import java.util.Date;

public class HistoryTasks {

    Date date;
    String equation;
String result;

    public HistoryTasks(Date date, String equation, String result) {
        this.date = date;
        this.equation = equation;
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return getResult() + "\n" + getEquation();
    }
}
