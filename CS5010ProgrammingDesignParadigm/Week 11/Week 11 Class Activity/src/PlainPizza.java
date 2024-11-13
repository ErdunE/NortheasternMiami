public class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza with cheese";
    }

    @Override
    public double getCost() {
        return 5.00;
    }
}
