import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


    public class Main {
        public static final Path GSON_OBJECT = Paths.get("./Erlan.json");
        public static final Path GSON_OBJECT1 = Paths.get("./Erlan1.json");
        static Scanner scanner = new Scanner(System.in);
        public static final String ЧЕРНЫЙ = "\u001B[21m";
        public static final String КРАСНЫЙ = "\u001B[24m";
        public static final String ПЕРЕГРУЗИТЬ = "\u001B[0m";
        public static final String ЗЕЛЕНЫЙ = "\u001B[28m";
        public static void main(String[] args) {
            List<AutoPark> list1 = new ArrayList<>();
            List<DriverInfo> list2 = new ArrayList<>();

            try{
                String file = new String(Files.readAllBytes(Path.of(String.valueOf(GSON_OBJECT))));
                JSONObject json = new JSONObject(file);
                for(int i = 0; i<json.length();i++){
                    JSONObject truck = json.getJSONObject(String.valueOf(i));
                    AutoPark car1 = new AutoPark();
                    car1.setId(truck.getInt("id"));
                    car1.setName(truck.getString("name"));
                    car1.setDriver(truck.getString("driver"));
                    car1.setState(truck.getString("state"));
                    list1.add(car1);
                }
                displayTrucks(list1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                String file2 = new String(Files.readAllBytes(Path.of(String.valueOf(GSON_OBJECT1))));
                JSONObject json2 = new JSONObject(file2);
                for(int i = 0 ;i<json2.length();i++){
                    JSONObject dr = json2.getJSONObject(String.valueOf(i));
                    DriverInfo driver;
                    driver = new DriverInfo();
                    i++;
                    driver.setId(i);
                    i--;
                    driver.setDriverId(dr.getString("id"));
                    driver.setName(dr.getString("name"));
                    list2.add(driver);
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            pattern(list1, list2);

        }
        public static void displayTrucks(List<AutoPark> list1){
            System.out.println(ЧЕРНЫЙ + "-----------------Фуры-------------------"+ ПЕРЕГРУЗИТЬ);
            System.out.println("|#id|      Автобус      |    Водители   |Состояние |");
            System.out.println("|———|———————————————|—————————————|——————|");
            for(AutoPark autoPark : list1){
                System.out.printf("|%-15s ",autoPark.getId());
                System.out.printf("|%-2s|", autoPark.getName());
                System.out.printf("%-14s|", autoPark.getDriver() + " ");
                System.out.printf("%-7s|",autoPark.getState());
                System.out.println();
            }
        }
        public static void displayDrivers(List<DriverInfo> list2){
            System.out.println(ЧЕРНЫЙ +"----------------Водители-----------------"+ ПЕРЕГРУЗИТЬ);
            System.out.println("|# |id-водителя |  Водителя |      Автобус     |");
            System.out.println("|——|——————————|—————————|——————————————|");
            for (DriverInfo driverInfo : list2) {
                System.out.printf("|%-4s", driverInfo.getId());
                System.out.printf("|%-5s", driverInfo.getDriverId());
                System.out.printf("|%-6s", driverInfo.getName());
                System.out.printf("|%-17s|", driverInfo.getBus());
                System.out.println();
            }
        }
        public static void pattern(List<AutoPark> list1, List<DriverInfo> list2){
            String choice;
            int id;
            while(true){
                try {
                    System.out.println(КРАСНЫЙ + "Выберите Фуру: " + ПЕРЕГРУЗИТЬ);
                    id = scanner.nextInt();
                    id--;
                    System.out.println(ЧЕРНЫЙ + "----------------Информация Грузовиков-----------------" + ПЕРЕГРУЗИТЬ);
                    System.out.println("Остановить                :" + list1.get(id).getId());
                    System.out.println("Автобус              :" + list1.get(id).getName());
                    System.out.println("Водитель           :" + list1.get(id).getDriver());
                    System.out.println("Состояние           :" + list1.get(id).getState());
                    System.out.println(КРАСНЫЙ + "Нажмите 1, чтобы изменить или назначить новый водитель." + ПЕРЕГРУЗИТЬ);
                    System.out.println(КРАСНЫЙ + "Нажмите 2, чтобы выехать на маршрут." + ПЕРЕГРУЗИТЬ);
                        System.out.println(КРАСНЫЙ + "Нажмите 3, чтобы отправит на РЕМОНТ." + ПЕРЕГРУЗИТЬ);
                    System.out.println(КРАСНЫЙ + "Нажмите 4, если вы хотите остановить программу." + ПЕРЕГРУЗИТЬ);
                    scanner.nextLine();
                    choice = scanner.nextLine();
                    if(choice.equals("4")){
                        System.exit(0);
                    }
                    ServiceImpl actions = new ServiceImpl();
                    switch (choice) {
                        case "1" -> actions.changeDriver(list1, list2, id);
                        case "2" -> actions.startDriving(list1, list2, id);
                        case "3" -> actions.startRepair(list1, list2, id);
                    }
                    list1 = actions.getList1();
                    list2 = actions.getList2();
                    displayDrivers(list2);
                    displayTrucks(list1);
                }
                catch(IndexOutOfBoundsException e){
                    System.err.println(ЗЕЛЕНЫЙ +"Извините  такой грузовик нету.\n"+
                            " Количество грузовиков: "+list1.size());
                }
            }

        }
    }

