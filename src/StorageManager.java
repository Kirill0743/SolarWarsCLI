public class StorageManager {
    short[] Storage = new short[8];
    short[] Prices = new short[8];
    int money = 0;
    int debt = 0;
    int savings = 0;
    boolean gun = false;
    int capacity = 80;
    void refreshPrices(int mode){
        Prices[0] = (short) IOManager.rand.nextInt(100,2000);
        Prices[1] = (short) IOManager.rand.nextInt(5000,30000);
        Prices[2] = (short) IOManager.rand.nextInt(10,300);
        Prices[3] = (short) IOManager.rand.nextInt(4000,11000);
        Prices[4] = (short) IOManager.rand.nextInt(1500,4000);
        Prices[5] = (short) IOManager.rand.nextInt(100,3000);
        if (mode == 4){
            Prices[6] = 0;
        }
        else {
            Prices[6] = (short) IOManager.rand.nextInt(2000,5000);
        }
        Prices[7] = (short) IOManager.rand.nextInt(100,2000);
        if (mode == 2){
            Prices[7] = (short) (Prices[7]*1.2);
        }
    }
    int calculateOccupied(){
        int busy = 0;
        for (byte i = 0; i < 8;i++){
            busy = busy + Storage[i];
        }
        return busy;
    }
    void printTable(){
        for (byte i = 0; i < 8;i++){
            if (Prices[i] == 0) {
                System.out.println("I"+i+" ["+Storage[i]+"] "+ IOManager.ProductName(i)+" N/A");
            }
            else{
                System.out.println("I"+i+" ["+Storage[i]+"] "+ IOManager.ProductName(i)+" "+Prices[i]+"$");
            }
        }
        System.out.print("C: "+money+"$ | D: "+debt+"$ | S: "+savings+"$ | "+ calculateOccupied()+"/"+capacity);
        if (gun){
            System.out.println(" | Gun installed.");
        }
        else {
            System.out.println();
        }
    }
    void add(int item, int ammount, boolean payment){
        Storage[item] = (short) (Storage[item] + ammount);
        if (payment){
            money = money - (Prices[item] * ammount);
        }
    }
    void remove(int item, int ammount, boolean payment){
        Storage[item] = (short) (Storage[item] - ammount);
        if (payment){
            money = money + (Prices[item] * ammount);
        }
    }
    void clear(){
        for (byte i = 0; i < 8;i++){
            Storage[i] = 0;
        }
    }
    void tickVaults(){
        debt = (int) (debt * 1.160);
        savings = (int) (savings * 1.120);
    }
}
