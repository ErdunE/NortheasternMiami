public class SauceDecorator extends PizzaDecorator{
    private String sauce;

    public SauceDecorator(Pizza pizza,String sauce) {
        super(pizza);
        this.sauce = sauce;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", with " + sauce + " sauce";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 0.5;
    }
}
