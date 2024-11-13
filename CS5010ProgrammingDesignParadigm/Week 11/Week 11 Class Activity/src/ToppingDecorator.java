public class ToppingDecorator extends PizzaDecorator{
    private String topping;
    public ToppingDecorator(Pizza pizza, String topping) {
        super(pizza);
        this.topping = topping;
    }
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", topping with " + topping;
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.25;
    }
}
