package whizkid.amaya.calculatorforlaunch;

import java.util.Date;

public class HistoryTasks {

    Date date;
    String equation;


    public HistoryTasks(Date date, String equation) {
        this.date = date;
        this.equation = equation;
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

    @Override
    public String toString() {
        return "Pankaj \n" + this.equation;
    }
}
