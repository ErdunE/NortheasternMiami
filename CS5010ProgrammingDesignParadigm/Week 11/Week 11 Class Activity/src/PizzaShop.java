public class PizzaShop {
    public static void main(String[] args) {


        Pizza myPizza = new PlainPizza();

        System.out.println(myPizza.getDescription());
        System.out.println(myPizza.getCost());

        myPizza = new SauceDecorator(myPizza, "Tomato");
        System.out.println(myPizza.getDescription());
        System.out.println(myPizza.getCost());

        myPizza = new ToppingDecorator(myPizza, "Cheese");
        myPizza = new ToppingDecorator(myPizza, "Pepperoni");
        myPizza = new ToppingDecorator(myPizza, "Pepper");
        System.out.println(myPizza.getDescription());
        System.out.println(myPizza.getCost());
    }
}