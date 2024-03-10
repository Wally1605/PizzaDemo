public class Pizza {
    float price = 14;
    String[] toppings;



    public Pizza(String[] toppings, int length){
        int count = 0;
        this.toppings = new String[length];
        for(int i =0; i < length; i++){
            this.toppings[i] = toppings[i];
            count++;
        }
        price += count*2;
    }
    public Pizza(){
        this.price = 14;
        toppings = new String[10];
    }

    public String toString() {
        String result = "$"+price+" Pizza with: ";

        if(toppings.length == 1){
            return result + this.toppings[0];
        }

        for(int i = 0; i<toppings.length-1 ; i++){
            result += this.toppings[i]+", ";
        }
        result += "and "+toppings[toppings.length-1]+".";
        return result;
    }
}
