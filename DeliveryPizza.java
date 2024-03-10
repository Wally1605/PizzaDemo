public class DeliveryPizza extends Pizza{
    String address;

    public DeliveryPizza(String[] toppings, int length, String address){
        super(toppings,length);
        this.address = address;
        if(super.price > 18){
            price += 3;
        }else{
            price += 5;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Delivered to: "+this.address;
    }
}
